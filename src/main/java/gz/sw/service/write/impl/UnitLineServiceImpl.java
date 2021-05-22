package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.ModelDao;
import gz.sw.service.write.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
	
	@Autowired
	private ModelDao modelDao;

	@Override
	public Model select(Integer id) {
		return modelDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return modelDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return modelDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return modelDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return modelDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return modelDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return modelDao.selectListByStcd(stcd);
	}

	@Override
	public List selectAll() {
		return modelDao.selectAll();
	}
}
