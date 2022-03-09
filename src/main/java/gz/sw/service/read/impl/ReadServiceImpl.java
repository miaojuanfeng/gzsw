package gz.sw.service.read.impl;

import gz.sw.mapper.read.ReadDao;
import gz.sw.mapper.read3.Read3Dao;
import gz.sw.service.read.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReadServiceImpl implements ReadService {

    @Autowired
    private ReadDao readDao;

    @Override
    public Map selectInitData(String stcd, String date, String year, String monthDay) {
        return readDao.selectInitData(stcd, date, year, monthDay);
    }

    @Override
    public List<Map> selectRsvrList(String stcd, String forecastTime, String affectTime) {
        return readDao.selectRsvrList(stcd, forecastTime, affectTime);
    }

}
