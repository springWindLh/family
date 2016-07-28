<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="loginApp">
<head>
    <meta charset="utf-8">
    <title>管理平台</title>
    <link rel="icon" href="/static/image/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/css/base.css">
    <link rel="stylesheet" type="text/css" href="/css/semantic.min.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/semantic.min.js"></script>
    <script type="text/javascript" src="/js/angular.min.js"></script>
    <script type="text/javascript" src="/js/angular-sanitize.min.js"></script>
    <style type="text/css">
        body > .grid {
            height: 100%;
        }
        .form_content{
            max-width: 400px;
            min-width: 400px;
        }
    </style>
</head>
<body ng-controller="loginController">
<div class="ui middle aligned center aligned grid">
    <div class="six wide column form_content">
        <h2 class="ui teal image header">
            <div class="content">练氏家族网---管理平台</div>
        </h2>
        <form class="ui large form">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input placeholder="用户名" ng-model="user.name">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" placeholder="密码" ng-model="user.password">
                    </div>
                </div>
                <div class="field">
                    <button type="button" ng-click="login()" class="ui fluid teal button">登录</button>
                </div>
            </div>
            <div ng-show="errorMsgShow" class="ui visible error message">
                <i ng-click="closeMsg($event.target)" class="close icon"></i>
                {{errorMsg}}
            </div>
        </form>
    </div>
</div>
</body>
<script>
    var app = angular.module('loginApp',[]);
    app.controller('loginController',function ($scope,$http) {
        $scope.user={
            name:'',
            password:''
        };
        $scope.errorMsg='';
        $scope.errorMsgShow = false;
        $scope.login=function () {
            $http.post('login',$scope.user).success(function (data) {
                if(data.code == 1){
                    window.location.href = data.jumpUrl;
                }else{
                    $scope.errorMsg = data.msg;
                    $scope.errorMsgShow = true;
                }
            });
        };
        $scope.closeMsg=function (target) {
            $scope.errorMsgShow = false;
        }
    });
</script>
</html>