package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Grid;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.mapper.write.GridDao;
import gov.gz.hydrology.mapper.write.StationDao;
import gov.gz.hydrology.service.write.GridService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GridServiceImpl implements GridService {
	
	@Autowired
	private GridDao gridDao;

	@Override
	public List<Grid> selectByStcd(String stcd) {
		return gridDao.selectByStcd(stcd);
	}
}
