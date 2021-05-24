package gz.sw.mapper.read;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZvarlDao {
    List selectList(@Param("stcd") String stcd, @Param("date") String date);
}
