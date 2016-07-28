<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.all.min.js"></script>
</head>
<div class="container">
    <div class="ui grid" ng-app="singleDetailApp" ng-controller="singleDetailController">
        <form class="ui eight wide column form">
            <h3 class="ui dividing header"><span class="backArrow"><i class="small link red arrow left icon" onclick="javascript:history.go(-1)"></i></span>编辑详情</h3>
            <div class="field">
                <label class="inputLabel">目录名称</label>
                <h3 class="margin-top10"><c:out value="${menuName}"></c:out></h3>
            </div>
            <div class="field">
            <label class="inputLabel">内容详情</label>
            <textarea id="container" name="container">{{singleDetail.content}}
            </textarea>
            </div>
            <div class="field">
                <button type="button" ng-click="saveData()" ng-disabled="form.$invalid" class="ui red button">保&nbsp;存</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var ue = UE.getEditor("container");
    var app = angular.module('singleDetailApp', []);
    app.controller('singleDetailController', function ($scope, $http) {
        $scope.singleDetail = {
            id: '${singleDetailForm.id}',
            content: '${singleDetailForm.content}',
            menuId: '${singleDetailForm.menuId==null ? menuId :singleDetailForm.menuId}'
        };

        function checkForm() {
            if ($scope.singleDetail.content == '') {
                showDynamicMsgModal('info','请填写内容详情');
                return false;
            }
            return true;
        };
        $scope.saveData = function () {
            $scope.singleDetail.content = ue.getContent();
            if (!checkForm()) {
                return;
            }
            var postUrl = $scope.singleDetail.id == '' ? '/singleMenu/detail/add' : '/singleMenu/detail/edit';
            $http.post(postUrl, $scope.singleDetail).success(function (data) {
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