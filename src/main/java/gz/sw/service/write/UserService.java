package gz.sw.service.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User select(Integer id);
    int insert(Model model);
    int update(Model model);
    int delete(Integer id);

    int selectCount();
    List selectList(Integer page, Integer limit);
    User selectByPhone(String phone);
}
