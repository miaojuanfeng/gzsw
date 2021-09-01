package gz.sw.mapper.write;

import gz.sw.entity.write.RainRun;
import gz.sw.entity.write.RainRunPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RainRunDao {
	RainRun select(Integer id);
	int insert(RainRun rainRun);
	int insertLineBatch(List<RainRun> rainRuns);
	int insertPointBatch(List<RainRunPoint> rainRunPoints);
	int update(RainRun rainRun);
	int delete(Integer id);
	int deleteById(Integer pid);

	int selectCount(@Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
    List selectList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectListByStcd(String stcd);
	List selectRainRunPoint(Integer rainRun);
	List selectPointList(Integer pid);
}
