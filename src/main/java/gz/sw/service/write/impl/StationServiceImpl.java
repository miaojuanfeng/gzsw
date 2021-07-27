package gz.sw.service.write.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import gz.sw.constant.CommonConst;
import gz.sw.constant.NumberConst;
import gz.sw.entity.write.Station;
import gz.sw.mapper.write.StationDao;
import gz.sw.service.read.RainfallService;
import gz.sw.service.write.StationService;
import gz.sw.util.NumberUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationDao stationDao;

	@Autowired
	private RainfallService rainfallService;

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return stationDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return stationDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public int selectRainCount(String selfP, String diffP, String stcd) {
		return stationDao.selectRainCount(selfP, diffP, stcd);
	}

	@Override
	public List selectRainList(Integer page, Integer limit, String selfP, String diffP, String stcd) {
		return stationDao.selectRainList(page, limit, selfP, diffP, stcd);
	}

	@Override
	public List selectListByType(String type) {
		return stationDao.selectListByType(type);
	}

	@Override
	public List selectAll() {
		return stationDao.selectAll();
	}

	@Override
	public List selectRain() {
		return stationDao.selectRain();
	}

	@Override
	public int insertBatch(List<Station> stationList) {
		return stationDao.insertBatch(stationList);
	}

	@Override
	public void clear() {
		stationDao.clear();
	}

	@Override
	public void dbcc() {
		stationDao.dbcc();
	}

	@Override
	@Transactional
	@Scheduled(cron="0 5 * * * ? ")
	public void unusual() {
		synchronized (CommonConst.stationLock) {
			System.out.println("执行");
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			String date = format.format(now);
//						String date = "2018-06-01 03:00:00";
			doUnusual(date);
		}
	}

	@Override
	@Transactional
	public void unusual(String date) {
		synchronized (CommonConst.stationLock) {
			doUnusual(date);
		}
	}

	private void doUnusual(String date){
		List<Station> stationList = selectAll();
		List<Station> insertList = new ArrayList<>();
		List<String> stcdList = new ArrayList<>();
		List<Map> rainfallList = new ArrayList<>();
		clear();
		dbcc();
		for (Station station : stationList) {
			stcdList.add(station.getStcd().trim());
			if (stcdList.size() > 100) {
				rainfallList.addAll(rainfallService.selectNearStationRainfall(stcdList, date));
				stcdList.clear();
			}
		}
		if (stcdList.size() > 0) {
			rainfallList.addAll(rainfallService.selectNearStationRainfall(stcdList, date));
			stcdList.clear();
		}
		for (Station station : stationList) {
			Boolean isSetSelf = false;
			Boolean isSetNear = false;
			station.setDateP(date);
			insertList.add(station);
			/**
			 * 本站雨量
			 */
			for (int i = 0; i < rainfallList.size(); i++) {
				String stcd1 = String.valueOf(rainfallList.get(i).get("stcd"));
				BigDecimal p1 = new BigDecimal(String.valueOf(rainfallList.get(i).get("p")));
				if (station.getStcd().trim().equals(stcd1.trim())) {
					station.setSelfP(p1);
					isSetSelf = true;
					break;
				}
			}
			/**
			 * 没有临站就继续
			 */
			if (station.getNearStcd() == null) {
				continue;
			}
			/**
			 * 临站雨量
			 */
			for (int i = 0; i < rainfallList.size(); i++) {
				String stcd2 = String.valueOf(rainfallList.get(i).get("stcd"));
				BigDecimal p2 = new BigDecimal(String.valueOf(rainfallList.get(i).get("p")));
				if (station.getNearStcd().trim().equals(stcd2.trim())) {
					station.setNearP(p2);
					isSetNear = true;
					break;
				}
			}
			if( isSetSelf && station.getSelfP() == null ){
				station.setSelfP(NumberConst.ZERO);
			}
			if( isSetNear && station.getNearP() == null ){
				station.setNearP(NumberConst.ZERO);
			}
			if( station.getSelfP() == null && station.getNearP() == null ){
				station.setDiffP(null);
			}else if( station.getSelfP() == null && station.getNearP() != null ||
					station.getSelfP() != null && station.getNearP() == null ||
					NumberUtil.gt(station.getSelfP(), NumberConst.ZERO) && NumberUtil.et(station.getNearP(), NumberConst.ZERO) ||
					NumberUtil.et(station.getSelfP(), NumberConst.ZERO) && NumberUtil.gt(station.getNearP(), NumberConst.ZERO)
					){
				station.setDiffP("999");
			}else if(NumberUtil.gt(station.getNearP(), NumberConst.ZERO)) {
				station.setDiffP((station.getSelfP().subtract(station.getNearP())).abs().divide(station.getNearP(), 2, NumberConst.MODE).toString());
			}else if(NumberUtil.gt(station.getSelfP(), NumberConst.ZERO)) {
				station.setDiffP((station.getNearP().subtract(station.getSelfP())).abs().divide(station.getSelfP(), 2, NumberConst.MODE).toString());
			}
			if (insertList.size() > 100) {
				insertBatch(insertList);
				insertList.clear();
			}
		}
		if (insertList.size() > 0) {
			insertBatch(insertList);
			insertList.clear();
		}
	}
}
