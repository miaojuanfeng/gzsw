package gz.sw.service.read2;

import gz.sw.entity.read.Rainfall;

import java.util.List;
import java.util.Map;

public interface InitService {
	Map selectInit(List<String> list, String affectTime);
}
