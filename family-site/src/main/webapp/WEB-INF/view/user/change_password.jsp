<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div class="ui grid" ng-app="userApp" ng-controller="userController">
    <form class="ui eight wide column form" name="form">
        <h3 class="ui dividing header"><span class="backArrow"><i class="small link red arrow left icon" onclick="javascript:history.go(-1)"></i></span> 编辑新闻</h3>

        <div class="field" ng-class="{'error':form.password.$dirty && form.password.$invalid}">
            <label class="inputLabel">新密码</label>
            <input type="password" placeholder="新密码" name="password" required minlength="1" maxlength="32" ng-model="user.password">
            <div class="ui basic red pointing prompt label" ng-if="form.password.$dirty && form.password.$invalid">
                不能为空，且密码长度为1-32
            </div>
        </div>
        <div class="field" ng-class="{'error':form.repeatPwd.$dirty && form.repeatPwd.$invalid}">
            <label class="inputLabel">重复密码</label>
            <input type="password" placeholder="重复密码" name="repeatPwd" required minlength="1" maxlength="32" ng-model="user.repeatPwd">
            <div class="ui basic red pointing prompt label" ng-if="form.repeatPwd.$dirty && form.repeatPwd.$invalid">
                不能为空，且密码长度为1-32位
            </div>
        </div>
        <div class="field">
            <button type="button" ng-click="saveData()" ng-disabled="form.$invalid" class="ui red button">保&nbsp;存</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    var app = angular.module('userApp', []);
    app.controller('userController', function ($scope, $http) {

        $scope.saveData = function () {
            if($scope.user.repeatPwd != $scope.user.password){
                showDynamicMsgModal('info','密码输入不一致');
            }else{
                $http.post('/user/change/password', $scope.user.password).success(function (data) {
                    if (data.code == 1) {
                        showOkMsgModal();
                    } else {
                        showFailMsgModal();
                    }
                }).error(function () {
                    showErrorMsgModal();
                });
            }
        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>