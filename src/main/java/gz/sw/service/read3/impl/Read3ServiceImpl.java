package gz.sw.service.read3.impl;

import gz.sw.mapper.read3.Read3Dao;
import gz.sw.service.read3.Read3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Read3ServiceImpl implements Read3Service {

    @Autowired
    private Read3Dao readDao;

    @Override
    public List selectStbprpList(){
        return readDao.selectStbprpList();
    }

    @Override
    public List selectZvarlList(String stcd, String date){
        return readDao.selectZvarlList(stcd, date);
    }

}
