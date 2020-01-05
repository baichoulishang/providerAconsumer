package com.bean.methodBean;

import com.pojo.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 陈宜康
 * @date 2019/9/13 20:30
 * @forWhat
 */
@Component
public class MethodBean {
    @Bean(name = "holyshit")
    public Role getRole() {
        Role role = new Role();
        role.setId("11122");
        role.setRolename("fsdfsdfds");
        role.setNote("fffff");
        return role;
    }
}
