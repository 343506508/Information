package com.project.service;

import com.project.component.ProjectComponent;
import com.project.service.base.BaseService;
import com.project.tools.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2018/1/6.
 */
@Service
public class ProjectService extends BaseService {

    //列表
    public Page<ProjectComponent> getListAll(String projectname, Integer pagesize, Integer count){
        return projectRepository.getListAll(projectname,pagesize,count);
    }
    // **管理表格导出
    public List<ProjectComponent> getList(String projectname){
        return projectRepository.getList(projectname);
    }

    //添加
    public Integer addProject(ProjectComponent project){
        return projectRepository.addProject(project);
    }

    //修改
    public Integer updateProject(ProjectComponent project){
        return projectRepository.updateProject(project);
    }

    //删除(真)
    public Integer goDelete(Integer id) {
        return projectRepository.goDelete(id);
    }
}
