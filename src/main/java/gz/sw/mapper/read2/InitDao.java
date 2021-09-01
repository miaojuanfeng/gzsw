package gz.sw.mapper.read2;

import gz.sw.entity.read.Rainfall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InitDao {
    Map selectInit(@Param("list") List list, @Param("affectTime") String affectTime);
}
