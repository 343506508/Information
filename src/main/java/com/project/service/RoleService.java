package com.project.service;

import com.project.component.RoleComponent;
import com.project.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2017/12/28.
 */
@Service
public class RoleService extends BaseService {

    public RoleComponent getRoleById(Integer roleId){
        return roleRepository.getRoleById(roleId);
    }
}
