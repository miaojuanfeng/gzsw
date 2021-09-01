package gz.sw.service.write.impl;

import gz.sw.entity.write.Discharge;
import gz.sw.entity.write.DischargePoint;
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
	public Discharge select(Integer id) {
		return dischargeDao.select(id);
	}

	@Override
	public int insert(Discharge discharge) {
		return dischargeDao.insert(discharge);
	}

	@Override
	public int insertPointBatch(List<DischargePoint> dischargePoints) {
		return dischargeDao.insertPointBatch(dischargePoints);
	}

	@Override
	public int update(Discharge discharge) {
		return dischargeDao.update(discharge);
	}

	@Override
	public int delete(Integer id) {
		return dischargeDao.delete(id);
	}

	@Override
	public int deletePoint(Integer lid) {
		return dischargeDao.deletePoint(lid);
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

	@Override
	public List selectPointList(Integer lid) {
		return dischargeDao.selectPointList(lid);
	}
}
