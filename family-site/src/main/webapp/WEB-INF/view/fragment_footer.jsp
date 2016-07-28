<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
</head>
</div>
</div>
</div>
<div style="text-align: center;font-size:12px;width:inherit;color: brown;clear: both;margin: 10px;">
    <p> Copyright © 练氏家族网 All Rights Reserved</p>
    <p style="margin-top: -5px;">站长：练小龙&nbsp;&nbsp;&nbsp;QQ:954513897&nbsp;&nbsp;&nbsp;手机：18280116335</p>
</div>
<jsp:include page="modal_template.jsp"></jsp:include>
</body>
<script type="text/javascript">
    $('.ui.sticky').sticky();
    var url = location.href;
    if (url.indexOf('news') != -1) {
        $('#menu_news').addClass('active');
    } else if (url.indexOf('singleMenu') != -1) {
        $('#menu_singleMenu').addClass('active');
    } else if (url.indexOf('leaveword') != -1) {
        $('#menu_leaveword').addClass('active');
    }else if (url.indexOf('photo') != -1) {
        $('#menu_photo').addClass('active');
    } else if (url.indexOf('user') != -1){
        $('#menu_user').addClass('active');
    }else {
        $('#menu_index').addClass('active');
    }

</script>
</html>