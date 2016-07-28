<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui modal okMsgModal">
    <div class="ui icon header" style="border: none;">
        <i class="green check icon"></i>
        保存成功
    </div>
</div>
<div class="ui modal failMsgModal">
    <div class="ui icon header" style="border: none;">
        <i class="red frown icon"></i>
        保存失败
    </div>
</div>
<div class="ui modal errorMsgModal">
    <div class="ui icon header" style="border: none;">
        <i class="red wifi icon"></i>
        网络出错了
    </div>
</div>
<div class="ui modal dynamicMsgModal">
    <div class="ui icon header" style="border: none;">
        <i id="userIcon"></i>
        <span id="userMsg"></span>
    </div>
</div>
<script type="text/javascript">
    function showOkMsgModal() {
        $('.modal.okMsgModal').modal({
            blurring: true
        }).modal('show');
        setTimeout(function () {
            $('.modal.okMsgModal').modal('hide');
        }, 700);
    }

    function showFailMsgModal() {
        $('.modal.failMsgModal').modal({
            blurring: true
        }).modal('show');
        setTimeout(function () {
            $('.modal.failMsgModal').modal('hide');
        }, 700);
    }

    function showErrorMsgModal() {
        $('.modal.errorMsgModal').modal({
            blurring: true
        }).modal('show');
        setTimeout(function () {
            $('.modal.errorMsgModal').modal('hide');
        }, 700);
    }

    function showDynamicMsgModal(type, msg) {
        $('#userIcon').attr('class', '');
        if (type == 'info') {
            $('#userIcon').attr('class', 'orange info icon');
        }
        if (type == 'failure') {
            $('#userIcon').attr('class', 'red frown icon');
        }
        if (type == 'success') {
            $('#userIcon').attr('class', 'green check icon');
        }
        $('#userMsg').html('');
        $('#userMsg').html(msg);
        $('.modal.dynamicMsgModal').modal({
            blurring: true
        }).modal('show');
        setTimeout(function () {
            $('.modal.dynamicMsgModal').modal('hide');
        },700);
    }
</script>