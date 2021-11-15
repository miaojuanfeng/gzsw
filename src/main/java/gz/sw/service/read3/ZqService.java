package gz.sw.service.read3;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZqService {
	String selectYear(String stcd);
	List selectZq(String stcd, String year);
}
