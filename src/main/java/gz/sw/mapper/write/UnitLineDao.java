package gz.sw.mapper.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.UnitLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UnitLineDao {
	UnitLine select(Integer id);
	int insert(UnitLine unitLine);
	int update(UnitLine unitLine);
	int delete(Integer id);

	int selectCount(@Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
    List selectList(@Param("page") Integer page, @Param("limit") Integer limit, @Param("sttp") String sttp, @Param("stcd") String stcd, @Param("name") String name);
	List selectListByStcd(String stcd);
	List selectLinePoint(Integer id);
	List selectPointList(Integer lid);
}
