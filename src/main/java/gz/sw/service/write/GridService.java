package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Grid;
import gov.gz.hydrology.entity.write.Station;

import java.util.List;
import java.util.Map;

public interface GridService {
	List<Grid> selectByStcd(String stcd);
}
