package com.project.repository;

import com.project.component.ProjectComponent;
import com.project.repository.base.BaseRepository;
import com.project.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 2017/12/28.
 */
@Repository
public class ProjectRepository {
    @Autowired
    private BaseRepository baseRepository;


    public ProjectComponent getRoleById(Integer roleId){
        String sql ="select * from t_role where id = ? ";
        return baseRepository.queryById(sql,ProjectComponent.class,roleId);
    }

    //列表
    public Page<ProjectComponent> getListAll(String projectname, Integer pagesize, Integer count){
        String sql ="select * from t_project where (projectname like ?) ";
        return baseRepository.queryByPage(sql,ProjectComponent.class, new Object[]{"%"+projectname+"%"},pagesize,count);
    }
    // **管理表格导出
    public List<ProjectComponent> getList(String projectname){
        String sql ="select * from t_project where (projectname like ?) ";
        return baseRepository.query(sql,ProjectComponent.class, new Object[]{"%"+projectname+"%"});
    }

    //添加
    public Integer addProject(ProjectComponent project){
        String sql ="insert into t_project(projectname,number) values(:projectname,:number)";
        return baseRepository.insert(sql,project);
    }

    //修改
    public Integer updateProject(ProjectComponent project){
        String sql ="update t_project set projectname=:projectname,number=:number where id=:id";
        return baseRepository.update2(sql,project);
    }

    //删除(真)
    public Integer goDelete(Integer id) {
        return baseRepository.delete("t_project",id);
    }
}
