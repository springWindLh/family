<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<head>
    <link rel="stylesheet" type="text/css" href="/js/lightgallery/lightgallery.min.css"/>
</head>
<div class="ui segment images" id="lightGallery">
    <c:forEach var="photo" items="${domainPage.domains}">
        <a href="${photo.url}">
            <img src="${photo.url}" class="ui image width200 shadow" style="margin: 10px;">
        </a>
    </c:forEach>
</div>
<div class="list_footer">
    <jsp:include page="../pager.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/js/lightgallery/lightgallery.min.js"></script>
<script type="text/javascript" src="/js/lightgallery/lg-thumbnail.min.js"></script>
<script type="text/javascript" src="/js/lightgallery/lg-fullscreen.min.js"></script>
<script type="text/javascript">
    pageUrl = '/photo/list';
        $(function () {
            $('#lightGallery').lightGallery();
        });
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>