package gz.sw.service.read;

import gz.sw.entity.read.Rainfall;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RainfallService {
	List<Rainfall> selectRainfallTotal(List<String> list, String startDay, String endDay);
	List<Rainfall> selectRainfallDaily(List<String> list, String startDay, String endDay);
	List<Rainfall> selectRainfallRange(List<String> list, String forecastTime, String affectTime);
	List<Map> selectNearStationRainfall(List list, String date);
}
