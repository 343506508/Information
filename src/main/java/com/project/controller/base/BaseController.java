package com.project.controller.base;

import com.project.service.FunctionService;
import com.project.service.ProjectService;
import com.project.service.RoleService;
import com.project.service.UserService;
import com.project.tools.LogInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Admin on 2017/12/28.
 * Base : 是Controller的基类
 */
@Controller
public class BaseController {

    @Autowired
    public LogInfoUtil logInfoUtil;

    @Autowired
    public UserService userService;
    @Autowired
    public RoleService roleService;
    @Autowired
    public FunctionService functionService;
    @Autowired
    public ProjectService projectService;

    //LoginController中session_name
    public static final String LOGIN_SESSION_NAME = "information_build";
}
