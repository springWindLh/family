<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <link rel="stylesheet" type="text/css" href="/js/webuploader/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="/js/photo/style.css" />
</head>
<div id="wrapper" style="padding-right: 252px;">
    <h3 class="ui dividing header"><span class="backArrow"><i class="small link red arrow left icon"
                                                              onclick="javascript:history.go(-1)"></i></span> 上传相片
    </h3>
    <div id="container">
        <div id="uploader">
            <div class="queueList">
                <div id="dndArea" class="placeholder">
                    <div id="filePicker"></div>
                    <p>或将图片拖到这里，单次最多可选300张</p>
                </div>
            </div>
            <div class="statusBar" style="display:none;">
                <div class="progress">
                    <span class="text">0%</span>
                    <span class="percentage"></span>
                </div><div class="info"></div>
                <div class="btns">
                    <div id="filePicker2"></div><div class="uploadBtn" style="margin-right: 252px;">开始上传</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/photo/webuploader.js"></script>
<script type="text/javascript" src="/js/photo/upload.js"></script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>