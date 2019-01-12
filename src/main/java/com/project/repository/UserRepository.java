package com.project.repository;

import com.project.component.RoleComponent;
import com.project.component.UserComponent;
import com.project.repository.base.BaseRepository;
import com.project.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2017/12/28.
 * User : 是Dao
 */
@Repository
public class UserRepository {

    @Autowired
    private BaseRepository baseRepository;

    public UserComponent login(String loginName, String pwd){
        // flag ==> 0:注销 1:正常
        final String sql ="select * from t_user where login_name = ? and login_pwd = ? and flag='1' ";
        List<UserComponent> user = baseRepository.query(sql,UserComponent.class,new Object[]{loginName,pwd});
        return user.isEmpty() ? null : user.get(0);
    }

    //列表
    public Page<UserComponent> getListAll(String search, Integer pagesize, Integer count){
        String sql ="select u.*,r.name as roleName from t_user u left join t_role r on r.id=u.role_id where (u.name like ? or login_name like ? or date like ? or phone like ? or sex like ? or r.name like ? ) ";
        return baseRepository.queryByPage(sql,UserComponent.class, new Object[]{"%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%","%"+search+"%"},pagesize,count);
    }

    //添加前的角色列表
    public List<RoleComponent> getRoleList(){
        String sql = "select * from t_role";
        return baseRepository.query(sql,RoleComponent.class);
    }

    //添加
    public Integer addUser(UserComponent user){
        String sql ="insert into t_user(name,login_name,login_pwd,date,role_id,phone,sex,flag) values(:name,:login_name,:login_pwd,now(),:role_id,:phone,:sex,1)";
        return baseRepository.insert(sql,user);
    }

    //修改
    public Integer updateUser(UserComponent user){
        String sql ="update t_user set name=:name,login_name=:login_name=:login_name,login_pwd=:login_pwd=:login_pwd,role_id=:role_id,phone=:phone,sex=:sex where id=:id ";
        return baseRepository.update2(sql,user);
    }

    //删除(真)
    public Integer goDelete(Integer id) {
        return baseRepository.delete("t_user",id);
    }

    //导入前 , 判断是否存在用户
    public int getphoneCount(String number) {
        String sql = "select count(*) from t_project where number = ? ";
        return  baseRepository.queryForObject(sql,Integer.class,new Object[]{number});
    }
}
