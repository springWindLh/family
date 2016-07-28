<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div>
    <form class="ui form" action="/user/list" method="get">
        <div class="inline fields">
            <div class="two wide field minWidth200">
                <input placeholder="用户名" name="name" value="${query.name}">
            </div>
            <div class="one wide field">
                <button type="submit" class="ui basic circular icon button">
                    <i class="icon search"></i>
                </button>
            </div>
        </div>
    </form>
</div>
<div ng-app="userApp" ng-controller="userController">
    <table class="ui grey table">
        <thead>
        <tr>
            <th class="one wide">
                <div class="ui checkbox">
                    <input type="checkbox" ng-click="checkAll()" ng-checked="globleChecked">
                    <label>全选</label>
                </div>
            </th>
            <th class="two wide">编号</th>
            <th class="two wide">用户名</th>
            <th class="two wide">简介</th>
            <th class="two wide">角色</th>
            <th class="two wide">创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${domainPage.domains}">
            <tr>
                <td class="one wide">
                    <div class="ui checkbox">
                        <input type="checkbox" value="${user.id}" class="itemCheckbox">
                        <label></label>
                    </div>
                </td>
                <td class="two wide">${user.id}</td>
                <td class="two wide">${user.name}</td>
                <td class="two wide">${user.introduce}</td>
                <td class="two wide">${user.role.value}</td>
                <td class="two wide"><fmt:formatDate value="${user.createdTime}"
                                                     pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
                <td><a href="/user/edit/${user.id}">查看/编辑</a></td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <c:if test="${domainPage.domainTotal>0}">
            <tr>
                <th colspan="3">
                    <jsp:include page="../pager.jsp"></jsp:include>
                </th>
                <th colspan="4">
                    <button class="ui basic circular icon button" ng-click="deleteSelecteds()"><i class="trash icon"></i>
                    </button>
                </th>
            </tr>
        </c:if>
        </tfoot>
    </table>
</div>
<div class="list_footer">
    <div class="ui divider"></div>
    <a class="ui button red" href="/user/add">添加</a>
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
<script type="text/javascript">
    pageUrl = '/user/list';
    var app = angular.module('userApp', []);
    app.controller('userController', function ($scope, $http) {
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
                $http.post('/user/batch/delete', ids).success(function (data) {
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