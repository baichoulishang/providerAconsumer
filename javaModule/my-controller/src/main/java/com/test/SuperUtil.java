package com.test;

import com.utils.SqlSessionFactoryUtils;
import mapper.RoleMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Role;

import java.io.IOException;

/**
 * @author 陈宜康
 * @date 2019/9/8 8:52
 * @forWhat
 */
public class SuperUtil {


    private static final String ROLENAMEPARAM = "%baichoulishang%";


    public static void testInsert() {
        Role role = new Role();
        role.setId("20190908210843");
        role.setNote("dddd");
        role.setRolename("bai");
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        int i = mapper.insertRole(role);
        sqlSession.commit();
        System.out.println("影响的行数是:" + i);

        sqlSession.close();
    }

}
