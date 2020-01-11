package service;

import api.IRoleService;
import mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Role;

import java.util.Date;
import java.util.List;

/**
 * @author 陈宜康
 * @date 2020/1/10 21:34
 * @forWhat
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void print() {

    }

    @Override
    public void insertRole() {
        Role role = new Role();
        role.setId(new Date().toString());
        role.setNote("生命与思考");
        role.setRolename("奥利给");
        roleMapper.insertRole(role);
    }

    @Override
    public List<Role> getList(String id) {
        return null;
    }
}
