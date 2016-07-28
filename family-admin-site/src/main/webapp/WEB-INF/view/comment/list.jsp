<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div ng-app="newsApp" ng-controller="newsController">
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
                <th class="two wide">内容</th>
                <th class="two wide">用户</th>
                <th class="two wide">创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="comment" items="${domainPage.domains}">
                <tr>
                    <td class="one wide">
                        <div class="ui checkbox">
                            <input type="checkbox" value="${comment.id}" class="itemCheckbox">
                            <label></label>
                        </div>
                    </td>
                    <td class="two wide">${comment.id}</td>
                    <td class="two wide">${comment.content}</td>
                    <td class="two wide">${comment.user.name}</td>
                    <td class="two wide"><fmt:formatDate value="${comment.createdTime}"
                                                         pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
                    <td><a href=""
                           ng-click="showDetailInfo('${comment.user.name}','${comment.createdTime}','${comment.content}')">查看</a>
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
                        <button class="ui basic circular icon button" ng-click="deleteSelecteds()"><i
                                class="trash icon"></i>
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
    <div class="ui small modal detailInfoModal">
        <i class="close icon"></i>
        <div class="header">评价详情</div>
        <div class="content">
            <div class="ui grid">
                <div class="four wide column">
                    <label class="inputLabel">用户名:</label>
                    <span>{{currentComment.userName}}</span>
                </div>
                <div class="seven wide column">
                    <label class="inputLabel">评价时间:</label>
                    <span>{{currentComment.createdTime}}</span>
                </div>
            </div>
            <div class="ten wide column margin-top10">
                <label class="inputLabel">评价内容:</label><br>
                <textarea class="margin-top10" rows="5" cols="48">{{currentComment.content}}
                </textarea>
            </div>
        </div>
        <div class="actions">
            <button class="ui basic cancel button">关闭</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    pageUrl = '/comment/list';
    var app = angular.module('newsApp', []);
    app.controller('newsController', function ($scope, $http) {
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
                $http.post('/comment/batch/delete', ids).success(function (data) {
                    if (data.code == 1) {
                        showDynamicMsgModal('success', '删除成功');
                    } else {
                        showDynamicMsgModal('failure', '删除失败');
                    }
                }).error(function () {
                    showErrorMsgModal();
                });
            };

            if (ids.length > 0) {
                $('.modal.deleteConfirmModal').modal({
                    blurring: true,
                    onDeny: function () {
                        return true;
                    },
                    onApprove: function () {
                        deleteExcute();
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    }
                }).modal('show');
            }
        };

        $scope.currentComment = {
            userName: '',
            createdTime: '',
            content: ''
        };
        $scope.showDetailInfo = function (userName, createdTime, content) {
            $scope.currentComment.userName = userName;
            $scope.currentComment.createdTime = createdTime;
            $scope.currentComment.content = content;
            $('.modal.detailInfoModal').modal({
                blurring: true,
                onDeny: function () {
                    return true;
                }
            }).modal('show');
        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>
