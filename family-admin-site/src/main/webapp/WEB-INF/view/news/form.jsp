<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <style type="text/css">
        .roll_img{
            position: relative;
        }
        .roll_img .button{
            position: absolute;top:10px;width: 100%;
        }
        .roll_img_add{
            position: absolute;top:18px;left:20px;opacity: 0;width: 100%;
        }
        .roll_img input{
            cursor: pointer;width: 100%;
        }
    </style>
    <script type="text/javascript" src="/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="/js/webuploader/webuploader.html5only.min.js"></script>

</head>
<div class="ui grid" ng-app="newsApp" ng-controller="newsController">
    <form class="ui eight wide column form" name="form">
        <h3 class="ui dividing header"><span class="backArrow"><i class="small link red arrow left icon"
                                                                  onclick="javascript:history.go(-1)"></i></span> 编辑新闻
        </h3>
        <div class="field" ng-class="{'error':form.title.$dirty && form.title.$invalid}">
            <label class="inputLabel">新闻标题</label>
            <input placeholder="新闻标题" name="title" required minlength="1" maxlength="20" ng-model="news.title">
            <div class="ui basic red pointing prompt label" ng-if="form.title.$dirty && form.title.$invalid">
                不能为空，且标题长度为1-20位
            </div>
        </div>
        <div class="inline fields">
            <label class="inputLabel">状态：</label>
            <div class="field">
                <div class="ui radio checkbox">
                    <input type="radio" name="status" value="ONLINE" ng-model="news.status">
                    <label>上线</label>
                </div>
                <div class="ui radio checkbox">
                    <input type="radio" name="status" value="OFFLINE" ng-model="news.status">
                    <label>下线</label>
                </div>
            </div>
        </div>
        <div class="inline fields">
            <label class="inputLabel">是否推荐：</label>
            <div class="field">
                <div class="ui radio checkbox">
                    <input type="radio" name="recommend" value="true" ng-model="news.recommend">
                    <label>是</label>
                </div>
                <div class="ui radio checkbox">
                    <input type="radio" name="recommend" value="false" ng-model="news.recommend">
                    <label>否</label>
                </div>
            </div>
            <p style="color: grey;font-size: 10px;margin-top: 10px;">（如果是推荐新闻，则必须上传一张轮播图（图片比例：1300*300）</p>
        </div>
        <div class="field">
            <label class="inputLabel">轮播图</label>
            <div class="ui segment" style="width: 300px;" ng-style="{height: news.rollImg != '' ? '280px' : '80px'}">
                <img ng-if="news.rollImg != ''" class="ui image width300 height200" ng-src="{{news.rollImg}}">
                <div class="roll_img">
                    <button class="ui basic button">上传轮播图片</button>
                    <div class="roll_img_add">uploader</div>
                </div>
            </div>
        </div>
        <div class="field" ng-class="{'error':form.weight.$dirty && form.weight.$invalid}">
            <label class="inputLabel">权重</label>
            <input placeholder="权重" name="weight" ng-model="news.weight" required ng-pattern="/^([1-9]\d{0,2}$|0)$/">
            <div class="ui basic red pointing prompt label" ng-if="form.weight.$dirty && form.weight.$invalid">
                权重不能为空，且只能为0-999的整数
            </div>
        </div>
        <div class="field">
            <label class="inputLabel">新闻内容</label>
            <textarea id="container" name="container">{{news.content}}
            </textarea>
        </div>
        <div class="field">
            <button type="button" ng-click="saveData()" ng-disabled="form.$invalid" class="ui red button">保&nbsp;存
            </button>
        </div>
    </form>
</div>

<script type="text/javascript">
    var ue = UE.getEditor("container");

    $('#statusSelect').dropdown();
    var app = angular.module('newsApp', []);
    app.controller('newsController', function ($scope, $http ) {
        $scope.news = {
            id: '${newsForm.id}',
            title: '${newsForm.title}',
            content: '${newsForm.content}',
            status: '${newsForm.status}' || 'OFFLINE',
            recommend: '${newsForm.recommend}' || 'false',
            rollImg: '${newsForm.rollImg}',
            weight: '${newsForm.weight}'
        };

        function checkForm() {
            if ($scope.news.content == '') {
                showDynamicMsgModal('info', '请填写新闻内容');
                return false;
            }
            if($scope.news.recommend == 'true' && $scope.news.rollImg == ''){
                showDynamicMsgModal('info', '请上传轮播图片');
                return false;
            }
            return true;
        };
        $scope.saveData = function () {
            $scope.news.content = ue.getContent();
            if (!checkForm()) {
                return;
            }
            var postUrl = $scope.news.id == '' ? '/news/add' : '/news/edit';
            $http.post(postUrl, $scope.news).success(function (data) {
                if (data.code == 1) {
                    showOkMsgModal();
                    setTimeout(function () {
                        location.href = data.jumpUrl;
                    }, 1000);
                } else {
                    showFailMsgModal();
                }
            }).error(function () {
                showErrorMsgModal();
            });
        };

        //轮播图上传
        var uploader = WebUploader.create({
            auto: true,
            server: '/qiniu/upload/newsRoll',
            pick: '.roll_img_add',
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
                $scope.news.rollImg = response.data;
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