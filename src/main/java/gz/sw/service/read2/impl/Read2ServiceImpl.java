package gz.sw.service.read2.impl;

import gz.sw.mapper.read2.Read2Dao;
import gz.sw.service.read2.Read2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Read2ServiceImpl implements Read2Service {

    @Autowired
    private Read2Dao read2Dao;

    @Override
    public Map selectInit(List<String> list, String affectTime) {
        return read2Dao.selectInit(list, affectTime);
    }

    @Override
    public List<Map> selectGridStation() {
        return read2Dao.selectGridStation();
    }
}
