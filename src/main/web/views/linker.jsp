<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="<c:url value="/assets/layuiadmin/layui/css/layui.css"></c:url>" media="all">
<link rel="stylesheet" href="<c:url value="/assets/layuiadmin/style/admin.css"></c:url>" media="all">
<script src="<c:url value="/assets/layuiadmin/layui/layui.js"></c:url>"></script>
<script>
    var base = '<c:url value="/assets/layuiadmin/"></c:url>';

    function ajaxSetup(jquery, title){
        jquery.ajaxSetup({
            type: 'POST',
            complete: function (xhr, status) {
                var sessionStatus = xhr.getResponseHeader('sessionstatus');
                if (sessionStatus == 'timeout') {
                    var top = getTopWinow();
                    layer.msg(title, function () {
                        top.location.href = '${pageContext.request.contextPath}' + '/login';
                    });
                }
            }
        });

        function getTopWinow() {
            var p = window;
            while (p != p.parent) {
                p = p.parent;
            }
            return p;
        }
    }

    function openTabsPage(url, title) {
        var href = '${pageContext.request.contextPath}' + '/' + url;
        var l = parent === self ? layui : top.layui;
        l.index.openTabsPage(href, title);
    }

    function deleteConfirm(url, title, obj) {
        layer.confirm(title, function(index){
            var loading = layer.load(0);
            layui.$.post(
                '${pageContext.request.contextPath}' + '/' + url,
                {
                    id: obj.data.id
                },
                function (data) {
                    if( data.code == 200 ) {
                        obj.del();
                    }else{
                        layer.msg(data.msg);
                    }
                    layer.close(index);
                    layer.close(loading);
                }
            );
        });
    }

    function commonConfirm(url, title, param, call) {
        layer.confirm(title, function(index){
            var loading = layer.load(0);
            layui.$.post(
                '${pageContext.request.contextPath}' + '/' + url,
                param,
                function (data) {
                    if( typeof call === 'function' ) call();
                    layer.close(index);
                    layer.close(loading);
                }
            );
        });
    }
</script>