package gz.sw.mapper.read;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZqDao {
    List selectZq(@Param("stcd") String stcd);
}
