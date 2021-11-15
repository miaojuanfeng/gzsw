package gz.sw.mapper.write;

import gz.sw.entity.write.ModelStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelStationDao {
	List selectByModel(Integer modelId);
	List selectByPlan(Integer planId);
	int selectCountByPlan(Integer planId);
	int deleteByModel(Integer modelId);
	int insertBatch(List<ModelStation> modelStations);
}
