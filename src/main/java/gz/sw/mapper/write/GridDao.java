package gz.sw.mapper.write;

import gz.sw.entity.write.Grid;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GridDao {
	List<Grid> selectByStcd(@Param("stcd") String stcd);
}
