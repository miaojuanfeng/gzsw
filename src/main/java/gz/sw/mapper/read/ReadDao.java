package gz.sw.mapper.read;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadDao {

    List selectStbprpList();

    List selectZvarlList(@Param("stcd") String stcd, @Param("date") String date);

    Map selectInitData(@Param("stcd") String stcd, @Param("date") String date, @Param("year") String year, @Param("monthDay") String monthDay);
}
