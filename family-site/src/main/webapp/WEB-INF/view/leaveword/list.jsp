<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>

<div class="ui relaxed divided list">
    <c:forEach var="leaveword" items="${domainPage.domains}">
        <div class="item">
            <img class="ui middle avatar image" src="${leaveword.user.avatar}">
            <div class="content">
                <span class="header" style="display: inline;">${leaveword.user.name}</span>
                <span class="margin-left20 size12 textGrey">
                    <fmt:formatDate value="${leaveword.createdTime}"
                                    pattern="yyyy-MM-dd hh:mm"></fmt:formatDate>
                </span>
                <div class="description margin-top10">
                        ${leaveword.content}
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="margin-bottom20 list_footer" style="text-align: right">
    <div class="ui divider"></div>
    <jsp:include page="../pager.jsp"></jsp:include>
</div>
<div ng-app="leavewordApp" ng-controller="leavewordController">
    <form class="ui reply form">
        <c:if test="${user != null}">
        <div class="field">
            <textarea ng-model="leaveword.content" placeholder="留言内容"></textarea>
        </div>
        <div class="ui red labeled icon button" ng-click="saveLeaveword()">
            <i class="send icon"></i>提&nbsp;交&nbsp;留&nbsp;言
        </div>
        </c:if>
        <c:if test="${user == null}">
            <div class="field">
                <div style="width: 100%;height: 100px;padding:20px;border: solid 1px lightgrey;"> <a href="/login">登录</a>后可留言</div>
            </div>
        </c:if>
    </form>
</div>
<script type="text/javascript">
    pageUrl = '/leaveword/list';
    var app = angular.module('leavewordApp', []);
    app.controller('leavewordController', function ($scope, $http) {
        $scope.leaveword = {
            content: ''
        };
        $scope.saveLeaveword = function () {
            $http.post('/leaveword/add', $scope.leaveword).success(function (data) {
                if (data.code == 1) {
                    showDynamicMsgModal('success', '留言发表成功');
                    setTimeout(function () {
                        location.reload();
                    }, 1000);
                } else {
                    showDynamicMsgModal('info', '留言发表失败');
                }
            }).error(function () {
                showErrorMsgModal();
            });
        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>