<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <meta http-equiv="Cache-Control" content="no-store" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <title>黑龙江省分公司WIFI管理平台</title>

  <!-- Bootstrap core CSS -->
  <link href="${ctx}/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
  <%-- 重置bootstrap对html的样式 --%>
  <link href="${ctx}/bootstrap-3.3.7-dist/bootstrap-reset.css" rel="stylesheet">

  <!--
      Custom styles for this template
      自定义样式(style中有引入的fonts[font-awesome]和jquery-ui-1.10.3.css)
   -->
  <link href="${ctx}/css/style.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <%--<link href="${ctx}/css/dashboard.css" rel="stylesheet">--%>

  <style>
    html{
      width: 100%; height: 100%;
    }
    .login-body {
      background: #ffffff url("${ctx}/images/loginbg_red.jpg") no-repeat fixed;
      background-size: cover;
      width: 100%;
      height: 100%;
    }

    /*form框*/
    .signin1 {
      width: 100%; max-width: 960px; height: 480px;
      margin: 0 auto;
      position:absolute; top:50%; left: 50%; transform: translate(-50%,-50%);
      background:#fff;
      box-shadow: 0 0 30px 0 rgba(0, 0, 0, 0.15);
    }
    .login-box-bottom{
      padding: 0 50px;
    }
    .login-box-bottom .form-group{
      margin-bottom: 30px;
    }
    .singin-left{
      float: left;
      width: 42%; height: 100%;
      background: url("${ctx}/images/login_left.png") no-repeat center; background-size: cover;
    }
    .form-signin1{
      float: right;
      width: 58%; height: 100%;
    }
    .form-signin1 .checkbox {
      margin-bottom: 10px;
    }
    .form-signin1 .checkbox {
      font-weight: normal;
    }
    .form-signin1 .form-control:focus {
      z-index: 2;
    }
    .input-group-addon1 input[type=radio],.input-group-addon1 input[type=checkbox] {
      margin-top:0
    }
    .title {
      position: relative;
      padding: 55px 0 55px;
      font-size: 26px;
      font-family: "Microsoft YaHei";
      color: #666;
      text-align: center;
    }
    .login-logo{
      padding: 15px;
    }
    .login-logo>img{
      height: 40px;
    }
    .btn-login{
      margin: 35px 0;
      width: 100%;
      max-width: 250px;
      box-shadow: 0 5px 20px 5px rgba(186, 35, 41, 0.35);
      outline: none;
      background-color: #d92d34;
      color: #fff;
    }
    .btn-login:hover{
      background-color: #c2232a;
      color: #fff;
    }

    /* maincolor style */
    .has-maincolor .form-control {
      border-color: #e1575d;
    }
    .has-maincolor .form-control:focus {
      border-color: #d92d34;
      -webkit-box-shadow: inset 0 0 0 1000px #fff, 0 0 6px #f56e66;
      box-shadow: inset 0 0 0 1000px #fff, 0 0 6px #f56e66;
    }
    .has-maincolor .input-group-addon{
      color: #d92d34;
      border-color: #e1575d;
      background-color: #f7d5d6;
    }

    /* public css */
    .form-group-lg .input-group-addon{
      font-size: 18px;
      padding: 6px 16px;
    }
    .form-group-lg .form-control{
      height: 46px;
      padding: 10px 16px;
      font-size: 18px;
      line-height: 1.3333333;
      border-radius: 6px;
    }
    .form-control:-webkit-autofill{
      -webkit-box-shadow: 0 0 0 1000px white inset;
      -webkit-text-fill-color: #333;
    }
    @media screen and (max-width: 600px){
      .singin-left{
        display: none;
      }
      .form-signin1{
        width: 100%;
       }
    }
  </style>
</head>

