package com.project.tools;

import com.project.HelperClazz;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/11/30.
 */
public class WebInterceptor extends HandlerInterceptorAdapter {
    /**
     * 前置检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        String cuser= HelperClazz.getLoginInfo(request,0);
//        String adminName = HelperClazz.getLoginInfo(request,1);
        String XRequested =request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(XRequested)){
            if (cuser == null || "".equals(cuser)) {
                String str = "ajaxfail";
                response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(str);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        }else {
            if (cuser == null || "".equals(cuser)) {
                String str = "<script language='javascript'>alert('会话过期,请重新登录');"
                        + "window.top.location.href='"
                        + request.getContextPath() + "/sys/index"
                        + "';</script>";
                response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(str);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //request.getRequestDispatcher("/sys/login").forward(request, response);
                //response.sendRedirect("/user/index");
                //response.sendRedirect("/index.jsp");
                return false;

            }

        }
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if(response.getStatus()==500){
            modelAndView.setViewName("/error/500");
        }else if(response.getStatus()==404){
            modelAndView.setViewName("/error/404");
        }
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        //request.getSession().setAttribute("abc","1");

    }
}
