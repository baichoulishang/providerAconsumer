package com.mapper;


import com.pojo.YwclCaseVO;
import org.springframework.stereotype.Repository;

import java.util.List;

//表示dao层
@Repository
public interface YwclMapper {

    List<YwclCaseVO> getYwclList(String ldzl);
}
