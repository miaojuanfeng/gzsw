package gz.sw.service.read3.impl;

import gz.sw.mapper.read3.ZqDao;
import gz.sw.service.read3.ZqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZqServiceImpl implements ZqService {

    @Autowired
    private ZqDao zqDao;

    @Override
    public String selectYear(String stcd) {
        return zqDao.selectYear(stcd);
    }

    @Override
    public List selectZq(String stcd, String year) {
        return zqDao.selectZq(stcd, year);
    }
}
