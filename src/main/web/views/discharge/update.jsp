<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>新建方案</title>
  <%@ include file="../linker.jsp" %>
</head>
<style type="text/css">
  .input-tr{
    padding:0 !important;
  }
  .input-tr .layui-input{
    border: none !important;
  }
</style>
<body>

  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-header">新建方案</div>
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="component-form-group">
          <div class="layui-form-item">
            <label class="layui-form-label">方案名称</label>
            <div class="layui-input-block">
              <input type="hidden" name="id" value="${id}">
              <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入方案名称" class="layui-input" value="${name}">
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
                      <option value="${sttp.code}">${sttp.text}</option>
                    </c:forEach>
                  </select>
                </div>
                <div style="width: 70%;float:right;">
                  <select name="station" lay-filter="station" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                  </select>
                </div>
            </div>
          </div>

          <div class="layui-form-item">
            <label class="layui-form-label">预报河系</label>
            <div class="layui-input-block">
              <select name="model" lay-filter="model" lay-verify="required" lay-search="">
                <option value="">请选择</option>
                <option value="1">新安江</option>
                <option value="2">经验单位线</option>
                <option value="3">API</option>
              </select>
            </div>
          </div>
        </form>

        <div class="layui-form-item">
          <label class="layui-form-label">方案参数</label>
          <div class="layui-input-block">
            <table id="model_" class="model_param layui-table" style="margin:0;">
              <colgroup>
                <col width="100%">
              </colgroup>
              <tbody>
              <tr>
                <td align="center">请先选择预报河系</td>
              </tr>
              </tbody>
            </table>
            <form id="form_model_1">
              <table id="model_1" lay-filter="model_1" class="model_param layui-table" style="margin:0;display:none;">
                  <colgroup>
                    <col width="10%">
                    <col width="15%">
                    <col width="10%">
                    <col width="15%">
                    <col width="10%">
                    <col width="15%">
                    <col width="10%">
                    <col width="15%">
                  </colgroup>
                  <tbody>
                  <tr>
                  <td>WU0</td>
                  <td class="input-tr"><input type="text" name="WU0" autocomplete="off" class="layui-input"></td>
                  <td>WL0</td>
                  <td class="input-tr"><input type="text" name="WL0" autocomplete="off" class="layui-input"></td>
                  <td>WD0</td>
                  <td class="input-tr"><input type="text" name="WD0" autocomplete="off" class="layui-input"></td>
                  <td>WUM</td>
                  <td class="input-tr"><input type="text" name="WUM" autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>WLM</td>
                  <td class="input-tr"><input type="text" name="WLM" autocomplete="off" class="layui-input"></td>
                  <td>WDM</td>
                  <td class="input-tr"><input type="text" name="WDM" autocomplete="off" class="layui-input"></td>
                  <td>B</td>
                  <td class="input-tr"><input type="text" name="B" autocomplete="off" class="layui-input"></td>
                  <td>K</td>
                  <td class="input-tr"><input type="text" name="K" autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>C</td>
                  <td class="input-tr"><input type="text" name="C" autocomplete="off" class="layui-input"></td>
                  <td>SM</td>
                  <td class="input-tr"><input type="text" name="SM" autocomplete="off" class="layui-input"></td>
                  <td>EX</td>
                  <td class="input-tr"><input type="text" name="EX" autocomplete="off" class="layui-input"></td>
                  <td>KSS</td>
                  <td class="input-tr"><input type="text" name="KSS" autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>KG</td>
                  <td class="input-tr"><input type="text" name="KG" autocomplete="off" class="layui-input"></td>
                  <td>IM</td>
                  <td class="input-tr"><input type="text" name="IM" autocomplete="off" class="layui-input"></td>
                  <td>CS</td>
                  <td class="input-tr"><input type="text" name="CS" autocomplete="off" class="layui-input"></td>
                  <td>CI</td>
                  <td class="input-tr"><input type="text" name="CI" autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>CG</td>
                  <td class="input-tr"><input type="text" name="CG" autocomplete="off" class="layui-input"></td>
                  <td>L</td>
                  <td class="input-tr"><input type="text" name="L" autocomplete="off" class="layui-input"></td>
                  <td>T</td>
                  <td class="input-tr"><input type="text" name="T" autocomplete="off" class="layui-input"></td>
                  <td>F</td>
                  <td class="input-tr"><input type="text" name="F" autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>S0</td>
                  <td class="input-tr"><input type="text" name="S0" autocomplete="off" class="layui-input"></td>
                  <td>FR0</td>
                  <td class="input-tr"><input type="text" name="FR0" autocomplete="off" class="layui-input"></td>
                  <td>QRS0</td>
                  <td class="input-tr"><input type="text" name="QRS0" autocomplete="off" class="layui-input"></td>
                  <td>QRSS0</td>
                  <td class="input-tr"><input type="text" name="QRSS0" autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>QRG0</td>
                  <td class="input-tr"><input type="text" name="QRG0" autocomplete="off" class="layui-input"></td>
                  <td colspan="6"></td>
                </tr>
                </tbody>
              </table>
            </form>
            <form id="form_model_2">
              <table id="model_2" lay-filter="model_2" class="model_param layui-table" style="margin:0;display:none;">
                <colgroup>
                  <col width="10%">
                  <col width="15%">
                  <col width="10%">
                  <col width="15%">
                  <col width="10%">
                  <col width="15%">
                  <col width="10%">
                  <col width="15%">
                </colgroup>
                <tbody>
                <tr>
                  <td>PA</td>
                  <td class="input-tr"><input type="text" name="PA" lay-filter="PA" autocomplete="off" class="layui-input"></td>
                  <td colspan="6"></td>
                </tr>
                </tbody>
              </table>
            </form>
            <form id="form_model_3">
              <table id="model_3" lay-filter="model_3" class="model_param layui-table" style="margin:0;display:none;">
                <colgroup>
                  <col width="10%">
                  <col width="15%">
                  <col width="10%">
                  <col width="15%">
                  <col width="10%">
                  <col width="15%">
                  <col width="10%">
                  <col width="15%">
                </colgroup>
                <tbody>
                <tr>
                  <td>KR</td>
                  <td class="input-tr"><input type="text" name="KR"  autocomplete="off" class="layui-input"></td>
                  <td>IM</td>
                  <td class="input-tr"><input type="text" name="IM"  autocomplete="off" class="layui-input"></td>
                  <td>IMM</td>
                  <td class="input-tr"><input type="text" name="IMM"  autocomplete="off" class="layui-input"></td>
                  <td>NA</td>
                  <td class="input-tr"><input type="text" name="NA"  autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>NU</td>
                  <td class="input-tr"><input type="text" name="NU"  autocomplete="off" class="layui-input"></td>
                  <td>KG</td>
                  <td class="input-tr"><input type="text" name="KG"  autocomplete="off" class="layui-input"></td>
                  <td>KU</td>
                  <td class="input-tr"><input type="text" name="KU"  autocomplete="off" class="layui-input"></td>
                  <td>AREA</td>
                  <td class="input-tr"><input type="text" name="AREA"  autocomplete="off" class="layui-input"></td>
                </tr>
                <tr>
                  <td>PA</td>
                  <td class="input-tr"><input type="text" name="PA"  autocomplete="off" class="layui-input"></td>
                  <td colspan="6"></td>
                </tr>
                </tbody>
              </table>
            </form>
          </div>
        </div>

        <div class="layui-form-item layui-layout-admin">
          <div class="layui-input-block">
            <div class="layui-footer" style="left: 0;">
              <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
              <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="close">关闭</button>
            </div>
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
  }).use(['index', 'form', 'laydate'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;
    ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

    form.on('select(sttp)', function(data){
        $("select[name=station]").html('<option value="">请选择</option>');
        form.render('select');
        var sttp = $("select[name=sttp]").val();
        if( sttp != "" ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/station/getByType',
                {
                    sttp: sttp
                },
                function (data) {
                    if( data.code == 200 ) {
                        var stations = $.parseJSON(data);
                        var html = '';
                        $.each(stations, function (key, value) {
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
    form.on('select(model)', function(data){
        $(".model_param").hide();
        $("#model_" + data.value).show();
    });
    
    form.render(null, 'component-form-group');

    laydate.render({
      elem: '#LAY-component-form-group-date'
    });
    
    /* 自定义验证规则 */
    form.verify({
      title: function(value){
        if(value.length < 5){
          return '标题至少得5个字符啊';
        }
      }
      ,pass: [/(.+){6,12}$/, '密码必须6到12位']
      ,content: function(value){
        layedit.sync(editIndex);
      }
    });

    /* 监听提交 */
    form.on('submit(save)', function(data){
        var submit = true;
        if ($("input[name=name]").val() == "" ||
            $("select[name=station]").val() == "" ||
            $("select[name=model]").val() == "" ) {
            submit = false;
        }
        if (!submit) {
            layer.msg('请填妥相关信息');
            return false;
        }
        var id = $("input[name=id]").val();
        var update = '';
        if( id != '' ){
            update = '/' + id;
        }

        var d = {};
        var t = $("#form_model_" + $("select[name=model]").val()).serializeArray();
        $.each(t, function() {
            d[this.name] = this.value;
        });
        d.name = $("input[name=name]").val();
        d.stcd = $("select[name=station]").val();
        d.model = $("select[name=model]").val();

        var loading = layer.load(0);
        $.post({
            url: "${pageContext.request.contextPath}/plan/insert" + update,
            contentType: "application/x-www-form-urlencoded",
            // data: {
            //     name: $("input[name=name]").val(),
            //     stcd: $("select[name=station]").val(),
            //     model: $("select[name=model]").val(),
            //     data: JSON.stringify(form.val('model_2')),
            // },
            data: d,
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

    /* 监听关闭 */
    form.on('submit(close)', function(data){
        parent.layui.admin.events.closeThisTabs();
    });
  });
  </script>
</body>
</html>
