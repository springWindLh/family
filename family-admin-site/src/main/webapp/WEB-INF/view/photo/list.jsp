<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <link rel="stylesheet" type="text/css" href="/js/lightgallery/lightgallery.min.css" />
</head>
<div ng-app="photoApp" ng-controller="photoController">
    <div ng-if="operateImg" class="ui images" style="padding-right: 245px;">
        <c:forEach var="photo" items="${domainPage.domains}">
            <div class="ui image" style="width: 200px;margin: 10px;">
                <div class="ui checkbox" style="position: absolute;top: 5px;right: -5px;">
                    <input type="checkbox" value="${photo.id}" class="itemCheckbox">
                    <label></label>
                </div>
                <img ng-cloak src="${photo.url}" class="shadow">
            </div>
        </c:forEach>
    </div>

    <div ng-if="!operateImg" class="ui images" id="lightGallery" style="padding-right: 245px;">
        <c:forEach var="photo" items="${domainPage.domains}">
                <a href="${photo.url}">
                    <img ng-cloak src="${photo.url}" class="ui image width200 shadow" style="margin: 10px;">
                </a>
        </c:forEach>
    </div>
    <div class="list_footer" style="position:relative;margin-bottom: 20px;">
        <div style="position: relative">
            <div class="ui divider"></div>
            <a class="ui button red" href="/photo/add">上传相片</a>
            <jsp:include page="../pager.jsp"></jsp:include>
            <div style="display: inline;margin-left: 10px;">
            <button class="ui basic circular icon button margin-right10" ng-click="configureImg()"><i class="configure middle icon"></i>
            </button>
                <div ng-if="operateImg" class="ui checkbox margin-left10 margin-right10">
                    <input type="checkbox" ng-click="checkAll()" ng-checked="globleChecked">
                    <label>全选</label>
                </div>
                <button ng-if="operateImg" class="ui basic circular icon button margin-left10" ng-click="deleteSelecteds()"><i class="trash icon"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<div class="ui small modal deleteConfirmModal">
    <i class="close icon"></i>
    <div class="header">温馨提示</div>
    <div class="content">
        <p>确认要删除已选中的项？</p>
    </div>
    <div class="actions">
        <button class="ui basic cancel button">取消</button>
        <button class="ui ok green button">确认</button>
    </div>
</div>
<script type="text/javascript" src="/js/lightgallery/lightgallery.min.js"></script>
<script type="text/javascript" src="/js/lightgallery/lg-thumbnail.min.js"></script>
<script type="text/javascript" src="/js/lightgallery/lg-fullscreen.min.js"></script>
<script type="text/javascript">
    pageUrl = '/photo/list';
    $(function () {
        $('#lightGallery').lightGallery();
    });
    var app = angular.module('photoApp', []);
    app.controller('photoController', function ($scope, $http) {
        $scope.operateImg = false;
        $scope.configureImg = function(){
            $scope.operateImg = !$scope.operateImg;
        };
        $scope.globleChecked = false;
        $scope.checkAll = function () {
            if ($scope.globleChecked == false) {
                $scope.globleChecked = true;
                $('.itemCheckbox').prop('checked', true);
            } else {
                $scope.globleChecked = false;
                $('.itemCheckbox').prop('checked', false);
            }
        };
        $scope.deleteSelecteds = function () {
            var ids = [];
            angular.forEach($('.itemCheckbox:checked'), function (target) {
                ids.push($(target).val());
            });

            var deleteExcute = function () {
                $http.post('/photo/batch/delete', ids).success(function (data) {
                    if (data.code == 1) {
                        showDynamicMsgModal('success','删除成功');
                    } else {
                        showDynamicMsgModal('failure','删除失败');
                    }
                }).error(function () {
                    showErrorMsgModal();
                });
            };
            if (ids.length > 0){
                $('.modal.deleteConfirmModal').modal({
                    blurring: true,
                    onDeny: function () {
                        return true;
                    },
                    onApprove: function () {
                        deleteExcute();
                        setTimeout(function () {
                            location.reload();
                        },1000);
                    }
                }).modal('show');
            }

        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>