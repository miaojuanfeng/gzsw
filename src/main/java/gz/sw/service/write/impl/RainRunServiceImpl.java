package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.RainRunDao;
import gz.sw.service.write.RainRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RainRunServiceImpl implements RainRunService {
	
	@Autowired
	private RainRunDao rainRunDao;

	@Override
	public Model select(Integer id) {
		return rainRunDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return rainRunDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return rainRunDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return rainRunDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return rainRunDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return rainRunDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return rainRunDao.selectListByStcd(stcd);
	}

	@Override
	public List selectRainRunPoint(Integer rainRun) {
		return rainRunDao.selectRainRunPoint(rainRun);
	}

	@Override
	public List selectPointList(Integer pid) {
		return rainRunDao.selectPointList(pid);
	}
}
