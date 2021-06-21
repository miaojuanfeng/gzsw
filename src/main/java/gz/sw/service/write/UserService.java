package gz.sw.service.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User select(Integer id);
    int insert(User user);
    int update(User user);
    int delete(Integer id);

    int selectCount(String phone, String name);
    List selectList(String phone, String name, Integer page, Integer limit);
    User selectByPhone(String phone);
}
