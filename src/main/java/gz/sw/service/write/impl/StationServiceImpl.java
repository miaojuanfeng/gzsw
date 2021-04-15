package gz.sw.service.write.impl;

import gz.sw.entity.write.Station;
import gz.sw.mapper.write.StationDao;
import gz.sw.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationDao stationDao;

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return stationDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return stationDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByType(String type) {
		return stationDao.selectListByType(type);
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
}
