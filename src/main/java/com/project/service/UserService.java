package com.project.service;

import com.project.component.RoleComponent;
import com.project.component.UserComponent;
import com.project.service.base.BaseService;
import com.project.tools.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2017/12/28.
 * User : 是Service
 */
@Service
public class UserService extends BaseService {
    //BaseService中是所有的Dao 在UserService中直接用皆可.

    public UserComponent login(String loginName, String pwd){
        return userRepository.login(loginName,pwd);
    }

    //列表
    public Page<UserComponent> getListAll(String search, Integer pagesize, Integer count){
        return userRepository.getListAll(search,pagesize,count);
    }

    //添加前的角色列表
    public List<RoleComponent> getRoleList(){
        return userRepository.getRoleList();
    }

    //添加
    public Integer addUser(UserComponent user){
        return userRepository.addUser(user);
    }

    //修改
    public Integer updateUser(UserComponent user){
        return userRepository.updateUser(user);
    }

    //删除(真)
    public Integer goDelete(Integer id) {
        return userRepository.goDelete(id);
    }

    //导入前 , 判断是否存在用户
    public int getphoneCount(String number) {
        return  userRepository.getphoneCount(number);
    }
}
