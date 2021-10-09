package gz.sw.service.write.impl;

import gz.sw.entity.write.Rain;
import gz.sw.entity.write.RainPoint;
import gz.sw.entity.write.Rainf;
import gz.sw.entity.write.RainfPoint;
import gz.sw.mapper.write.RainDao;
import gz.sw.mapper.write.RainfDao;
import gz.sw.service.write.RainService;
import gz.sw.service.write.RainfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RainfServiceImpl implements RainfService {
	
	@Autowired
	private RainfDao rainfDao;

	@Override
	public Rainf select(Integer id) {
		return rainfDao.select(id);
	}

	@Override
	public Map selectMap(Integer id) {
		return rainfDao.selectMap(id);
	}

	@Override
	public int insert(Rainf rainfArea) {
		return rainfDao.insert(rainfArea);
	}

	@Override
	public int insertPointList(List<Map> list) {
		return rainfDao.insertPointList(list);
	}

	@Override
	public int insertPointBatch(List<RainfPoint> rainfPoints) {
		return rainfDao.insertPointBatch(rainfPoints);
	}

	@Override
	public int update(Rainf rainfArea) {
		return rainfDao.update(rainfArea);
	}

	@Override
	public int delete(Integer id) {
		return rainfDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return rainfDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return rainfDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return rainfDao.selectListByStcd(stcd);
	}

	@Override
	public List selectRainfPoint(Integer rain) {
		return rainfDao.selectRainfPoint(rain);
	}

	@Override
	public int deleteListById(Integer id) {
		return rainfDao.deleteListById(id);
	}
}
