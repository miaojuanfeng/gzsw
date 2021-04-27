package gz.sw.mapper.write;

import gz.sw.entity.write.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlanDao {
	Plan select(Integer id);
	Map selectMap(Integer id);
	int insert(Plan plan);
	int insertBatch(List<Plan> plans);
	int update(Plan plan);
	int delete(Integer id);

	int selectCount(@Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectListByStcd(@Param("stcd") String stcd);
}
