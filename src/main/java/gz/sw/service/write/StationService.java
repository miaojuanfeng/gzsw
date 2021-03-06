package gz.sw.service.write;

import gz.sw.entity.write.Station;

import java.util.List;

public interface StationService {
	int selectCount(String sttp, String stcd, String name);
	List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
	int selectRainCount(String selfP, String diffP, String stcd);
	List selectRainList(Integer page, Integer limit, String selfP, String diffP, String stcd);
	List selectListByType(String type);
	List selectAll();
	List selectRain();
	int insertBatch(List<Station> stationList);
	void clear();
	void dbcc();
	void unusual();
	void unusual(String date);
}
