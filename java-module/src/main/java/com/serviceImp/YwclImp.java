package com.serviceImp;

import com.mapper.YwclMapper;
import com.pojo.YwclCaseVO;
import com.ssm.service.YwclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public class YwclImp implements YwclService {

    //自动装配mapper
    @Autowired
    public YwclMapper ywclMapper;

    @Override
    public List<YwclCaseVO> getList(String ldzl) {
        List<YwclCaseVO> ywclList = ywclMapper.getYwclList(ldzl);
        return ywclList;
    }
}
