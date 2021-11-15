package gz.sw.service.write;

import gz.sw.entity.write.ModelStation;

import java.util.List;

public interface ModelStationService {
    List selectByModel(Integer modelId);
    List selectByPlan(Integer planId);
    int selectCountByPlan(Integer planId);
    int deleteByModel(Integer modelId);
    int insertBatch(List<ModelStation> modelStations);
}
