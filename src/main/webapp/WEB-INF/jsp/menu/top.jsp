<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .top_time ul {
        list-style-type: none;
    }
    .top_time ul li {
        padding-left: 5px;
    }

    /*bootstrap按钮下拉菜单样式覆盖*/
    .dropdown-menu>li>button {
        display: block;
        width: 100%;
        text-align: left;
        padding: 3px 20px;
        clear: both;
        font-weight: 400;
        line-height: 1.42857143;
        color: #333;
        white-space: nowrap;
    }
    .dropdown-menu>li>button:focus,
    .dropdown-menu>li>button:hover {
        color: #262626;
        text-decoration: none;
        background-color: #f5f5f5;
    }

    .liantong-logo .img-logo{
        background: url("${ctx}/images/logo-liantong.svg") no-repeat left center;
        background-size: contain;
        height: 100%;
        width: 84px;
    }
    .btn-mopw:hover .fa{
        transform: rotate(45deg);
        -ms-transform:rotate(45deg); 	/* IE 9 */
        -moz-transform:rotate(45deg); 	/* Firefox */
        -webkit-transform:rotate(45deg); /* Safari 和 Chrome */
        -o-transform:rotate(45deg); 	/* Opera */
        transition: all 0.5s;
    }
    .cloudoss-logo{
        display: inline-block !important;
        height: 28px;
        vertical-align:middle;
    }

    #tips span {
        float: left;
        width: 50px;
        height: 20px;
        color: white;
        background: green;
        margin-right: 2px;
        line-height: 20px;
        text-align: center;
    }

    .modal.in .modal-dialog.modal-dialog-process .modal-content{
        position: absolute;top: 50%; left: 50%;
        transform: translateX(-50%) translateY(-50%);
        margin: 0;
        transition: none;
        z-index: 9999;
    }
</style>

<nav class="navbar navbar-red navbar-fixed-top">
    <div class="container-fluid" >
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a style="font-size: 0;" class="navbar-brand" href="#">
                <img class="cloudoss-logo" src="${ctx}/images/uwifi_logo.png"/>
                <div class="yonghu"></div>
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><p class="navbar-text" style="color: #fff;">${userSession.name}，欢迎您！</p></li>
            <li><a href="${ctx}/sys/nologin"><i  class="fa fa-sign-out"></i> 退出应用</a></li>
        </ul>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <form id="qhproject" action="${ctx}/sys/qhproject" method="post">
            <input id="qhproid" name="qhproid" type="hidden">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <%--<ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>--%>
            </div>
        </form>
    </div>
</nav>