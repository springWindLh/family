<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="app">
<head>
    <meta charset="utf-8">
    <title>氏家族网</title>
    <link rel="icon" href="/static/image/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/css/base.css">
    <link rel="stylesheet" type="text/css" href="/css/semantic.min.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/semantic.min.js"></script>
    <script type="text/javascript" src="/js/angular.min.js"></script>
    <script type="text/javascript" src="/js/angular-sanitize.min.js"></script>
    <style type="text/css">

        body{
            position: absolute;
            width: 100%;
            height:100%;
            background: url("http://o6p6h6085.bkt.clouddn.com/background.jpg") no-repeat center;
            background-size: cover;
        }
        .ui.grid{
            position: relative;
            top: 50%;
            margin-top: -220px;
        }
        .ui.form input:not([type]), .ui.form input[type=text], .ui.form input[type=email], .ui.form input[type=search], .ui.form input[type=password], .ui.form input[type=date], .ui.form input[type=datetime-local], .ui.form input[type=tel], .ui.form input[type=time], .ui.form input[type=url], .ui.form input[type=number]{
            background: rgba(255,255,255,.5);
        }
        .ui.form input:not([type]):focus, .ui.form input[type=text]:focus, .ui.form input[type=email]:focus, .ui.form input[type=search]:focus, .ui.form input[type=password]:focus, .ui.form input[type=date]:focus, .ui.form input[type=datetime-local]:focus, .ui.form input[type=tel]:focus, .ui.form input[type=time]:focus, .ui.form input[type=url]:focus, .ui.form input[type=number]:focus{
            background: rgba(255,255,255,.5);
        }
    </style>
    <script>

    </script>
</head>
<body>
<div class="ui twelve wide column middle aligned centered very relaxed stackable grid" style="background: rgba(255,255,255,.5);">
    <div ng-controller="loginController" class="six wide column maxWidth400 height400">
        <form class="ui large form" name="loginForm">
            <div class="field text aligned center">
                <h2 class="ui brown header" style="letter-spacing:5px;text-shadow: 1px 1px 1px black; ">练氏家族网</h2>
            </div>
            <div class="field" ng-class="{'error':loginForm.name.$dirty && loginForm.name.$invalid}">
                <div class="ui left icon input">
                    <i class="user icon"></i>
                    <input placeholder="用户名" required ng-model="user.name">
                    <div  class="ui basic red pointing prompt label"
                          ng-if="loginForm.name.$dirty && registerForm.name.$invalid">
                        请填写用户名
                    </div>
                </div>
            </div>
            <div class="field" ng-class="{'error':loginForm.password.$dirty && loginForm.password.$invalid}">
                <div class="ui left icon input">
                    <i class="lock icon"></i>
                    <input type="password" required placeholder="密码" ng-model="user.password">
                    <div  class="ui basic red pointing prompt label"
                          ng-if="registerForm.password.$dirty && registerForm.password.$invalid">
                        请输入密码
                    </div>
                </div>
            </div>
            <div class="field">
                <button type="button" ng-click="login()" ng-disabled="loginForm.$invalid" class="ui fluid brown button">登&nbsp;录</button>
            </div>
            <div ng-show="loginErrorMsgShow" class="ui visible error message">
                <i ng-click="closeLoginMsg($event.target)" class="close icon"></i>
                {{loginErrorMsg}}
            </div>
        </form>
    </div>
    <div class="ui vertical divider">OR</div>
    <div ng-controller="registerController" class="six wide column maxWidth400 height400">
        <form class="ui large form" name="registerForm">
            <div class="field" ng-class="{'error':registerForm.name.$dirty && registerForm.name.$invalid}">
                <input placeholder="用户名" name="name" required minlength="1" maxlength="20" ng-model="user.name">
                <div  class="ui basic red pointing prompt label"
                     ng-if="registerForm.name.$dirty && registerForm.name.$invalid">
                    不能为空，且名称长度为1-20位
                </div>
            </div>
            <div class="field" ng-class="{'error':registerForm.password.$dirty && registerForm.password.$invalid}">
                <input type="password" placeholder="密码" name="password" required minlength="1" maxlength="32"
                       ng-model="user.password">
                <div  class="ui basic red pointing prompt label"
                     ng-if="registerForm.password.$dirty && registerForm.password.$invalid">
                    不能为空，且密码长度为1-32
                </div>
            </div>
            <div class="field" ng-class="{'error':registerForm.repeatPwd.$dirty && registerForm.repeatPwd.$invalid}">
                <input type="password" placeholder="重复密码" name="repeatPwd" required minlength="1" maxlength="32"
                       ng-model="user.repeatPwd">
                <div  class="ui basic red pointing prompt label"
                     ng-if="registerForm.repeatPwd.$dirty && registerForm.repeatPwd.$invalid">
                    不能为空，且密码长度为1-32位
                </div>
            </div>
            <div class="field">
                <button type="button" ng-click="register()" ng-disabled="registerForm.$invalid" class="ui fluid brown button">注&nbsp;册</button>
            </div>
            <div ng-show="registerErrorMsgShow" class="ui visible error message">
                <i ng-click="closeRegisterMsg($event.target)" class="close icon"></i>
                {{registerErrorMsg}}
            </div>
        </form>
    </div>
</div>
<div style="position: absolute;text-align: center;font-size:10px;bottom: 20px;width:inherit;color: brown;rgba(101,97,78)">
   <p> Copyright © 练氏家族网 All Rights Reserved</p>
    <p>站长：练小龙&nbsp;&nbsp;&nbsp;QQ:954513897&nbsp;&nbsp;&nbsp;手机：18280116335</p>
</div>
</body>
<script>
    var app = angular.module('app',[]);
    app.controller('loginController',function ($scope,$http) {
        $scope.user={
            name:'',
            password:'',
            jumpUrl:'${jumpUrl}'
        };
        $scope.loginErrorMsg='';
        $scope.loginErrorMsgShow = false;
        $scope.login=function () {
            $http.post('login',$scope.user).success(function (data) {
                if(data.code == 1){
                    window.location.href = data.jumpUrl;
                }else{
                    $scope.loginErrorMsg = data.msg;
                    $scope.loginErrorMsgShow = true;
                }
            });
        };
        $scope.closeLoginMsg=function (target) {
            $scope.loginErrorMsgShow = false;
        }

    });

    app.controller('registerController',function ($scope,$http) {
        $scope.registerErrorMsg='';
        $scope.registerErrorMsgShow = false;
        $scope.register=function () {
            $scope.registerErrorMsg='';
            $scope.registerErrorMsgShow = false;
            if($scope.user.password != $scope.user.repeatPwd){
                $scope.registerErrorMsg='密码输入不一致';
                $scope.registerErrorMsgShow = true;
            }else{
                $http.post('register',$scope.user).success(function (data) {
                    if(data.code == 1){
                        window.location.href = data.jumpUrl;
                    }else{
                        $scope.registerErrorMsg = data.detail;
                        $scope.registerErrorMsgShow = true;
                    }
                });
            }

        };
        $scope.closeRegisterMsg=function (target) {
            $scope.registerErrorMsgShow = false;
        }
    });

</script>
</html>