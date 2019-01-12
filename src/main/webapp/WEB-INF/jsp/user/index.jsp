<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords" content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">
    <title>黑龙江分公司WIFI管理平台 </title>
</head>
<body class="sticky-header">
<section>
    <!-- left side start-->
    <%@include file="../menu/menu.jsp"%>
    <!-- left side end-->

    <!-- main content start-->
    <div class="main-content">
        <!-- header section start-->
        <%@include file="../menu/top.jsp"%>
        <!-- header section end-->

        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-sm-12">
                    <section class="panel">
                        <!-- page heading start-->
                        <div class="page-heading">
                            <h3>
                                <i class="fa fa-th-list" style="margin-right: 5px"></i> 用户管理
                            </h3>
                        </div>
                        <!-- page heading end-->

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-7">
                                    <div class="form-group">
                                        <input id="search_name" name="search_name" type="text" class="form-control" onkeydown="if(event.keyCode==13){gosearch();}" placeholder="请输入关键字">
                                    </div>
                                </div>
                                <div class="col-lg-1 ">
                                    <div hidden>
                                        <button class="btn btn-info" id="haohao" type="button"  ></button>
                                    </div>
                                    <button class="btn btn-info"  type="button" onclick="gosearch()"><i class="fa fa-search"></i> 查询</button>
                                </div>
                                <div class="col-lg-1 ">
                                    <button class="btn btn-danger" type="button" onclick="deleteMore()"><i class="fa fa-trash-o"></i> 删除</button>
                                </div>
                                <div class="col-lg-1 ">
                                    <button class="btn btn-info" type="button" onclick="goreset()"><i class="fa fa-repeat"></i> 重置</button>
                                </div>
                                <div class="col-lg-1">
                                    <button class="btn btn-info" type="button" onclick="add()"><i class="fa fa-plus"></i> 添加</button>
                                </div>
                                <div class="col-lg-1 ">
                                    <button class="btn btn-info" type="button" onclick="DaoRu()"><i class="fa fa-sign-in"></i> 导入</button>
                                </div>

                                <table id="teacher_table" data-toggle="table"
                                       data-toolbar="#toolbar"
                                <%-- data-search="true"--%>
                                       data-page-size="5">
                                </table>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!--body wrapper end-->
        <!--footer section start-->
        <!--footer section end-->
    </div>
    <!-- main content end-->
</section>
<%-- 添加和修改的model_Start 查看的model没做也是用的这个 --%>
<div class="modal fade" id="model">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header1">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">项目管理</h4>
            </div>
            <div class="modal-body" >

                <input type="hidden" id="id">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label  class="col-lg-4 col-sm-2 control-label"><font style="color: red">*</font> 用户姓名：</label>
                        <div class="col-lg-7">
                            <input id="name" name="name" type="text" class="form-control" placeholder="用户姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-lg-4 col-sm-2 control-label"><font style="color: red">*</font> 用户名：</label>
                        <div class="col-lg-7">
                            <input id="login_name" name="login_name"  type="text" class="form-control" placeholder="登录账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-lg-4 col-sm-2 control-label"><font style="color: red">*</font> 登录密码：</label>
                        <div class="col-lg-7">
                            <input id="login_pwd" name="login_pwd"  type="password" class="form-control" placeholder="登录密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-lg-4 col-sm-2 control-label"><font style="color: red">*</font> 角色：</label>
                        <div class="col-lg-7">
                            <select id="roleid" name="role_id" class="form-control">
                                <option value="">请选择角色</option>
                                <c:forEach var="i" items="${roleList}">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-lg-4 col-sm-2 control-label"><font style="color: red">*</font> 手机号：</label>
                        <div class="col-lg-7">
                            <input id="phone" name="phone" maxlength="11" type="text" class="form-control" placeholder="手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-lg-4 col-sm-2 control-label"><font style="color: red">*</font> 性别：</label>
                        <div class="col-lg-7">
                            <select id="sex" name="sex" class="form-control">
                                <option value="">请选择</option>
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer" id="qlfoot1">
                <button type="button" class="btn btn-main" onclick="goAdd()" ><i class="fa fa-check"></i> 确定</button>
                <button type="button"  class="btn btn-main-o" data-dismiss="modal"><i class="fa fa-times"></i> 取消</button>
            </div>
            <div class="modal-footer" id="qlfoot2" style="display: none">
                <button type="button" class="btn btn-main" onclick="goUpdate()"><i class="fa fa-check"></i> 修改</button>
                <button type="button" class="btn btn-main-o" data-dismiss="modal"><i class="fa fa-times"></i> 取消</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%-- 添加和修改的model_End --%>