<body class="login-body">
<div class="login-logo"><img src="${ctx}/images/logo.png" alt="logo"/></div>
<div class="signin1">
  <div class="singin-left"></div>
  <form class="form-signin1" style="margin-bottom: 0" action="${ctx}/sys/login" method="post" id="loginForm">
      <div class="title">
            Uwifi无线运营管理平台
      </div>
      <div class="login-box-bottom">
        <div class="form-group form-group-lg has-maincolor">
            <label class="control-label sr-only">用户名</label>
            <div class="input-group">
              <span class="input-group-addon"><i class="fa fa-user"></i></span>
              <input type="text" class="form-control" id="loginName" name="loginName" maxlength="20" placeholder="Username" autocomplete="off" autofocus>
            </div>
        </div>
        <div class="form-group form-group-lg has-maincolor" style="margin-bottom:25px;">
            <label class="control-label sr-only">密码</label>
            <div class="input-group">
              <span class="input-group-addon"><i class="fa fa-lock"></i></span>
              <input type="password" class="form-control" id="loginPwd" name="loginPwd" maxlength="20" placeholder="Password"  autocomplete="off">
            </div>
          </div>
        <div class="form-group">
          <label class="checkbox-inline"><input type="checkbox" onclick="remembers();" id="remember">记住密码</label>
        </div>
        <input type="hidden" id="rememeberFlag" name="rememeberFlag">
        <div class="text-center">
            <button class="btn btn-lg btn-login" id="btn_login" type="button" style="margin-top: 15px">登录</button>
        </div>
        <div class="text-center">
          <button class="btn btn-link btn-xs" id="btn_forget" type="button">忘记密码?</button>
        </div>
      </div>
  </form>
</div><!-- login box -->

<%--<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
     class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Forgot Password ?</h4>
      </div>
      <div class="modal-body">
        <p>Enter your e-mail address below to reset your password.</p>
        <input type="text" name="email" placeholder="Email" autocomplete="off"
               class="form-control placeholder-no-fix">

      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
        <button class="btn btn-primary" type="button">Submit</button>
      </div>
    </div>
  </div>
</div>--%><!-- Modal -->
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="model" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header1">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">找回密码</h4>
      </div>
      <div class="modal-body form-horizontal">
        <div class="alert alert-info" style="font-size: 14px;">输入手机号与用户名来重置密码.</div>
        <div class="form-group">
          <div class="col-sm-12">
            <input type="text" id="login_name" name="login_name" placeholder="账号" autocomplete="off" class="form-control placeholder-no-fix">
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-12">
            <input type="text" id="phone_num" name="phone_num" maxlength="11" placeholder="手机号" autocomplete="off" class="form-control placeholder-no-fix">
          </div>
          </div>
        <div class="form-group">
          <div class="col-xs-9">
            <input type="text" id="code" name="code" maxlength="6" placeholder="验证码" autocomplete="off" class="form-control placeholder-no-fix">
          </div>
          <div class="col-xs-3" style="padding-left: 0;">
            <button type="button" id="J_getCode" class="btn btn-info get-code" style="width: 98px;" onclick="getCode(this)">免费获取</button>
            <button class="btn btn-default reset-code" id="J_resetCode" style="display:none;width: 98px;">
              <span id="J_second">60</span>秒后重发
            </button>
          </div>

        </div>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-main-o" type="button" onclick="cl()">取消</button>
        <button class="btn btn-main" type="button" onclick="Submit()">提交</button>
      </div>
    </div>
  </div>
</div><!-- modal -->
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="model1" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">设置新密码</h4>
      </div>
      <div class="modal-body" style="margin-left: 13px;margin-right: 13px">
        <input type="text" id="newlogin_pwd" name="newlogin_pwd" placeholder="请输入新密码" autocomplete="off"
               class="form-control placeholder-no-fix">
      </div>
      <div class="modal-body" style="margin-left: 13px;margin-right: 13px">
        <input type="text" id="newlogin_pwd1" name="oldlogin_pwd" placeholder="请确认密码" autocomplete="off"
               class="form-control placeholder-no-fix">
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button" onclick="cl2()">取消</button>
        <button class="btn btn-primary" type="button" onclick="SubmitPwd()">确定</button>
      </div>
    </div>
  </div>
</div><!--model1-->
<!-- Placed js at the end of the document so the pages load faster -->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${ctx}/js/jquery-1.10.2.min.js"></script>
<script src="${ctx}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<%--
    Modernizr 使用 JavaScript 检测浏览器所支持的功能，但是，
    它并不是使用 JavaScript 动态地加载不同的样式表，而是使用非常简单的技术将类添加到页面的<html>标签。
    然后作为设计者由你决定使用 CSS 层叠为目标元素提供合适的样式。
 --%>
<%--<script src="${ctx}/js/modernizr.min.js"></script>--%>

