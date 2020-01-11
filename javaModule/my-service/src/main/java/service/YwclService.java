package service;

import api.IYwclService;
import mapper.YwclMapper;
import org.springframework.stereotype.Service;
import pojo.YwclCaseVO;

import javax.annotation.Resource;
import java.util.List;

@Service
// @Component
public class YwclService implements IYwclService {

    //自动装配mapper
    @Resource
    public YwclMapper ywclMapper;

    @Override
    public List<YwclCaseVO> getList(String ldzl) {
        List<YwclCaseVO> ywclList = ywclMapper.getYwclList(ldzl);
        return ywclList;
    }
}
