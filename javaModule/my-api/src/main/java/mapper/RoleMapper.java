package mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pojo.Role;

import java.util.List;

//表示dao层
@Repository
public interface RoleMapper {
    int insertRole(Role role);

    int deleteRole(Role role);

    int updateRole(Role role);

    Role getRole(@Param("role") Role role);

    List<Role> getRoles(String roleName);

}
