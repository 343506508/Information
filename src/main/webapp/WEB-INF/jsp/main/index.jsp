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

    <%@include file="../menu/common.jsp"%>
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
                        <div class="page-heading">
                                        BODY
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
</body>
</html>
