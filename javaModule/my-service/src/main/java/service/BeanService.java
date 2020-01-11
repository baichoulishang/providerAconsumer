package service;

import api.IBeanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Role;

/**
 * @author 陈宜康
 * @date 2019/9/18 20:33
 * @forWhat
 */
@Service
public class BeanService implements IBeanService {
    @Override
    // Bean注解和@Transactional不能同时存在!!!
    // @Bean(name = "beanRole")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3)
    public Role getRole() {
        Role role = new Role();
        role.setId("20190918203543");
        role.setRolename("自定义角色");
        role.setNote("今天挺累的,但是我还行");
        return role;
    }
}
