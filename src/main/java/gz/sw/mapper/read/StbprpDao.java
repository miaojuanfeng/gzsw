package gz.sw.mapper.read;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StbprpDao {
    List selectList();
}
