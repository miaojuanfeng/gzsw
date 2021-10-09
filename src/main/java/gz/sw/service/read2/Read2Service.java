package gz.sw.service.read2;

import gz.sw.entity.read.Rainfall;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Read2Service {
	Map selectInit(List<String> list, String affectTime);
	List<Map> selectGridStation();
	List<Map> selectGridPoint(List list, String fymdh, Integer unit, String startDay, String endDay);
}
