package gz.sw.service.read;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReadService {

	List selectStbprpList();

	List selectZvarlList(String stcd, String date);

	Map selectInitData(String stcd, String date, String year, String monthDay);

	List<Map> selectInqList(String stcd, String forecastTime, String affectTime);
}
