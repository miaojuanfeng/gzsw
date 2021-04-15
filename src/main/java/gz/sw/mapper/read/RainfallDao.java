package gz.sw.mapper.read;

import gz.sw.entity.read.Rainfall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RainfallDao {
    List<Rainfall> selectRainfallTotal(@Param("list") List list, @Param("startDay") String startDay, @Param("endDay") String endDay);
    List<Rainfall> selectRainfallDaily(@Param("list") List list, @Param("startDay") String startDay, @Param("endDay") String endDay);
    List<Rainfall> selectRainfallRange(@Param("list") List list, @Param("forecastTime") String forecastTime, @Param("affectTime") String affectTime);
}
