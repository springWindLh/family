<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <style type="text/css">
        .ui.comments .comment .avatar img, .ui.comments .comment img.avatar{
            height: auto;
        }
        #mainContent p img{
            max-width: 700px;
        }
    </style>
    <script type="text/javascript" src="/js/common-filters.js"></script>
</head>
<div class="ui segment">
    <div class="center">
        <h4 class="ui header">${news.title}</h4>
    </div>
    <p class="textGrey size12 margin-top10">
    <span class="margin-right10"><fmt:formatDate value="${news.createdTime}"
                                                 pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></span>
        <span>作者：${news.userName}</span>
    </p>
    <div class="ui divider"></div>
    <div id="mainContent" class="content" style="width:100%;overflow-x: scroll;">
        ${news.content}
    </div>
</div>
<div ng-app="newsApp" ng-controller="newsController">
    <div class="ui comments">
        <h3 class="ui dividing header">评论</h3>
        <div class="comment" ng-repeat="c in comments track by $index">
            <a class="avatar">
                <img src="{{c.user.avatar}}">
            </a>
            <div class="content">
                <span class="author">{{c.user.name}}</span>
                <span class="textGrey size12 margin-left10">{{c.createdTime | date:"yyyy-MM-dd HH:mm:ss" | dateFilter}}</span>
                <div class="metadata">
                    <div class="date"></div>
                </div>
                <div class="text">
                    {{c.content}}
                </div>
            </div>
        </div>
    </div>
    <form class="ui reply form">
        <c:if test="${user != null}">
        <div class="field">
            <textarea placeholder="评论内容" ng-model="comment.content"></textarea>
        </div>
        <div class="ui primary labeled icon button" ng-click="saveComment()">
            <i class="send icon"></i>发&nbsp;表
        </div>
        </c:if>
        <c:if test="${user == null}">
            <div class="field">
                <div style="width: 100%;height: 100px;padding:20px;border: solid 1px lightgrey;"> <a href="/login">登录</a>后可评论</div>
            </div>
        </c:if>
    </form>
</div>
<script type="text/javascript">
    var app = angular.module('newsApp', ['filter']);
    app.controller('newsController', function ($scope, $http) {
        $http.get('/comment/list').success(function (data) {
            $scope.comments = JSON.parse(data.data).domains;
        });
        $scope.comment = {
            content: '',
            targetType: 'NEWS',
            targetId: '${news.id}'
        };
        $scope.saveComment = function () {
            $http.post('/comment/add', $scope.comment).success(function (data) {
                if (data.code == 1) {
                    $scope.comments.unshift(data.data);
                    $scope.comment = {
                        content: '',
                        targetType: 'NEWS',
                        targetId: '${news.id}'
                    };
                }else {
                    showDynamicMsgModal('info','评论发表失败');
                }
            }).error(function () {
                showErrorMsgModal();
            });
        };
    });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>