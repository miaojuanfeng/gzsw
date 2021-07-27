package gz.sw.service.write;

import gz.sw.entity.write.Model;
import gz.sw.entity.write.UnitLine;

import java.util.List;

public interface UnitLineService {
    UnitLine select(Integer id);
    int insert(UnitLine unitLine);
    int update(UnitLine unitLine);
    int delete(Integer id);

    int selectCount(String sttp, String stcd, String name);
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd);
    List selectLinePoint(Integer id);
    List selectPointList(Integer lid);
}
