<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>站点列表</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>

<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">站点列表</div>
        <div class="layui-card-body">
          <div class="layui-form-item">
            <div class="layui-form">
              <div class="layui-form-item">
                <div class="layui-inline">
                  <label class="layui-form-label">小时雨量</label>
                  <div class="layui-input-inline">
                    <select name="selfP" lay-filter="selfP" lay-search="">
                      <option value="">请选择</option>
                      <option value="10">大于等于10mm</option>
                      <option value="30" selected>大于等于30mm</option>
                      <option value="50">大于等于50mm</option>
                    </select>
                  </div>
                </div>
                <div class="layui-inline">
                  <label class="layui-form-label">差异率</label>
                  <div class="layui-input-inline">
                    <select name="diffP" lay-filter="diffP" lay-search="">
                      <option value="">请选择</option>
                      <option value="1">大于等于1</option>
                      <option value="2" selected>大于等于2</option>
                      <option value="3">大于等于3</option>
                    </select>
                  </div>
                </div>
                <div class="layui-inline">
                  <label class="layui-form-label">站点代码</label>
                  <div class="layui-input-inline">
                    <input type="text" name="stcd" class="layui-input" placeholder="支持模糊查询">
                  </div>
                </div>
                <button class="layui-btn layui-btn-sm" id="search">立即搜索</button>
              </div>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">轮询时间</label>
              <div class="layui-input-inline">
                <input id="date" type="text" name="date" placeholder="请选择" autocomplete="off" class="layui-input" value="${date}">
              </div>
            </div>
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="reload">立即轮询</button>
          </div>
          <table id="data-table" class="layui-hide" lay-filter="data"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="edit">
    <a class="layui-btn layui-btn-sm" lay-event="setting">设置</a>
</script>

<script>
    layui.config({
        base: base //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'laydate'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,table = layui.table
            ,laydate = layui.laydate
            ,form = layui.form;
        ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

        laydate.render({
            elem: '#date'
            ,type: 'datetime'
        });

        table.render({
            elem: '#data-table'
            ,method: 'post'
            ,url: "${pageContext.request.contextPath}/station/unusual"
            ,where: {
                selfP: 30,
                diffP: 2
            }
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'stcd', width:100, title: '站点代码'}
                ,{field:'stname', width:100, title: '站点名称'}
                ,{field:'nearStcd', width:100, title: '临近站代码'}
                ,{field:'nearStname', width:100, title: '临近站名称'}
                ,{field:'dis', width:120, title: '两站距离(km)'}
                ,{field:'selfP', width:100, title: '本站雨量'}
                ,{field:'nearP', width:100, title: '临近站雨量'}
                ,{field:'diffP', title: '差异率'}
                ,{field:'dateP', width:160, title: '轮询时间'}
                // ,{fixed: 'right', width:140, align:'center', toolbar: '#edit', title: '操作'}
            ]]
            ,page: false
        });

        form.on('submit(reload)', function(data){
            var param = {
                date: $("input[name=date]").val()
            }
            commonConfirm('station/reload', '立即计算最近整点时间的降雨差异率，确定这样操作吗？', param, function () {
                table.reload('data-table');
            });
        });

        $("#search").click(function () {
            table.reload('data-table', {
                where: {
                    selfP: $("select[name=selfP]").val(),
                    diffP: $("select[name=diffP]").val(),
                    stcd: $("input[name=stcd]").val()
                }
            });
        });

    });
</script>
</body>
</html>
