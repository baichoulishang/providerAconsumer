package api;

import pojo.Role;

import java.util.List;

/**
 * @author 陈宜康
 * @date 2019/9/15 9:40
 * @forWhat
 */
public interface IRoleService {
    void print();

    void insertRole();

    List<Role> getList(String id);
}
