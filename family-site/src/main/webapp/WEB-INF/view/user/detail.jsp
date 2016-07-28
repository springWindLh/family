<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <style type="text/css">
        .user_avatar{
            position: relative;
        }
        .user_avatar .button{
            position: absolute;top:10px;width: 100%;
        }
        .user_avatar_add{
            position: absolute;top:18px;left:20px;opacity: 0;width: 100%;
        }
        .user_avatar input{
            height: 50px;
        }
    </style>
    <script type="text/javascript" src="/js/webuploader/webuploader.html5only.min.js"></script>
</head>
<div class="ui grid" ng-app="userApp" ng-controller="userController">
    <form class="ui eight wide column form" name="form">
        <div class="field">
            <label class="inputLabel">头像</label>
            <div class="ui segment" ng-style="{height: user.avatar != '' ? '280px' : '80px'}">
                <img ng-if="user.avatar != ''" class="ui image width300 height200" src="{{user.avatar}}">
                <div class="user_avatar">
                    <button class="ui basic button">上传头像</button>
                    <div class="user_avatar_add">uploader</div>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>

        <div class="field" ng-class="{'error':form.name.$dirty && form.name.$invalid}">
            <label class="inputLabel">用户名</label>
            <input placeholder="用户名" name="name" required minlength="1" maxlength="20" ng-model="user.name">
            <div class="ui basic red pointing prompt label" ng-if="form.name.$dirty && form.name.$invalid">
                不能为空，且名称长度为1-20位
            </div>
        </div>
        <div class="field" ng-class="{'error':form.password.$dirty && form.password.$invalid}">
            <label class="inputLabel">密码</label>
            <input type="password" placeholder="密码" name="password" required minlength="1" maxlength="32"
                   ng-model="user.password">
            <div class="ui basic red pointing prompt label" ng-if="form.password.$dirty && form.password.$invalid">
                不能为空，且密码长度为1-32
            </div>
        </div>
        <div class="field" ng-class="{'error':form.repeatPwd.$dirty && form.repeatPwd.$invalid}">
            <label class="inputLabel">重复密码</label>
            <input type="password" placeholder="重复密码" name="repeatPwd" required minlength="1" maxlength="32"
                   ng-model="user.repeatPwd">
            <div class="ui basic red pointing prompt label" ng-if="form.repeatPwd.$dirty && form.repeatPwd.$invalid">
                不能为空，且密码长度为1-32位
            </div>
        </div>
        <div class="field" ng-class="{'error':form.introduce.$dirty && form.introduce.$invalid}">
            <label class="inputLabel">自我简介</label>
            <textarea placeholder="自我简介" name="introduce" ng-model="user.introduce" maxlength="200"></textarea>
            <div class="ui basic red pointing prompt label" ng-if="form.introduce.$dirty && form.introduce.$invalid">
                内空长度不能超过200位
            </div>
        </div>
        <div class="field">
            <button type="button" ng-click="saveData()" ng-disabled="form.$invalid" class="ui red button">保&nbsp;存
            </button>
        </div>
    </form>
</div>


<script type="text/javascript">
    var app = angular.module('userApp', []);
    app.controller('userController', function ($scope, $http) {
        $scope.user = {
            id: '${userForm.id}',
            name: '${userForm.name}',
            password: '${userForm.password}',
            repeatPwd: '${userForm.password}',
            oldPwd: '${userForm.oldPwd}',
            introduce: '${userForm.introduce}',
            avatar: "${not empty userForm.avatar ? userForm.avatar: defaultAvatar}",
            role: "${userForm.role == null ? 'USER' : userForm.role}"
        };

        $scope.saveData = function () {
            if ($scope.user.repeatPwd != $scope.user.password) {
                showDynamicMsgModal('info', '密码输入不一致');
            } else {
                var postUrl = $scope.user.id == '' ? '/user/add' : '/user/edit';
                $http.post(postUrl, $scope.user).success(function (data) {
                    if (data.code == 1) {
                        showOkMsgModal();
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    } else {
                        showFailMsgModal();
                    }
                }).error(function () {
                    showErrorMsgModal();
                });
            }
        };

        //头像上传
        var uploader = WebUploader.create({
            auto: true,
            server: '/qiniu/upload/avatar',
            pick: '.user_avatar_add',
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
            },
            fileSingleSizeLimit: 5 * 1024 * 1024
        });
        uploader.on('uploadStart',function(){
            $scope.disabled = true;
        });
        uploader.on( 'uploadSuccess', function( file , response) {
            if(response.code == 1){
                $scope.user.avatar = response.data;
                $scope.$apply();
            }else{
                showDynamicMsgModal('info',response.msg);
            }
        });
        uploader.on('error',function(type) {
            if("F_EXCEED_SIZE" == type) {
                showDynamicMsgModal('info','图片大小不能超过5M');
            } else {
                showDynamicMsgModal('info','图片格式不正确');
            }
        });
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>