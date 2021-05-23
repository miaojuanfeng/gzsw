package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.RainRunPointDao;
import gz.sw.mapper.write.UnitLineDao;
import gz.sw.service.write.RainRunPointService;
import gz.sw.service.write.UnitLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RainRunPointServiceImpl implements RainRunPointService {
	
	@Autowired
	private RainRunPointDao rainRunPointDao;

	@Override
	public Model select(Integer id) {
		return rainRunPointDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return rainRunPointDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return rainRunPointDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return rainRunPointDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return rainRunPointDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return rainRunPointDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return rainRunPointDao.selectListByStcd(stcd);
	}

	@Override
	public List selectAll() {
		return rainRunPointDao.selectAll();
	}
}
