<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div class="ui segment">
    <ul class="bxslider">
        <c:forEach var="news" items="${domainPage.domains}">
            <li class="ui grid">
              <span class="one wide column"><i class="newspaper icon"></i> </span>  <span class="eleven wide column"><a href="/news/detail/${news.id}">${news.title}</a></span>
                <span class="four wide column textGrey"><fmt:formatDate value="${news.createdTime}"
                                                                pattern="yyyy-MM-dd"></fmt:formatDate></span>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="margin-bottom20 list_footer" style="text-align: right">
    <div class="ui divider"></div>
    <jsp:include page="../pager.jsp"></jsp:include>
</div>
<script type="text/javascript">
    pageUrl = '/news/list';
</script>
<jsp:include page="../fragment_footer.jsp"></jsp:include>