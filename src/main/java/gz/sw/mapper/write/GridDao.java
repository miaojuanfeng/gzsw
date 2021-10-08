package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Grid;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GridDao {
	List<Grid> selectByStcd(@Param("stcd") String stcd);
}
