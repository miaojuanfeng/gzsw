package gz.sw.service.read.impl;

import gz.sw.entity.read.Zq;
import gz.sw.mapper.read.ZqDao;
import gz.sw.service.read.ZqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ZqServiceImpl implements ZqService {

    @Autowired
    private ZqDao zqDao;

    @Override
    public List selectZq(String stcd) {
        return zqDao.selectZq(stcd);
    }
}
