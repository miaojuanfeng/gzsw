package gz.sw.service.write.impl;

import gz.sw.entity.write.Plan;
import gz.sw.mapper.write.PlanDao;
import gz.sw.service.write.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanDao planDao;


	@Override
	public Plan select(Integer id) {
		return planDao.select(id);
	}

	@Override
	public Map selectMap(Integer id) {
		return planDao.selectMap(id);
	}

	@Override
	public int insert(Plan plan) {
		return planDao.insert(plan);
	}

	@Override
	public int insertBatch(List<Plan> plans) {
		return planDao.insertBatch(plans);
	}

	@Override
	public int update(Plan plan) {
		return planDao.update(plan);
	}

	@Override
	public int delete(Integer id) {
		return planDao.delete(id);
	}

	@Override
	public int selectCount(String sttp, String stcd, String name) {
		return planDao.selectCount(sttp, stcd, name);
	}

	@Override
	public List selectList(String sttp, String stcd, String name, Integer page, Integer limit) {
		return planDao.selectList(sttp, stcd, name, page, limit);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return planDao.selectListByStcd(stcd);
	}
}
