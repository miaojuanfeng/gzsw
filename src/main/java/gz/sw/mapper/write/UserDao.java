package gz.sw.mapper.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
	User select(Integer id);
	int insert(User user);
	int update(User user);
	int delete(Integer id);

	int selectCount(@Param("phone") String phone, @Param("name") String name);
    List selectList(@Param("phone") String phone, @Param("name") String name, @Param("page") Integer page, @Param("limit") Integer limit);
	User selectByPhone(@Param("phone") String phone);
}
