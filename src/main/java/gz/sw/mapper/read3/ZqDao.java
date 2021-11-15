package gz.sw.mapper.read3;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZqDao {
    String selectYear(@Param("stcd") String stcd);
    List selectZq(@Param("stcd") String stcd, @Param("year") String year);
}
