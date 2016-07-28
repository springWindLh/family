<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../fragment_header.jsp"></jsp:include>
<div class="ui segment">
    <div class="content">
        ${singleDetail.content}
    </div>
</div>
<jsp:include page="../fragment_footer.jsp"></jsp:include>