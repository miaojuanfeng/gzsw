package gz.sw.mapper.read3;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface Read3Dao {

    List selectStbprpList();

    List selectZvarlList(@Param("stcd") String stcd, @Param("date") String date);
}
