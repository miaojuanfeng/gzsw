package gz.sw.service.write.impl;

import gz.sw.entity.write.Model;
import gz.sw.mapper.write.ModelDao;
import gz.sw.mapper.write.UserDao;
import gz.sw.service.write.ModelService;
import gz.sw.service.write.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Model select(Integer id) {
		return userDao.select(id);
	}

	@Override
	public int insert(Model model) {
		return userDao.insert(model);
	}

	@Override
	public int update(Model model) {
		return userDao.update(model);
	}

	@Override
	public int delete(Integer id) {
		return userDao.delete(id);
	}

	@Override
	public int selectCount() {
		return userDao.selectCount();
	}

	@Override
	public List selectList(Integer page, Integer limit) {
		return userDao.selectList(page, limit);
	}

	@Override
	public List selectListByStcd(String stcd) {
		return userDao.selectListByStcd(stcd);
	}
}
