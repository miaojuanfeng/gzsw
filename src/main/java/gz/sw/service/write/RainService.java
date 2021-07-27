package gz.sw.service.write;

import gz.sw.entity.write.Rain;

import java.util.List;
import java.util.Map;

public interface RainService {
    Rain select(Integer id);
    Map selectMap(Integer id);
    int insert(Rain rain);
    int insertPointList(List<Map> list);
    int update(Rain rain);
    int delete(Integer id);

    int selectCount(String sttp, String stcd, String name);
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd);
    List selectRainPoint(Integer rain);
    int deleteListById(Integer id);
}
