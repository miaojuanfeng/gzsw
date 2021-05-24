package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.UnitLineDao;
import gz.sw.service.write.UnitLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitLineServiceImpl implements UnitLineService {
	
	@Autowired
	private UnitLineDao unitLineDao;

	@Override
	public Model select(Integer id) {
		return unitLineDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return unitLineDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return unitLineDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return unitLineDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return unitLineDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return unitLineDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return unitLineDao.selectListByStcd(stcd);
	}

	@Override
	public List selectLinePoint(Integer id) {
		return unitLineDao.selectLinePoint(id);
	}

	@Override
	public List selectPointList(Integer lid) {
		return unitLineDao.selectPointList(lid);
	}
}
