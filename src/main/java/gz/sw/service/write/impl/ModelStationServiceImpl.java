package gz.sw.service.write.impl;

import gz.sw.entity.write.ModelStation;
import gz.sw.mapper.write.ModelStationDao;
import gz.sw.service.write.ModelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelStationServiceImpl implements ModelStationService {
	
	@Autowired
	private ModelStationDao modelStationDao;

	@Override
	public List selectByModel(Integer modelId) {
		return modelStationDao.selectByModel(modelId);
	}

	@Override
	public int selectCountByPlan(Integer planId) {
		return modelStationDao.selectCountByPlan(planId);
	}

	@Override
	public int deleteByModel(Integer modelId) {
		return modelStationDao.deleteByModel(modelId);
	}

	@Override
	public int insertBatch(List<ModelStation> modelStations) {
		return modelStationDao.insertBatch(modelStations);
	}
}
