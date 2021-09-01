package gz.sw.mapper.write;

import gz.sw.entity.write.Rain;
import gz.sw.entity.write.RainPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RainDao {
	Rain select(Integer id);
	Map selectMap(Integer id);
	int insert(Rain rain);
	int insertPointList(List<Map> list);
	int insertPointBatch(List<RainPoint> rainPoints);
	int update(Rain rain);
	int delete(Integer id);

	int selectCount(@Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
    List selectList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectListByStcd(String stcd);
	List selectRainPoint(Integer rain);
	int deleteListById(Integer id);
}
