package gz.sw.service.read.impl;

import gz.sw.entity.read.Rainfall;
import gz.sw.mapper.read.RainfallDao;
import gz.sw.service.read.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RainfallServiceImpl implements RainfallService {

    @Autowired
    private RainfallDao rainfallDao;

    @Override
    public List<Rainfall> selectRainfallTotal(List<String> list, String startDay, String endDay) {
        return rainfallDao.selectRainfallTotal(list, startDay, endDay);
    }

    @Override
    public List<Rainfall> selectRainfallDaily(List<String> list, String startDay, String endDay) {
        return rainfallDao.selectRainfallDaily(list, startDay, endDay);
    }

    @Override
    public List<Rainfall> selectRainfallRange(List<String> list, String forecastTime, String affectTime) {
        return rainfallDao.selectRainfallRange(list, forecastTime, affectTime);
    }
}
