<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui pagination blue menu">
    <c:if test="${query.page>2}">
        <a class="icon item firstPageItem" onclick="toPage(1)">
            <i class="left chevron icon"></i>
        </a>
    </c:if>
    <a class="icon item previousPageItem" onclick="toPage(${query.page-1})">
        <i class="left caret icon"></i>
    </a>
    <c:if test="${domainPage.pageTotal>1}">
        <c:forEach var="index" begin="${domainPage.pageTotal<=5 ? 1 : domainPage.page+1}"
                   end="${domainPage.pageTotal<=5 ? domainPage.pageTotal : domainPage.pageTotal+domainPage.page+1}"
                   step="1">
            <a class="item pageNumber" data-value="${index}" onclick="toPage(${index})">${index}</a>
        </c:forEach>
    </c:if>
    <a class="icon item nextPageItem" onclick="toPage(${query.page+1})">
        <i class="right caret icon"></i>
    </a>
    <c:if test="${domainPage.pageTotal-query.page>=2}">
        <a class="icon item lastPageItem" onclick="toPage(${domainPage.pageTotal})">
            <i class="right chevron icon"></i>
        </a>
    </c:if>
        <span class="item">
            共&nbsp;${domainPage.domainTotal}&nbsp;项
        </span>
</div>
<script type="text/javascript">
    var pageUrl = null;
    function initPageState() {
        var page = parseInt(${domainPage.page}+1);
        var hasNext = ${domainPage.hasNext};
        if (page == 1) {
            $('.previousPageItem').css('display', 'none');
        }
        if (!hasNext) {
            $('.nextPageItem').css('display', 'none');
        }
        $(".pageNumber[data-value=" + page + "]").addClass('active');
    }
    initPageState();
    function toPage(page) {
        if (pageUrl == null || pageUrl == '') {
            console.error("pageUrl can not be null or empty!");
            return;
        }
        var params = ${query};
        var html = '<form id="pageForm" action="' + pageUrl + '" method="get">';
        for (key in params) {
            if (key == 'page') {
                html += '<input type="hidden" name="' + key + '" value="' + page + '">';
            }
            if (params[key] != null && key != 'page') {
                html += '<input type="hidden" name="' + key + '" value="' + params[key] + '">';
            }
        }
        html += '</form>';
        $(".list_footer").append(html);
        $('#pageForm').submit();
    }
</script>