package gz.sw.mapper.write;

import gz.sw.entity.write.Discharge;
import gz.sw.entity.write.DischargePoint;
import gz.sw.entity.write.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DischargeDao {
	Discharge select(Integer id);
	int insert(Discharge discharge);
	int insertPointBatch(List<DischargePoint> dischargePoints);
	int update(Discharge discharge);
	int delete(Integer id);
	int deletePoint(Integer lid);

	int selectCount(@Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
    List selectList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectListByStcd(String stcd);
	List selectLinePoint(Integer id);
	List selectPointList(Integer lid);
}
