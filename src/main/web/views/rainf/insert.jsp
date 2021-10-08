<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>新建未来雨量方案</title>
  <%@ include file="../linker.jsp" %>
</head>
<style type="text/css">
  .input-tr{
      padding:0 !important;
  }
  .input-tr .layui-input{
      border: none !important;
  }
  #model_cl_1,
  #model_hl_2,
  #model_cl_3,
  #model_hl_4,
  #model_cl_5,
  #model_hl_6,
  #rainRun,
  #unitLine{
      display: none;
  }
  .layui-form-label{
      width: 120px;
  }
  .layui-input-block {
      margin-left: 150px;
  }
</style>
<body>

  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-header">未来雨量方案信息</div>
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="component-form-group">
          <div class="layui-form-item">
            <label class="layui-form-label">未来雨量方案名称</label>
            <div class="layui-input-block">
              <input type="hidden" name="id" value="${rainArea.ID}">
              <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入未来雨量方案名称" class="layui-input" value="${rainArea.NAME}">
            </div>
          </div>

          <div class="layui-form-item">
            <label class="layui-form-label">预报站点</label>
            <div class="layui-input-block">
              <%--<select name="station" lay-filter="station" lay-verify="required" lay-search="">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach items="${stations}" var="station" varStatus="id">--%>
                  <%--<option value="${station.stcd}">${station.stname}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
                <div style="width: 30%;float:left;">
                  <select name="sttp" lay-filter="sttp" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${sttps}" var="sttp" varStatus="id">
                      <option value="${sttp.code}" <c:if test="${rainArea.sttype==sttp.code}">selected</c:if>>${sttp.text}</option>
                    </c:forEach>
                  </select>
                </div>
                <div style="width: 70%;float:right;">
                  <select name="station" lay-filter="station" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${stations}" var="station" varStatus="id">
                      <option value="${station.stcd}" <c:if test="${rainArea.stcd==station.stcd}">selected</c:if>>${station.stname}</option>
                    </c:forEach>
                  </select>
                </div>
            </div>
          </div>

          <div class="layui-form-item">
            <label class="layui-form-label">未来雨量方案格点</label>
            <div class="layui-input-block">
                <div id="transBox" class="demo-transfer"></div>
            </div>
          </div>

        </form>

        <div class="layui-form-item layui-layout-admin">
          <div class="layui-input-block">
              <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
              <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="close">关闭</button>
          </div>
        </div>

      </div>
    </div>
  </div>

  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'transfer'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,transfer = layui.transfer
    ,layer = layui.layer
    ,form = layui.form;
    ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

    form.on('select(sttp)', function(data){
        $("select[name=station]").html('<option value="">请选择</option>');
        form.render('select');
        var sttp = $("select[name=sttp]").val();
        if( sttp != "" ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/station/getStation',
                {sttp: sttp},
                function (data) {
                    if( data.code == 200 ) {
                        var html = '';
                        $.each(data.data, function (key, value) {
                            html += '<option value="' + value.stcd + '">' + value.stname + '</option>';
                        });
                        $("select[name=station]").append(html);
                        form.render('select');
                    }
                    layer.close(loading);
                }
            );
        }
    });

    form.on('select(station)', function(data){
        transfer.reload('transBox', {
            value: [data.value]
        });
    });

    function loadStation(){
      var stations = [];
      transfer.reload('transBox', {
          data: stations
      });
      var loading = layer.load(0);
      $.post(
          '${pageContext.request.contextPath}/rainf/station',
          {},
          function (data) {
              if( data.code == 200 ) {
                  stations = data.data;
                  transfer.reload('transBox', {
                      data: stations
                  });
              }
              layer.close(loading);
          }
      );
    }

    //显示搜索框
    transfer.render({
      elem: '#transBox'
      ,id: 'transBox'
      ,data: []
      ,title: ['所有格点', '流域格点']
      ,showSearch: true
      ,value: [${stcds}]
    });
    loadStation();

    form.on('submit(save)', function(data){
        var rainStation = transfer.getData('transBox');
        if( $("input[name=name]").val() == "" ){
            layer.msg('请填妥未来雨量方案名称');
            return false;
        }
        if( $("select[name=station]").val() == "" ){
            layer.msg('请填妥预报站点');
            return false;
        }
        if( rainStation.length == 0 ){
            layer.msg('请填妥未来雨量方案格点');
            return false;
        }
        var action = $("input[name=id]").val() == '' ? 'insert' : 'update/' + $("input[name=id]").val();
        var data = {};
        data.name = $("input[name=name]").val();
        data.stcd = $("select[name=station]").val();
        data.rainStation = JSON.stringify(rainStation);

        var loading = layer.load(0);
        $.post({
            url: "${pageContext.request.contextPath}/rainf/" + action,
            contentType: "application/x-www-form-urlencoded",
            data: data,
            success : function(result) {
                if( result.code == 200 ) {
                    parent.layer.alert("数据保存成功", function (index) {
                        parent.layer.close(index);
                        parent.layui.admin.events.closeThisTabs();
                    })
                }
                layer.close(loading);
            }
        }).fail(function(response) {
            parent.layer.alert("数据保存失败");
            layer.close(loading);
        });
        return false;
    });

    form.on('submit(close)', function(data){
        parent.layui.admin.events.closeThisTabs();
    });
  });
  </script>
</body>
</html>
