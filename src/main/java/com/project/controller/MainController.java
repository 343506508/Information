package com.project.controller;

import com.project.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Admin on 2017/12/29.
 */
@Controller
@RequestMapping(value = "/main")
public class MainController extends BaseController {

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpSession session){
        return "main/index";
    }
}
