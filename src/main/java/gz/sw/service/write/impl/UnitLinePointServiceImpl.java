package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.UnitLineDao;
import gz.sw.mapper.write.UnitLinePointDao;
import gz.sw.service.write.UnitLinePointService;
import gz.sw.service.write.UnitLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitLinePointServiceImpl implements UnitLinePointService {
	
	@Autowired
	private UnitLinePointDao unitLinePointDao;

	@Override
	public Model select(Integer id) {
		return unitLinePointDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return unitLinePointDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return unitLinePointDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return unitLinePointDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return unitLinePointDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return unitLinePointDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return unitLinePointDao.selectListByStcd(stcd);
	}

	@Override
	public List selectAll() {
		return unitLinePointDao.selectAll();
	}
}
