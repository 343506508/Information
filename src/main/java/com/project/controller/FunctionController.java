package com.project.controller;

import com.project.HelperClazz;
import com.project.component.FunctionComponent;
import com.project.component.UserRoleComponent;
import com.project.controller.base.BaseController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Admin on 2018/1/10.
 */
@Controller
@RequestMapping( value = "/function")
public class FunctionController extends BaseController {
    //菜单显示
    @RequestMapping( value = "/queryMenu")
    public void queryMenu(HttpServletResponse response, Integer roleid){
        response.setCharacterEncoding("utf-8");
        //所有权限
        List<FunctionComponent> functionAllList =  functionService.queryRoleAllFunction();
        //当前登录人的的权限
        List<FunctionComponent> functionLoginList =  functionService.functionLoginList(roleid);
        JSONArray jsonArray = new JSONArray();
        if (functionAllList != null && functionAllList.size() > 0) {
            for (int i = 0; i < functionAllList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                FunctionComponent function = functionAllList.get(i);
                jsonObject.put("id",function.getId());
                jsonObject.put("pid",function.getPid());
                jsonObject.put("name",function.getName());
                for (int j = 0; j < functionLoginList.size(); j++) {
                    FunctionComponent functionChecked = functionLoginList.get(j);
                    //判断已有权限选中
                    if (function.getId()==functionChecked.getId()) {
                        jsonObject.put("checked", true);
                    }
                }
                jsonObject.put("open", "true");
                jsonArray.put(jsonObject);
            }
        }
        String jsonCommunity = jsonArray.toString();
        HelperClazz.renderText(response,jsonCommunity);
    }

    //授权菜单(将原有的菜单在关联表中删除, 在添加新的)
    @RequestMapping(value="/settingMenu")
    public void settingMenu(String id,String str,HttpServletResponse response,HttpServletRequest request){
        logInfoUtil.setLogName("配置菜单");
        String id1 = HelperClazz.getLoginInfo(request, 0);
        //删除该ID下所有菜单
        functionService.deleteMenuByRoleId(id);
        int i=0;
        //添加菜单
        if(str!=null&&!"".equals(str)){
            String[] menuArray = str.split(",");
            UserRoleComponent userRole=new UserRoleComponent();
            userRole.setRoleid(Integer.parseInt(id));
            for(String menuId:menuArray){
                if(!"0".equals(menuId)){
                    userRole.setFunctionid(Integer.parseInt(menuId));
                    i = functionService.insertUserRoleMenu(userRole);
                }
            }
        }
        if (i!=-1){
            HelperClazz.renderText(response,"ok");
        }
    }
}