<%-- 配置tree_Start --%>
<div class="modal fade" id="settingModel">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header1">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">菜单配置</h4>
            </div>
            <div class="modal-body" >
                <input type="hidden" id="settingId">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree" style="width: 310px"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-main" onclick="settingMenu()"><i class="fa fa-check"></i> 确定</button>
                <button type="button" class="btn btn-main-o" data-dismiss="modal"><i class="fa fa-times"></i> 取消</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%-- 配置tree_End --%>

<%-- 导入_Start --%>
<div class="modal fade" id="modelImportExcel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header1">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> 批量导入</h4>
            </div>
            <div class="modal-body" >
                <form action="${pageContext.request.contextPath}/project/importExcel" name="importfrom" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <a style="color: #0c75d4" href="${pageContext.request.contextPath}/fileload/user.xlsx"> 模板下载.xlsx</a>
                    </div>
                    <div class="form-group">
                        <input type="file" name="file" id="file" >
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary"  id="btn_import" ><i class="fa fa-check"></i>导入</button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>
<%-- 导入_End --%>
</body>
<script>
    $(function () {
        var dtb1 = new DataTable1();
        dtb1.Init();
    });
    var DataTable1 = function (){
        var oTableInit = new Object();
        oTableInit.Init = function (){
            $('#teacher_table').bootstrapTable('destroy').bootstrapTable({
                url: "${ctx}/user/queryList",
                method: 'get',
                striped: true,
                cache: false,
                toolbar: false,
                pagination: true,
                sortable: false,
                queryParamsType: "limit",
                queryParams: oTableInit.queryParams,
                pageNumber:1,
                pageSize: 7,
                pageList:[7,14, 21],
                strictSearch: true,
                clickToSelect: true,
                cardView: false,
                paginationFirstText:"<<",
                paginationPreText:"<",
                paginationNextText:">",
                paginationLastText:">>",
                showExport: true,//显示导出按钮
                exportDataType: "basic",//导出类型
                sidePagination: "server",

                columns: [
                    {
                        checkbox:true,
                    }
                    ,{
                        title: '序号',align: 'center',
                        formatter: function(value,row,index){
                            return Number(row.count+index)+1;
                        }
                    }
                    ,{
                        field: 'name',
                        title:  '用户名称',align: 'center',

                    },{
                        field: 'login_name',
                        title: '用户名',align: 'center',
                    }
                    ,{
                        field: 'sex',
                        title: '性别',align: 'center',
                        formatter: function(value,row,index){
                            if(value==1){
                                return "男";
                            } else {
                                return "女";
                            }
                        }
                    }
                    ,{
                         field: 'date',
                         title: '创建日期',align: 'center',
                         formatter: function(value,row,index){
                         return getTime(value)
                         }

                    }
                    ,{
                        field: 'phone',
                        title: '手机号',align: 'center',
                    }
                    ,{

                        title: '操作',
                        width: '130px',align:'center',

                        formatter: function(value,row,index){
                            var button ='<div class="btn-group btn-group-xs">'+
                                    '<button type="button" class="btn btn-xs btn-lightmain" onclick="select(\''+row.name+'\',\''+row.login_name+'\',\''+row.login_pwd+'\',\''+row.role_id+'\',\''+row.phone+'\',\''+row.sex+'\')""><i class="fa fa-eye"></i>&nbsp;查&nbsp;看</button>';
                            var e =  '<button type="button" class="btn btn-lightmain dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> '+
                                    '<span class="caret"></span>'+
                                    '</button>'+
                                    '<ul class="dropdown-menu">';
                            var g = '<li><a onclick="update(\''+row.id+'\',\''+row.name+'\',\''+row.login_name+'\',\''+row.login_pwd+'\',\''+row.role_id+'\',\''+row.phone+'\',\''+row.sex+'\')">修改</a></li>'+
                                    /*'<li><a onclick="qy(\''+ row.bg_id + '\')">启用</a></li>'+*/
                                    '<li><a onclick="setMenu(\''+ row.role_id + '\')">配置</a></li>'+
                                    '<li><a id="ServerStop" onclick="goDelete(\''+row.id+'\')" style="color:red">删除</a></li>';
                            if(row.sts ==1){
                                return button+e+ '</ul></div>';
                            } else {
                                return button+e+g + '</ul></div>';
                            }
                        },
                    }
                ]
            });
        };
        //得到查询的参数
        oTableInit.queryParams = function (params) {
            return {

                count: params.limit,  //页面大小
                pagesize:params.offset, //页码
                search:$('#search_name').val(),
            };
        };
        return oTableInit;
    }

    //时间format
    function getTime(timestamp) {
        var ts = arguments[0] || 0;
        var t,y,m,d,h,i,s;
        t = ts ? new Date(parseInt(ts)) : new Date();
        y = t.getFullYear();
        m = t.getMonth()+1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        s = t.getSeconds();
        // 可根据需要在这里定义时间格式
        return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d)+' '+(h<10?'0'+h:h)+':'+(i<10?'0'+i:i)+':'+(s<10?'0'+s:s);
    }

    //查询
    function gosearch() {
        $('#teacher_table').bootstrapTable('refreshOptions',{pageNumber:1,pagesize:7});
    }
    //清空
    function goreset() {
        $("#search_name").val("");
        gosearch();
    }

    //打开添加模态框
    function add(){
        $('#name').val("");
        $('#login_name').val("");
        $('#login_pwd').val("");
        $('#roleid').val("");
        $('#phone').val("");
        $('#sex').val("");
        $('#model').modal();
    }
    //添加项目 *(添加项目的model和修改的model是一个model)
    function goAdd() {
        var name = $('#name').val();
        var login_name = $('#login_name').val();
        var login_pwd = $('#login_pwd').val();
        var role_id = $('#roleid').val();
        var phone = $('#phone').val();
        var sex = $('#sex').val();
        //手机正则if(!myreg.test(***)&& phone !=""){
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
        //非法字符if(!myReg.test(***)&&***!=""){
        var myReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
        //数字
        var patrn2=/^\d{11}$/;
        if ($.isEmptyObject(name)||name.trim()=="") {
            Showbo.Msg.alert("请输入用户名称！");
            return false;
        }else if(!myReg.test(name)){
            Showbo.Msg.alert(name+"名称含有非法字符，请重新输入！");
            return false;
        }else if($.isEmptyObject(login_name)||login_name.trim()==""){
            Showbo.Msg.alert("请输登录账号！");
            return false;
        }else if(!myReg.test(login_name)&&login_name!=""){
            Showbo.Msg.alert("账号含有非法字符，请重新输入！");
            return false;
        }else if($.isEmptyObject(login_pwd)||login_pwd.trim()==""){
            Showbo.Msg.alert("请输密码！");
            return false;
        }else if(!myReg.test(login_pwd)&&login_pwd!=""){
            Showbo.Msg.alert("密码含有非法字符，请重新输入！");
            return false;
        }else if($.isEmptyObject(role_id)||role_id.trim()==""){
            Showbo.Msg.alert("请选择角色！");
            return false;
        }else if($.isEmptyObject(phone)||phone.trim()==""){
            Showbo.Msg.alert("请输手机号！");
            return false;
        }else if(!myreg.test(phone)&&phone!=""){
            Showbo.Msg.alert("手机号有误!，请重新输入！");
            return false;
        }else if($.isEmptyObject(sex)||sex.trim()==""){
            Showbo.Msg.alert("请选择性别！");
            return false;
        } else {
            $.post("${ctx}/user/addUser",{name:name,login_name:login_name,login_pwd:login_pwd,role_id:role_id,phone:phone,sex:sex},function(d){
                if(d!=-1){
                    $('#model').modal('hide');
                    $('#teacher_table').bootstrapTable('refresh');
                    Showbo.Msg.alert('添加成功！');
                } else {
                    Showbo.Msg.alert('添加失败！');
                }
            });
        }
    }

    //查看
    function select(name,login_name,login_pwd,role_id,phone,sex){
        var name = $('#name').val(name);
        var login_name = $('#login_name').val(login_name);
        var login_pwd = $('#login_pwd').val(login_pwd);
        var role_id = $('#roleid').val(role_id);
        var phone = $('#phone').val(phone);
        var sex = $('#sex').val(sex);
        $("#qlfoot2").css("display","block");
        //$("#qlfoot2").find(".btn-main").css("display","none");
        $("#qlfoot2").children(".btn-main").css("display","none");
        $("#qlfoot1").css("display","none");
        $('#model').modal();
    }

    //打开修改模态框
    function update(id,name,login_name,login_pwd,role_id,phone,sex){
        var id = $('#id').val(id);
        var name = $('#name').val(name);
        var login_name = $('#login_name').val(login_name);
        var login_pwd = $('#login_pwd').val(login_pwd);
        var role_id = $('#roleid').val(role_id);
        var phone = $('#phone').val(phone);
        var sex = $('#sex').val(sex);
        $("#qlfoot2").css("display","block");
        $("#qlfoot2").children(".btn-main").css("display","");
        $("#qlfoot1").css("display","none");
        $('#model').modal();
    }
    function goUpdate() {
        var id = $('#id').val();
        var name = $('#name').val();
        var login_name = $('#login_name').val();
        var login_pwd = $('#login_pwd').val();
        var role_id = $('#roleid').val();
        var phone = $('#phone').val();
        var sex = $('#sex').val();
        //手机正则if(!myreg.test(***)&& phone !=""){
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
        //非法字符if(!myReg.test(***)&&***!=""){
        var myReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
        //数字
        var patrn2=/^\d{11}$/;
        if ($.isEmptyObject(name)||name.trim()=="") {
            Showbo.Msg.alert("请输入用户名称！");
            return false;
        }else if(!myReg.test(name)){
            Showbo.Msg.alert(name+"名称含有非法字符，请重新输入！");
            return false;
        }else if($.isEmptyObject(login_name)||login_name.trim()==""){
            Showbo.Msg.alert("请输登录账号！");
            return false;
        }else if(!myReg.test(login_name)&&login_name!=""){
            Showbo.Msg.alert("账号含有非法字符，请重新输入！");
            return false;
        }else if($.isEmptyObject(login_pwd)||login_pwd.trim()==""){
            Showbo.Msg.alert("请输密码！");
            return false;
        }else if(!myReg.test(login_pwd)&&login_pwd!=""){
            Showbo.Msg.alert("密码含有非法字符，请重新输入！");
            return false;
        }else if($.isEmptyObject(role_id)||role_id.trim()==""){
            Showbo.Msg.alert("请选择角色！");
            return false;
        }else if($.isEmptyObject(phone)||phone.trim()==""){
            Showbo.Msg.alert("请输手机号！");
            return false;
        }else if(!myreg.test(phone)&&phone!=""){
            Showbo.Msg.alert("手机号有误!，请重新输入！");
            return false;
        }else if($.isEmptyObject(sex)||sex.trim()==""){
            Showbo.Msg.alert("请选择性别！");
            return false;
        } else {
            $.post("${ctx}/user/updateUser",{id:id,name:name,login_name:login_name,login_pwd:login_pwd,role_id:role_id,phone:phone,sex:sex},function(d){
                if(d!=-1){
                    $('#model').modal('hide');
                    $('#teacher_table').bootstrapTable('refresh');
                    Showbo.Msg.alert('修改成功！');
                } else {
                    Showbo.Msg.alert('修改失败！');
                }
            });
        }
    }

    //删除(真)
    function goDelete(id) {
        Showbo.Msg.confirm('确定要删除吗？',function (btn) {
            if(btn=='yes'){
                $.post("${ctx}/user/goDelete",{id:id},function (d) {
                    if(d=="ajaxfail"){//ajaxfail在拦截器中返回的, 正常每个方法中都会有一个这样的判断
                        Showbo.Msg.confirm1("会话过期,请重新登录!",function(btn){
                            if(btn=="yes"){
                                window.location.href="${ctx}/sys/index";
                            }
                        });
                    }else {
                        if(d=="ok"){
                            Showbo.Msg.alert('删除成功');
                            $('#teacher_table').bootstrapTable('refresh');
                        }else {
                            Showbo.Msg.alert('删除失败');
                        }
                    }

                });
            }
        })
    }

    //批量删除
    function deleteMore() {
        var allstring = "";
        var checkbox = $("input[name='btSelectItem']:checkbox");
        var box= $("#teacher_table").bootstrapTable('getData');
        for (var i=0;i<checkbox.length;i++){
            if(checkbox[i].checked==true){
                allstring+=JSON.stringify(box[i].id)+",";
            }
        }
        if (allstring==""){
            Showbo.Msg.alert("请至少选择一条数据");
            return false;
        }else{
            Showbo.Msg.confirm('确定要批量删除吗？',function (btn) {
                if(btn=='yes'){
                    $.post("${ctx}/user/deleteMore",{DeleteIdArr:allstring},function (d) {
                        if (d=="ok" ){
                            Showbo.Msg.alert("删除成功");
                        }else{
                            Showbo.Msg.alert("删除失败");
                        }

                        $('#teacher_table').bootstrapTable('refreshOptions', {pageNumber: 1, pagesize: 7});
                    })
                }
            });
        }
    }

    //导入
    function DaoRu() {
        $("#modelImportExcel").modal();
    }
    //确定导入
    $("#btn_import").click(function () {
        var ff = document.getElementsByName("file");
        if(ff[0].value==''){
            Showbo.Msg.alert("请选择上传文件！");
            return false;
        }else{
            var newFileName = ff[0].value.split('.');
            newFileName = newFileName[newFileName.length-1];
            if(newFileName!='xlsx'&&newFileName!='xls'){
                Showbo.Msg.alert("请选择正确格式上传！");
                return false;
            }
            $("#modelImportExcel").modal("hide");
            importfrom.submit();
        }
    })


    /* 操作中的配置_Start */
    var zTreeObj;
    /* 配置中的数据 */
    function showMenu1(){
        url="${ctx}/function/queryMenu";
        $.post(
                url,
                {"roleid":function(){
                    return $('#settingId').val();
                }},
                function(data,textStatus){
                    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting,data);
                },
                "json");
    }

    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        edit:{
            drag:{
                isCopy: false,
                isMove: false
            },
            showRemoveBtn: false,
            showRenameBtn: false
        },
        check: {
            enable: true,
            nocheckInherit: true
        },
        view:{
            showLine: true,
            dblClickExpand: false,
            selectedMulti: true
        }
    };

    /* 点击配置调用模态框 */
    function setMenu(menuid) {
        $('#settingId').val(menuid);
        showMenu1();
        $('#settingModel').modal();
    }

    /* 配置中的确定 */
    function settingMenu(){
        var nodes = new Array();
        nodes = zTreeObj.getCheckedNodes(true);
        var str = "";
        for (i = 0; i < nodes.length; i++) {
            if (str != "") {
                str += ",";
            }
            str += nodes[i].id;
        }
        var id= $('#settingId').val();
        $.post("${ctx}/function/settingMenu",{id:id,str:str},function (d) {
            if(d=="ajaxfail"){
                Showbo.Msg.confirm1("会话过期,请重新登录!",function(btn){
                    if(btn=="yes"){
                        window.location.href="${ctx}/sys/index";
                    }
                });
            }else {
                if(d=="ok"){
                    Showbo.Msg.alert('配置成功');
                    $('#settingModel').modal('hide');
                }else {
                    Showbo.Msg.alert('配置失败');
                }
            }


        });
    }
    /* 操作中的配置_End */
</script>
</html>
