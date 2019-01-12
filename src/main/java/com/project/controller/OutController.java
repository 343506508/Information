package com.project.controller;

import com.project.controller.base.BaseController;
import com.project.tools.MenuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 2017/12/28.
 * User : 是Controller
 */
@Controller
@RequestMapping( value = "/out")
public class OutController extends BaseController  {

    /* BaseController可(@Autowired 或 extends )
    @Autowired
    private BaseController baseController;*/

    //首页跳转
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model, @ModelAttribute MenuUtil menuUtil) {
        model.addAttribute("menuUtil",menuUtil);
        return "out/index";
    }

}
