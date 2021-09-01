package gz.sw.service.read2.impl;

import gz.sw.entity.read.Rainfall;
import gz.sw.mapper.read.RainfallDao;
import gz.sw.mapper.read2.InitDao;
import gz.sw.service.read.RainfallService;
import gz.sw.service.read2.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private InitDao initDao;

    @Override
    public Map selectInit(List<String> list, String affectTime) {
        return initDao.selectInit(list, affectTime);
    }
}
