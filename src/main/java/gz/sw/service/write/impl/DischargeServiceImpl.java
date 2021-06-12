package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.DischargeDao;
import gz.sw.mapper.write.UnitLineDao;
import gz.sw.service.write.DischargeService;
import gz.sw.service.write.UnitLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DischargeServiceImpl implements DischargeService {
	
	@Autowired
	private DischargeDao dischargeDao;

	@Override
	public Model select(Integer id) {
		return dischargeDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return dischargeDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return dischargeDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return dischargeDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return dischargeDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(Integer page, Integer limit, String sttp, String stcd, String name) {
		return dischargeDao.selectList(page, limit, sttp, stcd, name);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return dischargeDao.selectListByStcd(stcd);
	}
}
