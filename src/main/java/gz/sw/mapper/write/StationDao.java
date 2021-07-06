package gz.sw.mapper.write;

import gz.sw.entity.write.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StationDao {
	int selectCount(@Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	int selectRainCount(@Param("selfP") String selfP, @Param("diffP") String diffP, @Param("stcd") String stcd);
	List selectRainList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("selfP") String selfP, @Param("diffP") String diffP, @Param("stcd") String stcd);
	List selectListByType(String type);
	List selectAll();
	int insertBatch(List<Station> stationList);
	void clear();
	void dbcc();
}
