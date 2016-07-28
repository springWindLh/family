<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="./fragment_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/jquery.bxslider.css">
<style type="text/css">
    .bx-wrapper .bx-caption{
        bottom: 10px;
    }
</style>
<script type="text/javascript" src="js/jquery.bxslider.min.js"></script>
<c:if test="${fn:length(recommendPage.domains)>0}">
<div>
    <ul class="bxslider" style="margin-top: 0px;height: 300px;">
        <c:forEach var="news" items="${recommendPage.domains}">
            <li>
                <a href="/news/detail/${news.id}"> <img src="${news.rollImg}" title="${news.title}" style="height: 300px;"></a>
                </li>
        </c:forEach>
    </ul>
</div>
</c:if>
<div class="ui grid">
    <div class="ten wide column">
    <label class="inputLabel">最新动态</label>
    <div>
        <c:forEach var="news" items="${domainPage.domains}">
            <li class="listNoneStyle margin10">
                <i class="icon newspaper"></i>&nbsp;&nbsp;
                <a href="/news/detail/${news.id}">
                    <c:if test="${fn:length(news.title) > 15}">
                        <span class="margin-top10">${fn:substring(news.title,0,15)}...</span>
                    </c:if>
                    <c:if test="${fn:length(news.title) <= 15}">
                        <span class="margin-top10">${news.title}</span>
                    </c:if>
                <span style="float: right;color: grey;">
                    <fmt:formatDate value="${news.createdTime}"
                                                        pattern="yyyy-MM-dd"></fmt:formatDate></span>
                </a>
            </li>
        </c:forEach>
        <div class="margin10"><a href="/news/list">更多<i class="angle double right icon"></i> </a> </div>
    </div>
    </div>
    <div class="six wide column">
        <label class="inputlabel">练氏始祖</label>
        <img src="http://o6p6h6085.bkt.clouddn.com/lianhe_info.jpg" class="ui image margin-top10">
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $(".bxslider").bxSlider({
            mode: 'horizontal',
            captions: true,
            autoHover:true
        });
    });
</script>
<jsp:include page="./fragment_footer.jsp"></jsp:include>