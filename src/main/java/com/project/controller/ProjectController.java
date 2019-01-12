package com.project.controller;

import com.project.HelperClazz;
import com.project.component.ProjectComponent;
import com.project.controller.base.BaseController;
import com.project.tools.ExportExcel;
import com.project.tools.MenuUtil;
import com.project.tools.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( value = "/project" )
public class ProjectController extends BaseController {

    //首页跳转
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model, @ModelAttribute MenuUtil menuUtil) {
        model.addAttribute("menuUtil",menuUtil);
        return "project/index";
    }

    //列表
    @ResponseBody
    @RequestMapping(value = "/queryList")
    public Map<String, Object> queryList(String projectname, Integer pagesize, Integer count) {
        Map<String, Object> map = new HashMap<>();
        Page<ProjectComponent> pageList = projectService.getListAll(projectname.trim(), pagesize, count);
        map.put("rows", pageList.getResult());
        map.put("total", pageList.getTotalCount());
        return map;
    }

    //添加
    @ResponseBody
    @RequestMapping(value = "/addProject")
    public Integer addProject(String projectname, String number) {
        ProjectComponent project = new ProjectComponent();
        project.setProjectname(projectname);
        project.setNumber(number);
        return projectService.addProject(project);
    }

    //修改
    @RequestMapping(value = "/updateProject")
    public void updateProject(HttpServletResponse response, ProjectComponent project) {
        Integer status = projectService.updateProject(project);
        HelperClazz.renderText(response,"ok");
    }

    //删除(真)
    @RequestMapping(value = "/goDelete")
    public void goDelete(Integer id, HttpServletResponse response) {
        int i= projectService.goDelete(id);
        if(i != -1){
            HelperClazz.renderText(response,"ok");
        }
    }

    //真删除多条
    @RequestMapping("/deleteMore")
    public void deleteMore(String  DeleteIdArr,HttpServletResponse response){
        System.out.println("DeleteIdArr                "+DeleteIdArr);
        List<String> arr =new ArrayList<String>();
        if (!"".equals(DeleteIdArr)){
            for (int i=0;i<DeleteIdArr.split(",").length;i++){
                arr.add(DeleteIdArr.split(",")[i]);
            }
        }
        int j=0;
        for (String id: arr) {
            //删除t_project(调用的是单条删除)
            projectService.goDelete(Integer.parseInt(id));
            j++;
        }
        if(j==arr.size()){
            HelperClazz.renderText(response,"ok");
        }
    }

    // **管理表格导出
    @RequestMapping(value = "/excelForm")
    public void excel(HttpServletResponse response,String projectname,HttpServletRequest request) throws Exception {
        String[] rowsName = new String[]{"序号","项目名称", "编号"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List projectList = projectService.getList(projectname);
        for (int i = 0; i < projectList.size(); i++) {
            ProjectComponent project = (ProjectComponent) projectList.get(i);
            objs = new Object[rowsName.length];

            if (project.getProjectname() != null && !project.getProjectname().equals("")) {
                objs[1] = project.getProjectname();
            } else {
                objs[1] = " ";
            }
            if (project.getNumber() != null && !project.getNumber().equals("")) {
                objs[2] = project.getNumber();
            } else {
                objs[2] = " ";
            }

            /*if (bg.getCdate() != null && !bg.getCdate().equals("")) {
                objs[6] = sdf.format(bg.getCdate());
            } else {
                objs[6] = " ";
            }*/
            /*if (bg.getCdate() != null ) {
                objs[6] = sdf.format(bg.getCdate());
            } else {
                objs[6] = " ";
            }*/

            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel("", rowsName, dataList);
        ex.export(response);

    }
}
