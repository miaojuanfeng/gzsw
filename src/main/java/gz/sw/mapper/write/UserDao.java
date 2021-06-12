package gz.sw.mapper.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
	User select(Integer id);
	int insert(Model model);
	int update(Model model);
	int delete(Integer id);

	int selectCount();
    List selectList(@Param("page") Integer page, @Param("limit") Integer limit);
	User selectByPhone(@Param("phone") String phone);
}
