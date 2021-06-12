package gz.sw.service.write;

import gz.sw.entity.write.Station;

import java.util.List;

public interface StationService {
	int selectCount(String sttp, String stcd, String name);
	List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
	int selectRainCount(String sttp, String stcd, String name);
	List selectRainList(Integer page, Integer limit, String sttp, String stcd, String name);
	List selectListByType(String type);
	List selectAll();
	int insertBatch(List<Station> stationList);
	void clear();
	void dbcc();
	void unusual();
}
