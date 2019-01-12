package com.project.repository;

import com.project.component.FunctionComponent;
import com.project.component.UserRoleComponent;
import com.project.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2017/12/28.
 * User : 是Dao
 */
@Repository
public class FunctionRepository {

    @Autowired
    private BaseRepository baseRepository;

    //查出pid=0的(父菜单)
    public List<FunctionComponent> getAllParent(Integer roleid){
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from t_function where flag = '1' and pid=0");
        sql.append(" and id in (SELECT functionid from t_role_function where roleid = ? and flag='1') ");
        sql.append(" order by sort");
        return baseRepository.query(sql.toString(),FunctionComponent.class,new Object[]{roleid});
    }

    //子菜单
    public List<FunctionComponent> getAllByPid(Integer pid, Integer roleid){
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from t_function where flag = '1' and pid=?");
        sql.append(" and id in (SELECT functionid from t_role_function where roleid = ? and flag='1') ");
        sql.append(" order by sort");
        return baseRepository.query(sql.toString(),FunctionComponent.class,new Object[]{pid,roleid});
    }

    //所有菜单
    public List<FunctionComponent> queryRoleAllFunction(){
        String sql = "select * from t_function where flag = 1";
        return baseRepository.query(sql,FunctionComponent.class);
    }

    //当前登录人的菜单(Menu)
    public List<FunctionComponent> functionLoginList(Integer roleid){
        String sql = "select * from t_function where flag = 1 and id in ( select functionid from t_role_function where roleid = ? )";
        return baseRepository.query(sql,FunctionComponent.class,new Object[]{roleid});
    }

    //删除角色_URL的关联
    public void deleteMenuByRoleId(String roleid) {
        String sql = "delete from t_role_function where roleid =?";
        baseRepository.delete2(sql,roleid);
    }

    //添加角色_URL的关联
    public Integer insertUserRoleMenu(UserRoleComponent userRole) {
        String sql = "insert into t_role_function (roleid,functionid) values(:roleid,:functionid)";
        return baseRepository.insert(sql,userRole);
    }
}
