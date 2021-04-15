package gz.sw.service.read;

import gz.sw.entity.read.River;

import java.util.List;

public interface RiverService {
	List<River> selectRiverTime(String stcd, String statDay, String endDay);
	List<River> selectRiverQRange(String stcd, String forecastTime, String affectTime);
	List<River> selectRiverZRange(String stcd, String forecastTime, String affectTime);
	River selectRiverLast(String stcd);
}
