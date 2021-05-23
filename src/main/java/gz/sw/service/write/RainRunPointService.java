package gz.sw.service.write;

import gz.sw.entity.write.Model;

import java.util.List;

public interface RainRunPointService {
    Model select(Integer id);
    int insert(Model model);
    int update(Model model);
    int delete(Integer id);

    int selectCount(String sttp, String stcd, String name);
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd);
    List selectAll();
}
