package gz.sw.service.write;

import gz.sw.entity.write.Grid;

import java.util.List;

public interface GridService {
	List<Grid> selectByStcd(String stcd);
}
