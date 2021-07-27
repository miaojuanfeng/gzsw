package gz.sw.service.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.RainRun;

import java.util.List;

public interface RainRunService {
    RainRun select(Integer id);
    int insert(RainRun rainRun);
    int update(RainRun rainRun);
    int delete(Integer id);

    int selectCount(String sttp, String stcd, String name);
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd);
    List selectRainRunPoint(Integer rainRun);
    List selectPointList(Integer pid);
}
