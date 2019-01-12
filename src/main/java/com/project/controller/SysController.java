package com.project.controller;

import com.project.HelperClazz;
import com.project.component.FunctionComponent;
import com.project.component.RoleComponent;
import com.project.component.UserComponent;
import com.project.controller.base.BaseController;
import com.project.tools.CookieUtil;
import com.project.tools.Des3;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2017/12/28.
 */
@Controller
@RequestMapping(value = "/sys")
public class SysController extends BaseController {
    //cookie
    private static final String USER_ENCODE_KEY = "r_s@3+_09c294dm_?.cv<_u_u!@sd1";
    private static final String iv = "15801445";
    public static final String USER_COOKIE_NAME = "z_z_y_n";
    private static final String SPLIT = "@";

    //首页跳转
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        return "menu/login";
    }

    //登录
    @RequestMapping(value = "/login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response, String rememeberFlag) throws Exception{
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
        UserComponent user = userService.login(loginName,loginPwd);
        if (user != null) {//login ok
            String userInfo = user.getId() + SPLIT + user.getLogin_name() + SPLIT + user.getLogin_pwd();
            //3DES加密
            String encyStr = Des3.encode(userInfo, USER_ENCODE_KEY, iv);
            RoleComponent role = roleService.getRoleById(user.getRole_id());
            //是否在Cookie中记住密码 , rememeberFlag是前台一个隐藏input(就是放的记住状态)
            if ("1".equals(rememeberFlag)) {
                //记住密码功能
                Cookie cookie = new Cookie(USER_COOKIE_NAME, encyStr);
                cookie.setMaxAge(30 * 24 * 60 * 60);//有效期为一个月
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                //否则Cookie=>USER_COOKIE_NAME,setMaxAge=0清空
                String cookieValue = CookieUtil.getCookieValueByName(request,USER_COOKIE_NAME);
                if(cookieValue!=null){
                    Cookie cookie = new Cookie(USER_COOKIE_NAME, encyStr);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            HttpSession session = request.getSession();
            //LOGIN_SESSION_NAME是 session 名=>在BaseController中定义(public static final String LOGIN_SESSION_NAME = "information_build";)
            session.setAttribute(LOGIN_SESSION_NAME, user.getId() +"@"+ user.getName() +"@"+ role.getId() +"@"+ role.getName() +"@"+ role.getFlag() +"@"+ role.getLevel());
            session.setAttribute("userSession",user);
            session.setAttribute("roleSession",role);
            model.addAttribute("user", user);
            //菜单列表
            List<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>();
            //查出pid=0的
            List<FunctionComponent> parentList=functionService.getAllParent(user.getRole_id());
            for (FunctionComponent function : parentList){
                Map<String,Object> map = new HashMap<>();
                map.put("parentList",function);//pid=0的
                //查菜单列表中的子菜单
                List<FunctionComponent> childrenList=functionService.getAllByPid(function.getId(),user.getRole_id());
                map.put("childrenList",childrenList);
                menuList.add(map);
            }
            request.getSession().setAttribute("menuList",menuList);
            String path = "redirect:/main/index";//默认跳转到该页面
            return path;

        } else {//login no ok

            model.addAttribute("returnInfo", "login_Not_OK");
            return index(request);

        }
    }

    //退出登录
    @RequestMapping(value = "/nologin")
    public String nologin(HttpServletRequest request,HttpServletResponse response,Model model) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_SESSION_NAME, null);
        //request.setAttribute("version",paramSettingsType.getVersion());
        String cookieValue = CookieUtil.getCookieValueByName(request,USER_COOKIE_NAME);
        if (cookieValue!=null){
            model.addAttribute("remember","1");
        }else{
            model.addAttribute("remember","0");
        }
        return "menu/login";

    }

    //利用CookieUtil工具类获得Cookie
    @RequestMapping(value="getDesStringCookieing")
    public void getDecString(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String cookieValue = CookieUtil.getCookieValueByName(request,USER_COOKIE_NAME);
        String values="";
        if (cookieValue!=null){
            values = Des3.decode(cookieValue,USER_ENCODE_KEY,iv) ;
        }else{
            values = "0";
        }
        HelperClazz.renderText(response,values);
    }
}
