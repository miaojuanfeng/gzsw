package gz.sw.service.write;

import gz.sw.entity.write.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {
    Plan select(Integer id);
    Map selectMap(Integer id);
    int insert(Plan plan);
    int insertBatch(List<Plan> plans);
    int update(Plan plan);
    int delete(Integer id);

    int selectCount(String sttp, String stcd, String name);
    List selectList(String sttp, String stcd, String name, Integer page, Integer limit);
    List selectListByStcd(String stcd);
}
