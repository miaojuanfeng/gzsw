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
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd, Integer model);
}
