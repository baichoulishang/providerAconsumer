package com.test;

import api.IBeanService;
import api.IMyProxy;
import com.utils.FreeProxy;
import com.utils.SqlSessionFactoryUtils;
import mapper.RoleMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Role;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author 陈宜康
 * @date 2019/9/8 8:52
 * @forWhat
 */
public class SuperUtil {


    private static final String ROLENAMEPARAM = "%baichoulishang%";

    public static void main(String[] args) throws IOException {
        testServiceBean();

    }


    public static void testServiceBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
        // Object holyshit = context.getBean("beanRole");
        // System.out.println(holyshit);
        IBeanService bean = context.getBean(IBeanService.class);
        bean.getRole();
    }

    public static void testSpringIoC() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
        Object holyshit = context.getBean("holyshit");
        System.out.println(holyshit);
        // IRoleService roleService = context.getBean(IRoleService.class);
        // roleService.print();
    }

    public static void testProxy() {
        IMyProxy proxy = (IMyProxy) Proxy.newProxyInstance(IMyProxy.class.getClassLoader(), new Class[]{IMyProxy.class}, new FreeProxy());
        proxy.check();
    }

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
