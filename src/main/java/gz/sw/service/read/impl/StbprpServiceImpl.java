package gz.sw.service.read.impl;

import gz.sw.mapper.read.StbprpDao;
import gz.sw.service.read.StbprpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StbprpServiceImpl implements StbprpService {

    @Autowired
    private StbprpDao stbprpDao;

    @Override
    public List selectList(){
        return stbprpDao.selectList();
    }

}
