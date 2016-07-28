<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div class="container" ng-app="leavewordApp" ng-controller="leavewordController">
    <div>
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
                <th class="two wide">留言内容</th>
                <th class="two wide">创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="word" items="${domainPage.domains}">
                <tr>
                    <td class="one wide">
                        <div class="ui checkbox">
                            <input type="checkbox" value="${word.id}" class="itemCheckbox">
                            <label></label>
                        </div>
                    </td>
                    <td class="two wide">${word.id}</td>
                    <td class="two wide">${word.user.name}</td>
                    <td class="two wide">${word.content}</td>
                    <td class="two wide"><fmt:formatDate value="${word.createdTime}"
                                                         pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
                    <td>
                        <a class="ui link" ng-click="lookDetail('${word.content}')">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <c:if test="${domainPage.domainTotal>0}">
                <tr>
                    <th colspan="3">
                        <jsp:include page="../pager.jsp"></jsp:include>
                    </th>
                    <th colspan="3">
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
    <div class="ui modal detailModal">
        <i class="close icon"></i>
        <div class="header">详细内容</div>
        <div class="content">
            <textarea class="widthFull" rows="5">{{detailContent}}</textarea>
        </div>
        <div class="actions">
            <button class="ui basic cancel button">关闭</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    pageUrl = '/leaveword/list';
    var app = angular.module('leavewordApp', []);
    app.controller('leavewordController', function ($scope, $http) {
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
                $http.post('/leaveword/batch/delete', ids).success(function (data) {
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

        $scope.detailContent = '';
        $scope.lookDetail = function(content){
            $scope.detailContent = content;
            $('.modal.detailModal').modal({
                onDeny: function () {
                    return true;
                }
            }).modal('show');
        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>