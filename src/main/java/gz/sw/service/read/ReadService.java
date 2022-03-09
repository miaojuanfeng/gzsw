package gz.sw.service.read;

import java.util.List;
import java.util.Map;

public interface ReadService {

	Map selectInitData(String stcd, String date, String year, String monthDay);

	List<Map> selectRsvrList(String stcd, String forecastTime, String affectTime);
}
