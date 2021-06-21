package gz.sw.service.write.impl;

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
	public int selectRainCount(String selfP, String diffP) {
		return stationDao.selectRainCount(selfP, diffP);
	}

	@Override
	public List selectRainList(Integer page, Integer limit, String selfP, String diffP) {
		return stationDao.selectRainList(page, limit, selfP, diffP);
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
			//			String date = "2018-06-01 03:00:00";
			List<Station> stationList = selectAll();
			List<Station> insertList = new ArrayList<>();
			List<String> stcdList = new ArrayList<>();
			List<Map> rainfallList = new ArrayList<>();
			clear();
			dbcc();
			for (Station station : stationList) {
				stcdList.add(station.getStcd());
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
				station.setDateP(date);
				insertList.add(station);
				if (station.getNearStcd() == null) {
					continue;
				}
				for (int i = 0; i < rainfallList.size(); i++) {
					String stcd1 = String.valueOf(rainfallList.get(i).get("stcd"));
					BigDecimal p1 = new BigDecimal(String.valueOf(rainfallList.get(i).get("p")));
					if (station.getStcd().equals(stcd1)) {
						station.setSelfP(p1);
						for (int j = 0; j < rainfallList.size(); j++) {
							String stcd2 = String.valueOf(rainfallList.get(j).get("stcd"));
							BigDecimal p2 = new BigDecimal(String.valueOf(rainfallList.get(j).get("p")));
							if (station.getNearStcd().equals(stcd2)) {
								BigDecimal diffP = NumberConst.ZERO;
								if (NumberUtil.gt(p1, NumberConst.ZERO) && NumberUtil.et(p2, NumberConst.ZERO) ||
									NumberUtil.et(p1, NumberConst.ZERO) && NumberUtil.gt(p2, NumberConst.ZERO)
								){
									diffP = new BigDecimal(999);
								} else if (NumberUtil.gt(p2, NumberConst.ZERO)) {
									diffP = (p1.subtract(p2)).abs().divide(p2, 2, NumberConst.MODE);
								}
								station.setNearP(p2);
								station.setDiffP(diffP.toString());
								break;
							}
						}
						break;
					}
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
}
