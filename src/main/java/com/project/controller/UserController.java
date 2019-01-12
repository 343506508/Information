package com.project.controller;

import com.project.HelperClazz;
import com.project.component.ImportExceptionUser;
import com.project.component.RoleComponent;
import com.project.component.UserComponent;
import com.project.controller.base.BaseController;
import com.project.tools.ExcelHelper;
import com.project.tools.MenuUtil;
import com.project.tools.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2017/12/28.
 * User : 是Controller
 */
@Controller
@RequestMapping( value = "/user")
public class UserController extends BaseController  {

    /* BaseController可(@Autowired 或 extends )
    @Autowired
    private BaseController baseController;*/

    //首页跳转
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model, @ModelAttribute MenuUtil menuUtil) {
        model.addAttribute("menuUtil",menuUtil);
        //角色列表
        List<RoleComponent> roleList = userService.getRoleList();
        model.addAttribute("roleList",roleList);
        return "user/index";
    }

    //列表
    @ResponseBody
    @RequestMapping(value = "/queryList")
    public Map<String, Object> queryList(String search, Integer pagesize, Integer count) {
        Map<String, Object> map = new HashMap<>();
        Page<UserComponent> pageList = userService.getListAll(search.trim(), pagesize, count);
        map.put("rows", pageList.getResult());
        map.put("total", pageList.getTotalCount());
        return map;
    }

    //添加
    @ResponseBody
    @RequestMapping(value = "/addUser")
    public Integer addUser(UserComponent user) {
        return userService.addUser(user);
    }

    //修改
    @RequestMapping(value = "/updateUser")
    public void updateProject(HttpServletResponse response, UserComponent user) {
        Integer status = userService.updateUser(user);
        HelperClazz.renderText(response,"ok");
    }

    //删除(真)
    @RequestMapping(value = "/goDelete")
    public void goDelete(Integer id, HttpServletResponse response) {
        int i= userService.goDelete(id);
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
            //删除t_user(调用的是单条删除)
            userService.goDelete(Integer.parseInt(id));
            j++;
        }
        if(j==arr.size()){
            HelperClazz.renderText(response,"ok");
        }
    }

    // **管理表格导入
    @RequestMapping(value = "/importExcel")
    public String importExcel(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, RedirectAttributesModelMap attr,Model model){
        //*******上传文件  保存在fileload文件夹下的excel文件夹中
//        System.out.println(request.getParameter("bgname1"));
        logInfoUtil.setLogName("开卡数据导入");
        String fileName = file.getOriginalFilename();
        try {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        }catch (Exception e){

        }
        String filePath = request.getSession().getServletContext().getRealPath("/")
                + "fileload/excel/"+System.currentTimeMillis()+fileName;
        // 转存文件
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File targetFile = new File(filePath);
        //****上传文件end
        //得到表格中所有的数据
        int errcount = 0;
        int successcount = 0;
        int cfcount = 0;
        List<String> list = null;
        List<ImportExceptionUser> elist = new ArrayList<ImportExceptionUser>();
        try {
            list = ExcelHelper.exportListFromExcel(new File(""+targetFile), 0);
            for (int i = 0; i < list.size(); i++) {

                String[] strArray = null;
                String str=list.get(i).substring(1)+"|";
                System.out.println("str==>"+str);
                int len=str.length()-1;
                strArray=str.substring(0,len).split("\\|");
                if("null".equals(strArray[0])){
                    strArray[0]="";
                }
                if("null".equals(strArray[1])){
                    strArray[1]="";
                }
                if("null".equals(strArray[2])){
                    strArray[2]="";
                }
//                System.out.println(strArray[0]);
//                System.out.println(strArray[1]);

                if(!"".equals(list.get(i))&&list.get(i)!=null){
//                    String[] liantongnum = { "186", "185", "156", "155","130","131","132","176","145"};
                    String[] p_numreg = { "13", "14", "15", "17","18"};
                    ImportExceptionUser iue = new ImportExceptionUser();
                    if(i!=0&&strArray[0].length()==11&&Arrays.asList(p_numreg).contains(strArray[0].substring(0,2))){
//                        if(Arrays.asList(liantongnum).contains(strArray[0].substring(0,3))||"1".equals(customDao.findGetOtherFlag(request.getParameter("bgname")).get(0).getOtherflag())){

                        if(userService.getphoneCount(strArray[0])==0){
                            if(!isChineseChar(strArray[2])){
                                UserComponent user = new UserComponent();
                                //导入时手机号后六位作为密码
                                user.setPhone(strArray[0]);
                                String pwd = strArray[0].substring(5,11);
                                user.setLogin_pwd(pwd);
//                                if(type!=null){
//                                    user.setType_id(Integer.parseInt(type));
//                                }
                                user.setName(strArray[1]);
                                user.setLogin_name(strArray[2]);

                                user.setDate(new Date());
                                user.setRole_id(4);//默认普通员工
                                user.setPhone("1");
                                user.setSex(1);//默认男
                                user.setFlag(1);//默认账户启用

                                int flag = userService.addUser(user);

                                if(flag==-1){
                                    iue.setEname("异常错误");
                                    elist=shuJu(iue,elist,strArray,i);
                                    errcount++;
                                }else{
                                    successcount++;
                                }
                            }else {

                                iue.setEname("身份证号不符合标准");
                                elist=shuJu(iue,elist,strArray,i);
                                errcount++;


                            }
                        }


                        else{
                            iue.setEname("电话号码重复");
                            elist=shuJu(iue,elist,strArray,i);
                            errcount++;
                        }

//                        }else{
//                            iue.setEname("非联通号码");
//                            elist=shuJu(iue,elist,strArray,i);
//                            errcount++;
//                        }
                    }else{
                        iue.setEname("号码不符合标准");
                        elist=shuJu(iue,elist,strArray,i);
                        errcount++;
                    }
                }
            }
            attr.addFlashAttribute("importflag","1");
            attr.addFlashAttribute("eList",elist);
        } catch (IOException e) {
            e.printStackTrace();
            attr.addFlashAttribute("importflag","0");
        }

        attr.addFlashAttribute("errcount",errcount);
        attr.addFlashAttribute("cfcount",cfcount);
        attr.addFlashAttribute("successcount",successcount);
        return "redirect:/user/index";
    }
    //中文
    public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }
    //导入报错实体记录
    public  List<ImportExceptionUser> shuJu(ImportExceptionUser iue, List elist, String[] strArray, int i){
        iue.setPhonenum(strArray[0]);
        iue.setName(strArray[1]);
        iue.setIdCard(strArray[2]);
        iue.setRownum(""+i);
        elist.add(iue);
        return elist;
    }
}
