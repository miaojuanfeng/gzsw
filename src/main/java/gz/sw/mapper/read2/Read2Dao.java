package gz.sw.mapper.read2;

import gz.sw.entity.read.Rainfall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface Read2Dao {
    Map selectInit(@Param("list") List list, @Param("affectTime") String affectTime);
    List<Map> selectGridStation();
    List<Map> selectGridPoint(@Param("list") List list, @Param("fymdh") String fymdh, @Param("unit") Integer unit, @Param("startDay") String startDay, @Param("endDay") String endDay);
}
