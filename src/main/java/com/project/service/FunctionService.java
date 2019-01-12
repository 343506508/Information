package com.project.service;

import com.project.component.FunctionComponent;
import com.project.component.UserRoleComponent;
import com.project.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2017/12/28.
 * User : 是Service
 */
@Service
public class FunctionService extends BaseService {
    //BaseService中是所有的Dao 在UserService中直接用皆可.

    //查出pid=0的(父菜单)
    public List<FunctionComponent> getAllParent(Integer roleid){
        return functionRepository.getAllParent(roleid);
    }

    //子菜单
    public List<FunctionComponent> getAllByPid(Integer pid, Integer roleid){
        return functionRepository.getAllByPid(pid,roleid);
    }

    //所有菜单
    public List<FunctionComponent> queryRoleAllFunction(){
        return functionRepository.queryRoleAllFunction();
    }

    //当前登录人的菜单(Menu)
    public List<FunctionComponent> functionLoginList(Integer roleid){
        return functionRepository.functionLoginList(roleid);
    }

    //删除角色_URL的关联
    public void deleteMenuByRoleId(String roleid) {
        functionRepository.deleteMenuByRoleId(roleid);
    }

    //添加角色_URL的关联
    public Integer insertUserRoleMenu(UserRoleComponent userRole) {
        return functionRepository.insertUserRoleMenu(userRole);
    }
}
