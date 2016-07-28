<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>管理平台</title>
    <link rel="icon" href="/static/image/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/css/base.css">
    <link rel="stylesheet" type="text/css" href="/css/semantic.min.css">
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/semantic.min.js"></script>
    <script type="text/javascript" src="/js/angular.min.js"></script>
    <script type="text/javascript" src="/js/angular-sanitize.min.js"></script>
</head>
<style>
    .list_footer {
        position: absolute;
        bottom: 20px;
        width: 100%;
    }
</style>
<body>

<div class="ui left visible sidebar vertical large labeled icon menu">
    <div>
        <img class="ui circular image width100 height100 toCenter shadow" src="${sessionScope.userAvatar}">
    </div>
    <div class="ui top center pointing dropdown link margin20">
        <span class="text">${sessionScope.user.name}</span>
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
    <div class="ui fitted divider"></div>

    <a id="menu_news" class="item" href="/news/list"><i class="newspaper icon"></i> 新闻</a>
    <a id="menu_singleMenu" class="item" href="/singleMenu/list"><i class="bars icon"></i>菜单</a>
    <a id="menu_user" class="item" href="/user/list"><i class="users icon"></i>用户</a>
    <a id="menu_leaveword" class="item" href="/leaveword/list"><i class="comments icon"></i>留言</a>
    <a id="menu_comment" class="item" href="/comment/list"><i class="comments icon"></i>评论</a>
    <a id="menu_photo" class="item" href="/photo/list"><i class="picture icon"></i>相册</a>
</div>
<script type="text/javascript">
    var url = location.href;
    if (url.indexOf('news') != -1) {
        $('#menu_news').addClass('active');
    } else if (url.indexOf('singleMenu') != -1) {
        $('#menu_singleMenu').addClass('active');
    } else if (url.indexOf('leaveword') != -1) {
        $('#menu_leaveword').addClass('active');
    }else if (url.indexOf('photo') != -1) {
        $('#menu_photo').addClass('active');
    } else if (url.indexOf('user') != -1){
        $('#menu_user').addClass('active');
    }else if (url.indexOf('comment') != -1){
        $('#menu_comment').addClass('active');
    }

    $('.ui.dropdown').dropdown();
</script>
<div class="pusher heightFull">
    <div class="padding20">