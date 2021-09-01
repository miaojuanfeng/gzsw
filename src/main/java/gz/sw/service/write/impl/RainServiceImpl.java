package gz.sw.service.write.impl;

import gz.sw.entity.write.Rain;
import gz.sw.entity.write.RainPoint;
import gz.sw.mapper.write.RainDao;
import gz.sw.service.write.RainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RainServiceImpl implements RainService {
	
	@Autowired
	private RainDao rainDao;

	@Override
	public Rain select(Integer id) {
		return rainDao.select(id);
	}

	@Override
	public Map selectMap(Integer id) {
		return rainDao.selectMap(id);
	}

	@Override
	public int insert(Rain rainArea) {
		return rainDao.insert(rainArea);
	}

	@Override
	public int insertPointList(List<Map> list) {
		return rainDao.insertPointList(list);
	}

	@Override
	public int insertPointBatch(List<RainPoint> rainPoints) {
		return rainDao.insertPointBatch(rainPoints);
	}

	@Override
	public int update(Rain rainArea) {
		return rainDao.update(rainArea);
	}

	@Override
	public int delete(Integer id) {
		return rainDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return rainDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return rainDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return rainDao.selectListByStcd(stcd);
	}

	@Override
	public List selectRainPoint(Integer rain) {
		return rainDao.selectRainPoint(rain);
	}

	@Override
	public int deleteListById(Integer id) {
		return rainDao.deleteListById(id);
	}
}