<%-- alert(弹出框的封装类); --%>
<link rel="stylesheet" href="${ctx}/css/showBo.css" />
<script src="${ctx}/js/showBo.js"></script>
<script>
  //记住密码功能在后台解密
  var url;
  $(document).ready(function(){
    var currentUrl = window.location.href;
    var chars = currentUrl.substring(currentUrl.length-1);
    var aa = currentUrl.split("/");
    if (chars=="/"){
      url= aa[aa.length-2];
    }else{
      url= aa[aa.length-1];
    }
    if(url=='nologin'){
      //$ {remember}是退出后的返回值 1:有Cookie 0:无Cookie
      var a = '${remember}';
      if (a=="1"){
        $("#remember").attr("checked","checked");
        $("#rememeberFlag").val("1");
      }else if(a=="0"){
        $("#remember").removeAttr('checked');
        $("#rememeberFlag").val("");
      }
    }
    getCookie()
  });

  //在后台取  Cookie
  function getCookie(){
    $.ajax({
      async: false, //采用同步方式
      type:"POST",
      url :"${ctx}/sys/getDesStringCookieing",
      dataType: "text",
      success : function(c) {
        if (c!='0'){
          $("#remember").attr("checked","checked");
          var username = c.split("@")[1];
          var password = c.split("@")[2];
          $("#loginName").val(username);
          $("#loginPwd").val(password);
        }
        if(c==''){
          $("#remember").removeAttr('checked');
          $("#rememeberFlag").val("");
        }
      }
    });
  }

  //记住密码功能
  function remembers(){
    var remFlag = $("#remember").is(':checked');
    if(remFlag==true){ //如果选中设置remFlag为1
      Showbo.Msg.confirm('您确定要记住密码吗?',function(btn) {
        if(btn=='yes'){
          $("#rememeberFlag").val("1")
        } else{
          $("#remember").removeAttr('checked');
          $("#rememeberFlag").val("");
        }
      })
    }else{ //如果没选中设置remFlag为""
      $("#remember").removeAttr('checked');
      $("#rememeberFlag").val("");
    }
  }

  /**
   * 在密码框中 '回车登录'
   * bind() 方法为被选元素添加一个或多个事件处理程序，并规定事件发生时运行的函数。
   * keydown 当按钮被按下时,发生 keydown 事件。
   * event.keyCode按的建的代码，13表示回车, 18表示获取
   */
  $(function() {
    $("#loginPwd").bind('keydown', function(event) {
      if (event.keyCode == "13") {
        var loginName = $("#loginName").val();
        var loginPwd = $("#loginPwd").val();
        if (loginName.length==0 || loginPwd.length==0 ) {
          Showbo.Msg.alert("登录名称与登陆密码不能为空！");
          return false;
        }else{
          if($.isEmptyObject(loginName) || $.isEmptyObject(loginPwd)){
            Showbo.Msg.alert("请使用极速模式浏览器！");
            return false;
          }else {
            var remFlag = $("#remember").is(':checked');
            if(remFlag==true){ //如果选中设置remFlag为1
              $("#rememeberFlag").val("1")
            }else {
              $("#remember").removeAttr('checked');
              $("#rememeberFlag").val("");
            }
            $("#loginForm").submit();
          }
        }
      }
    });

    /**
     * 点击登录按钮
     */
    $("#btn_login").click(function () {
      var loginName = $("#loginName").val();
      var loginPwd = $("#loginPwd").val();
      if (loginName.length==0 || loginPwd.length==0 ) {
        Showbo.Msg.alert("登录名称与登陆密码不能为空！");
        return false;
      }else{
        if($.isEmptyObject(loginName) || $.isEmptyObject(loginPwd)){
          Showbo.Msg.alert("请使用极速模式浏览器！");
          return false;
        }else {
          var remFlag = $("#remember").is(':checked');
          if(remFlag==true){ //如果选中设置remFlag为1
            $("#rememeberFlag").val("1")
          }else {
            $("#remember").removeAttr('checked');
            $("#rememeberFlag").val("");
          }
          $("#loginForm").submit();
        }
      }
    });

    //"$ {returnInfo}"是 SysController类中login方法中登录失败的返回值
    var returnInfo = "${returnInfo}";
    if(!$.isEmptyObject(returnInfo)){
      if(returnInfo == "login_Not_OK"){
        Showbo.Msg.alert("登陆失败，登录名称或登陆密码错误！");
        $("#loginName").focus();
        return false;
      }
    }
  })

  //忘记密码
  $("#btn_forget").click(function(){
    $("#model").modal();

  })
  //获取格式为yyyy-MM-dd HH-MM-SS的当前系统时间
  function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
      month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
  }

  function Submit(){
      var time = getNowFormatDate();
      var code = $("#code").val();
      var phone_num =  $("#phone_num").val();
      var login_name =  $("#login_name").val();
     // var numList = [ "186", "185", "156", "155","130","131","132","176"];
      //var phoneNumPrefix = phone_num.substring(0,3);
      var re = /^[0-9]+.?[0-9]*$/;
     // var rv = $.inArray(phoneNumPrefix, numList);
      if(code.length==0||$.trim(code)==""){
        $("#code").focus();
          Showbo.Msg.alert("请输入验证码！")
        return false;
      }
      else if(code.length!=6){
        $("#code").focus();
          Showbo.Msg.alert("验证码为6位！")
        return false;
      }
      else  if(!re.test(code)) {
        Showbo.Msg.alert("验证码为纯数字！");
        $("#code").focus();
        return false;
      }
    $.post("${ctx}/Find/check",{phone_num:phone_num,code:code,time:time},function(d){
       if(d=="no1"){
         Showbo.Msg.alert("验证码错误！");
       }
       else if(d=="no2"){
         Showbo.Msg.alert("验证码已失效，请重新获取");
       }
       else if(d=="ok"){
           Showbo.Msg.alert("验证成功！")
           $('#model').modal('hide');
           $('#model1').modal();
       }
    });

  }
  function SubmitPwd() {
    var login_name =  $("#login_name").val();
    var newlogin_pwd = $("#newlogin_pwd").val();
    var newlogin_pwd1 = $("#newlogin_pwd1").val();
    if($.isEmptyObject(newlogin_pwd)){
      Showbo.Msg.alert("请输入新的密码！");
      return;
    }
    if($.isEmptyObject(newlogin_pwd1)){
      Showbo.Msg.alert("请输入确认密码！");
      return;
    }
    if(newlogin_pwd!=newlogin_pwd1){
      Showbo.Msg.alert("两次密码不一致，请重新输入！");
      return;
    }
    $.post("${ctx}/Find/update",{newlogin_pwd:newlogin_pwd,login_name:login_name},function(d){
      if(d=="ok"){
        Showbo.Msg.alert('修改成功');
        $('#model1').modal('hide');
        $("#phone_num").val("");
        $("#login_name").val("")
        $("#newlogin_pwd").val("");
        $("#newlogin_pwd1").val("");
        $("#code").val("");
      }else {
        Showbo.Msg.alert('修改失败');
      }
    })

  }
  function getCode(e){
    var phone_num =  $("#phone_num").val();
    var login_name =  $("#login_name").val();
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    var myReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
    if(login_name.length==0||$.trim(login_name)==""){
      Showbo.Msg.alert("请输入账号！");
      return false;
    }else  if (!myReg.test(login_name)){
      Showbo.Msg.alert("该账号含有非法字符，请重新输入！");
      return false;
    }
    else if(phone_num.length==0||$.trim(phone_num)==""){
      Showbo.Msg.alert("手机号不能为空！");
      $("#phone_num").focus();
      return false;
    }else if(!myreg.test(phone_num)){
      Showbo.Msg.alert("请输入正确的手机号码");
      $("#phone_num").focus();
      return false;
    }


    $.post("${ctx}/Find/sendCode",{phone_num:phone_num,login_name:login_name},function(d){
      if(d=="ok"){
        resetCode(); //倒计时
        Showbo.Msg.alert("验证码已发送到您的手机，请注意查收！");
        $("#phone_num").prop('disabled',true);
        $("#login_name").prop('disabled',true)
      }
      else if(d=="nook"){
        resetCode(); //倒计时
        Showbo.Msg.alert("获取失败，请重新发送！");
      }
      else if(d=="no"){
        Showbo.Msg.alert("手机号与账号不匹配！");

      }else if(d=="nook1"){
        Showbo.Msg.alert("该账号不存在！");

      }

    })


  }
  //倒计时
  function resetCode(){
    $("#J_resetCode").prop('disabled', true);
    $('#J_getCode').hide();
    $('#J_second').html('60');
    $('#J_resetCode').show();
    var second = 60;
    var timer = null;
    timer = setInterval(function(){
      second -= 1;
      if(second >0 ){
        $('#J_second').html(second);
      }else{
        clearInterval(timer);
        $('#J_getCode').show();
        $('#J_resetCode').hide();
      }
    },1000);
  }
function cl() {
  $("#phone_num").val("");
  $("#login_name").val("");
  $("#code").val("");
  $("#phone_num").prop('disabled',false);
  $("#login_name").prop('disabled',false);
}
  function cl2() {
    $("#phone_num").val("");
    $("#login_name").val("")
    $("#code").val("");
    $("#phone_num").prop('disabled',false);
    $("#login_name").prop('disabled',false);
  }
</script>

</body>
</html>

