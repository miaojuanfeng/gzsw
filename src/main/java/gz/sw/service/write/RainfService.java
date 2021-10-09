package gz.sw.service.write;

import gz.sw.entity.write.Rain;
import gz.sw.entity.write.RainPoint;
import gz.sw.entity.write.Rainf;
import gz.sw.entity.write.RainfPoint;

import java.util.List;
import java.util.Map;

public interface RainfService {
    Rainf select(Integer id);
    Map selectMap(Integer id);
    int insert(Rainf rainf);
    int insertPointList(List<Map> list);
    int insertPointBatch(List<RainfPoint> rainfPoints);
    int update(Rainf rainf);
    int delete(Integer id);

    int selectCount(String sttp, String stcd, String name);
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd);
    List selectRainfPoint(Integer rainf);
    int deleteListById(Integer id);
}
