package com.project.repository;

import com.project.component.RoleComponent;
import com.project.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 2017/12/28.
 */
@Repository
public class RoleRepository {
    @Autowired
    private BaseRepository baseRepository;

    public RoleComponent getRoleById(Integer roleId){
        String sql ="select * from t_role where id = ? ";
        return baseRepository.queryById(sql,RoleComponent.class,roleId);
    }
}
