package gz.sw.service.write;

import gz.sw.entity.write.Model;

import java.util.List;

public interface UserService {
    Model select(Integer id);
    int insert(Model model);
    int update(Model model);
    int delete(Integer id);

    int selectCount();
    List selectList(Integer page, Integer limit);
    List selectListByStcd(String stcd);
}
