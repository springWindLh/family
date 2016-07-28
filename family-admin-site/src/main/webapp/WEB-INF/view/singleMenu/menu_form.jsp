<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div class="container">
    <div class="ui grid" ng-app="singleMenuApp" ng-controller="singleMenuController">
        <form class="ui five wide column form" name="form">
            <h3 class="ui dividing header"><span class="backArrow"><i class="small link red arrow left icon" onclick="javascript:history.go(-1)"></i></span> 编辑目录</h3>
            <div class="field" ng-class="{'error':form.name.$dirty && form.name.$invalid}">
                <label class="inputLabel">目录名称</label>
                <input name="name" placeholder="目录名称" ng-model="singleMenu.name" required minlength="1" maxlength="4">
                <div class="ui basic red pointing prompt label" ng-if="form.name.$dirty && form.name.$invalid">
                    不能为空，且标题长度为1-4位
                </div>
            </div>
            <div class="field" ng-class="{'error':form.weight.$dirty && form.weight.$invalid}">
                <label class="inputLabel">权重</label>
              <input placeholder="权重" name="weight" ng-model="singleMenu.weight" required ng-pattern="/^([1-9]\d{0,2}$|0)$/">
                <div class="ui basic red pointing prompt label" ng-if="form.weight.$dirty && form.weight.$invalid">
                    权重不能为空，且只能为0-999的整数
                </div>
            </div>
            <div class="field">
                <button type="button" ng-disabled="form.$invalid" ng-click="saveData()" class="ui red button">保&nbsp;存</button>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript">

    var app = angular.module('singleMenuApp', []);
    app.controller('singleMenuController', function ($scope, $http) {
       $scope.singleMenu = {
            id: '${singleMenuForm.id}',
            name: '${singleMenuForm.name}',
            weight: '${singleMenuForm.weight}',
            status: '${singleMenuForm.status}' || 'OFFLINE',
        };

        $scope.saveData = function () {
            var postUrl = $scope.singleMenu.id == '' ? '/singleMenu/add' : '/singleMenu/edit';
            $http.post(postUrl, $scope.singleMenu).success(function (data) {
                if (data.code == 1) {
                    showOkMsgModal();
                    setTimeout(function () {
                        location.href = data.jumpUrl;
                    },1000);
                } else {
                    showFailMsgModal();
                }
            }).error(function () {
                showErrorMsgModal();
            });
        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>
