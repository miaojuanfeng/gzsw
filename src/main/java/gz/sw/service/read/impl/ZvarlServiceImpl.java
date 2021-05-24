package gz.sw.service.read.impl;

import gz.sw.mapper.read.ZvarlDao;
import gz.sw.service.read.ZvarlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZvarlServiceImpl implements ZvarlService {

    @Autowired
    private ZvarlDao zvarlDao;

    @Override
    public List selectList(String stcd, String date){
        return zvarlDao.selectList(stcd, date);
    }

}
