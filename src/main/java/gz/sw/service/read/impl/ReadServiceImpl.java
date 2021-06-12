package gz.sw.service.read.impl;

import gz.sw.mapper.read.ReadDao;
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
    public List selectStbprpList(){
        return readDao.selectStbprpList();
    }

    @Override
    public List selectZvarlList(String stcd, String date){
        return readDao.selectZvarlList(stcd, date);
    }

    @Override
    public Map selectInitData(String stcd, String date, String year, String monthDay) {
        return readDao.selectInitData(stcd, date, year, monthDay);
    }

}
