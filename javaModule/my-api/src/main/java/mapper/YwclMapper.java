package mapper;


import org.springframework.stereotype.Repository;
import pojo.YwclCaseVO;

import java.util.List;

//表示dao层
@Repository
public interface YwclMapper {

    List<YwclCaseVO> getYwclList(String ldzl);
}
