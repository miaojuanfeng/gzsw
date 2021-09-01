package gz.sw.service.write;

import gz.sw.entity.write.Discharge;
import gz.sw.entity.write.DischargePoint;
import gz.sw.entity.write.Model;
import gz.sw.entity.write.UnitLinePoint;

import java.util.List;

public interface DischargeService {
    Discharge select(Integer id);
    int insert(Discharge discharge);
    int insertPointBatch(List<DischargePoint> dischargePoints);
    int update(Discharge discharge);
    int delete(Integer id);
    int deletePoint(Integer lid);

    int selectCount(String sttp, String stcd, String name);
    List selectList(Integer page, Integer limit, String sttp, String stcd, String name);
    List selectListByStcd(String stcd);
    List selectPointList(Integer lid);
}
