package gz.sw.service.write.impl;

import gz.sw.entity.write.User;
import gz.sw.mapper.write.UserDao;
import gz.sw.service.write.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User select(Integer id) {
		return userDao.select(id);
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int delete(Integer id) {
		return userDao.delete(id);
	}

	@Override
	public int selectCount(String phone, String name) {
		return userDao.selectCount(phone, name);
	}

	@Override
	public List selectList(String phone, String name, Integer page, Integer limit) {
		return userDao.selectList(phone, name, page, limit);
	}

	@Override
	public User selectByPhone(String phone) {
		return userDao.selectByPhone(phone);
	}

}
