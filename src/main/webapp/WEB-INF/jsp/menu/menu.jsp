<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 子菜单的menuUtil_Pid_Mid --%>
<%@ page import="com.project.tools.MenuUtil" %>

<div class="left-side sticky-left-side">
    <div class="uwifi-logo text-center">
        <img src="${ctx}/images/uwifi_logo.png"/>
    </div>
    <div class="left-side-inner">
        <!--sidebar nav start-->
        <ul class="nav nav-pills nav-stacked custom-nav">
            <li ><a href="${ctx}/main/index"><i class="fa fa-home"></i> <span>首页</span></a></li>
            <c:forEach var="parentMenu" items="${menuList}">
                <li <c:if test="${parentMenu.parentList.id!=menuUtil.pid}"> class="menu-list" </c:if> <c:if test="${parentMenu.parentList.id==menuUtil.pid}"> class="menu-list nav-active" </c:if>><a href="#"><i class="${parentMenu.parentList.icon}"></i> <span>${parentMenu.parentList.name}</span></a>
                    <ul class="sub-menu-list" >
                        <c:forEach var="childrenMenu" items="${parentMenu.childrenList}">
                           <li <c:if test="${childrenMenu.id==menuUtil.mid}">class="active"</c:if>><a href="${ctx}/${childrenMenu.url}?mid=${childrenMenu.id}&pid=${childrenMenu.pid}">${childrenMenu.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
            <li><a href="${ctx}/sys/nologin"><i class="fa fa-sign-in"></i> <span>退出登录</span></a></li>

        </ul>
        <!--sidebar nav end-->

    </div>
    <div id="desriDiv" class="left-side-inner text-center" style="width:100%; color:#ccc; position: absolute;bottom: 25px;">
        <div id="rightDesri">copyright@Chinaunicom<br />2017-2022</div>
        <div id="timeDesri"></div>
    </div>
</div>
<%@include file="../menu/common.jsp"%><%-- 只因为 '$'  --%>
<script>
    function showTime(){
        $("#timeDesri").html((new Date()).toLocaleString());
    }
    setInterval("showTime()",1000);
</script>