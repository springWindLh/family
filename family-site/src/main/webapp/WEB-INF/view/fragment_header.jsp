<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>氏家族网</title>
    <link rel="icon" href="/static/image/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/css/base.css">
    <link rel="stylesheet" type="text/css" href="/css/semantic.min.css">
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/semantic.min.js"></script>
    <script type="text/javascript" src="/js/angular.min.js"></script>
    <script type="text/javascript" src="/js/angular-sanitize.min.js"></script>
</head>
<style type="text/css">
    body {
        background: url("http://o6p6h6085.bkt.clouddn.com/background.jpg") no-repeat center;
        background-size:cover;
    }
    .ui.container{
        /*background: rgba(233,218,175,.5);*/
        background-color: white;
        border: 10px solid #660000;
        /*padding: 10px;*/
    }
    .ui.vertical.tabular.menu .active.item {
        background: #993333;
        color: lightgrey;
    }
    .ui.vertical.tabular.menu .item {
        background: #993333;
        color: #e9cb73;
        text-shadow: 1px 1px 2px black;
    }
    .ui.pointing.dropdown>.menu:after{
        background: none;
    }
    .ui.dropdown .menu{
        background: #993333;
    }
    .ui.dropdown .menu>.item a{
        color: white !important;
    }
    .ui.sticky{
        background: #993333;
        box-shadow: 2px 2px 2px grey;
    }
</style>
<body>
<div class="ui container">
    <div>
    <img src="http://o6p6h6085.bkt.clouddn.com/banner.jpg" class="widthFull height200" style="
    border-bottom: 3px solid #660000;">
    </div>
    <div>
        <img src="http://o6p6h6085.bkt.clouddn.com/banner_border4.jpg" class="widthFull height30">
    </div>
    <div class="ui grid padded">
        <div class="four wide column">
            <div class="ui sticky">
                <c:if test="${sessionScope.user!=null}">
                <div class="ui segment" style="background: #993333">
                    <div>
                        <img class="ui circular image width100 height100 toCenter" src="${sessionScope.user.avatar}">
                    </div>
                    <div class="ui top center pointing dropdown link margin20 userModule">
                        <span class="text" style="color: white !important;padding-left: 55px;">${sessionScope.user.name}</span>
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <div class="item">
                                <a href="/user/edit/${sessionScope.user.id}" class="textBlack"><i class="user icon"></i>
                                    个人信息</a>
                            </div>
                            <div class="item">
                                <a href="/login/out" class="textBlack"> <i class="sign out icon"></i>
                                    退出</a>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>
                <div class="ui vertical fluid tabular menu aligned center">
                    <a id="menu_index" class="item" href="/index">首页</a>
                    <a id="menu_news" class="item" href="/news/list">新闻动态</a>
                    <c:forEach var="sm" items="${singleMenus}">
                        <c:if test="${not empty menuName and sm.name == menuName}">
                    <a class="item active" href="/singleMenu/detail/${sm.detail.id}">${sm.name}</a>
                        </c:if>
                        <c:if test="${sm.name != menuName}">
                            <a class="item" href="/singleMenu/detail/${sm.detail.id}">${sm.name}</a>
                        </c:if>
                    </c:forEach>
                    <a id="menu_photo" class="item" href="/photo/list">家族相册</a>
                    <a id="menu_leaveword" class="item" href="/leaveword/list">网站留言</a>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $('.userModule').dropdown();
        </script>
        <div id="context" class="twelve wide stretched column" style="border-left: 1px dotted #993333;">
