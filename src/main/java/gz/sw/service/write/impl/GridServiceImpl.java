package gz.sw.service.write.impl;

import gz.sw.entity.write.Grid;
import gz.sw.mapper.write.GridDao;
import gz.sw.service.write.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridServiceImpl implements GridService {
	
	@Autowired
	private GridDao gridDao;

	@Override
	public List<Grid> selectByStcd(String stcd) {
		return gridDao.selectByStcd(stcd);
	}
}
