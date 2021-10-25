package gz.sw.service.read3;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Read3Service {

	List selectStbprpList();

	List selectZvarlList(String stcd, String date);
}
