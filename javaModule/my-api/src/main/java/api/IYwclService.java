package api;

import pojo.YwclCaseVO;

import java.util.List;

public interface IYwclService {

    List<YwclCaseVO> getList(String ldzl);
}
