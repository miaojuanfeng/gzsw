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
  #model_cl_1, #model_hl_2, #model_cl_3, #model_hl_4, #model_cl_5, #model_hl_6{
      display: none;
  }
  .layui-form-label{
      width: 100px;
  }
  .layui-input-block {
      margin-left: 130px;
  }
</style>
<body>

  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-header">方案信息</div>
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="component-form-group">
          <div class="layui-form-item">
            <label class="layui-form-label">方案名称</label>
            <div class="layui-input-block">
              <input type="hidden" name="id" value="${plan.ID}">
              <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入方案名称" class="layui-input" value="${plan.NAME}">
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
                      <option value="${sttp.code}" <c:if test="${plan.sttype==sttp.code}">selected</c:if>>${sttp.text}</option>
                    </c:forEach>
                  </select>
                </div>
                <div style="width: 70%;float:right;">
                  <select name="station" lay-filter="station" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${stations}" var="station" varStatus="id">
                      <option value="${station.stcd}" <c:if test="${plan.stcd==station.stcd}">selected</c:if>>${station.stname}</option>
                    </c:forEach>
                  </select>
                </div>
            </div>
          </div>

          <div class="layui-form-item">
            <label class="layui-form-label">产流方案类型</label>
            <div class="layui-input-block">
                <select name="modelCl" lay-filter="modelCl" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${modelCls}" var="model" varStatus="id">
                        <option value="${model.id}" <c:if test="${plan.MODEL_CL==model.id}">selected</c:if>>${model.text}</option>
                    </c:forEach>
                </select>
            </div>
          </div>

          <div class="layui-form-item">
            <label class="layui-form-label">汇流方案类型</label>
            <div class="layui-input-block">
                <select name="modelHl" lay-filter="modelHl" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${modelHls}" var="model" varStatus="id">
                        <option value="${model.id}" <c:if test="${plan.MODEL_HL==model.id}">selected</c:if>>${model.text}</option>
                    </c:forEach>
                </select>
            </div>
          </div>

        </form>

        <div class="layui-form-item">
          <label class="layui-form-label">产流方案参数</label>
          <div class="layui-input-block">
            <table id="model_cl_" class="model_param_cl layui-table" style="margin:0;<c:if test="${not empty plan.MODEL_CL}">display:none;</c:if>">
              <colgroup>
                <col width="100%">
              </colgroup>
              <tbody>
              <tr>
                <td align="center">请先选择产流方案类型</td>
              </tr>
              </tbody>
            </table>
            <form id="form_model_cl_1">
              <table id="model_cl_1" lay-filter="model_cl_1" class="model_param_cl layui-table" style="margin:0;<c:if test="${plan.MODEL_CL==1}">display:table;</c:if>">
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
                  <td class="input-tr"><input type="text" name="WU0" autocomplete="off" class="layui-input" value="${plan.WU0}"></td>
                  <td>WL0</td>
                  <td class="input-tr"><input type="text" name="WL0" autocomplete="off" class="layui-input" value="${plan.WL0}"></td>
                  <td>WD0</td>
                  <td class="input-tr"><input type="text" name="WD0" autocomplete="off" class="layui-input" value="${plan.WD0}"></td>
                  <td>WUM</td>
                  <td class="input-tr"><input type="text" name="WUM" autocomplete="off" class="layui-input" value="${plan.WUM}"></td>
                </tr>
                <tr>
                  <td>WLM</td>
                  <td class="input-tr"><input type="text" name="WLM" autocomplete="off" class="layui-input" value="${plan.WLM}"></td>
                  <td>WDM</td>
                  <td class="input-tr"><input type="text" name="WDM" autocomplete="off" class="layui-input" value="${plan.WDM}"></td>
                  <td>B</td>
                  <td class="input-tr"><input type="text" name="B" autocomplete="off" class="layui-input" value="${plan.B}"></td>
                  <td>K</td>
                  <td class="input-tr"><input type="text" name="K" autocomplete="off" class="layui-input" value="${plan.K}"></td>
                </tr>
                <tr>
                  <td>C</td>
                  <td class="input-tr"><input type="text" name="C" autocomplete="off" class="layui-input" value="${plan.C}"></td>
                  <td>SM</td>
                  <td class="input-tr"><input type="text" name="SM" autocomplete="off" class="layui-input" value="${plan.SM}"></td>
                  <td>EX</td>
                  <td class="input-tr"><input type="text" name="EX" autocomplete="off" class="layui-input" value="${plan.EX}"></td>
                  <td>KSS</td>
                  <td class="input-tr"><input type="text" name="KSS" autocomplete="off" class="layui-input" value="${plan.KSS}"></td>
                </tr>
                <tr>
                  <td>KG</td>
                  <td class="input-tr"><input type="text" name="KG" autocomplete="off" class="layui-input" value="${plan.KG}"></td>
                  <td>IM</td>
                  <td class="input-tr"><input type="text" name="IM" autocomplete="off" class="layui-input" value="${plan.IM}"></td>
                  <td>T</td>
                  <td class="input-tr"><input type="text" name="T" autocomplete="off" class="layui-input" value="${plan.T}"></td>
                  <td>F</td>
                  <td class="input-tr"><input type="text" name="F" autocomplete="off" class="layui-input" value="${plan.F}"></td>
                </tr>
                <tr>
                  <td>S0</td>
                  <td class="input-tr"><input type="text" name="S0" autocomplete="off" class="layui-input" value="${plan.S0}"></td>
                  <td>FR0</td>
                  <td class="input-tr"><input type="text" name="FR0" autocomplete="off" class="layui-input" value="${plan.FR0}"></td>
                  <td>E</td>
                  <td class="input-tr"><input type="text" name="E" autocomplete="off" class="layui-input" value="${plan.E}"></td>
                  <td colspan="2"></td>
                </tr>
                </tbody>
              </table>
            </form>
            <form id="form_model_cl_3">
              <table id="model_cl_3" lay-filter="model_cl_3" class="model_param_cl layui-table" style="margin:0;<c:if test="${plan.MODEL_CL==3}">display:table;</c:if>;">
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
                  <td class="input-tr"><input type="text" name="PA" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.PA}">${plan.PA}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
                  <td colspan="6"></td>
                </tr>
                </tbody>
              </table>
            </form>
            <form id="form_model_cl_5">
              <table id="model_cl_5" lay-filter="model_cl_5" class="model_param_cl layui-table" style="margin:0;<c:if test="${plan.MODEL_CL==5}">display:table;</c:if>;">
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
                  <td class="input-tr"><input type="text" name="KR"  autocomplete="off" class="layui-input" value="${plan.KR}"></td>
                  <td>IM</td>
                  <td class="input-tr"><input type="text" name="IM"  autocomplete="off" class="layui-input" value="${plan.IM}"></td>
                  <td>IMM</td>
                  <td class="input-tr"><input type="text" name="IMM"  autocomplete="off" class="layui-input" value="${plan.IMM}"></td>
                  <td>AREA</td>
                  <td class="input-tr"><input type="text" name="AREA"  autocomplete="off" class="layui-input" value="${plan.AREA}"></td>
                </tr>
                <tr>
                  <td>PA</td>
                  <td class="input-tr"><input type="text" name="PA" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.PA}">${plan.PA}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
                  <td colspan="6"></td>
                </tr>
                </tbody>
              </table>
            </form>
          </div>
        </div>

        <div class="layui-form-item">
          <label class="layui-form-label">汇流方案参数</label>
          <div class="layui-input-block">
              <table id="model_hl_" class="model_param_hl layui-table" style="margin:0;<c:if test="${not empty plan.MODEL_HL}">display:none;</c:if>">
                  <colgroup>
                      <col width="100%">
                  </colgroup>
                  <tbody>
                  <tr>
                      <td align="center">请先选择汇流方案类型</td>
                  </tr>
                  </tbody>
              </table>
              <form id="form_model_hl_2">
                  <table id="model_hl_2" lay-filter="model_hl_2" class="model_param_hl layui-table" style="margin:0;<c:if test="${plan.MODEL_HL==2}">display:table;</c:if>">
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
                          <td>CS</td>
                          <td class="input-tr"><input type="text" name="CS" autocomplete="off" class="layui-input" value="${plan.CS}"></td>
                          <td>CI</td>
                          <td class="input-tr"><input type="text" name="CI" autocomplete="off" class="layui-input" value="${plan.CI}"></td>
                          <td>CG</td>
                          <td class="input-tr"><input type="text" name="CG" autocomplete="off" class="layui-input" value="${plan.CG}"></td>
                          <td>L</td>
                          <td class="input-tr"><input type="text" name="L" autocomplete="off" class="layui-input" value="${plan.L}"></td>
                      </tr>
                      <tr>
                          <td>QRS0</td>
                          <td class="input-tr"><input type="text" name="QRS0" autocomplete="off" class="layui-input" value="${plan.QRS0}"></td>
                          <td>QRSS0</td>
                          <td class="input-tr"><input type="text" name="QRSS0" autocomplete="off" class="layui-input" value="${plan.QRSS0}"></td>
                          <td>QRG0</td>
                          <td class="input-tr"><input type="text" name="QRG0" autocomplete="off" class="layui-input" value="${plan.QRG0}"></td>
                          <td colspan="2"></td>
                      </tr>
                      </tbody>
                  </table>
              </form>
              <form id="form_model_hl_4">
                  <table id="model_hl_4" lay-filter="model_hl_4" class="model_param_hl layui-table" style="margin:0;<c:if test="${plan.MODEL_HL==4}">display:table;</c:if>;">
                      <colgroup>
                          <col width="100%">
                      </colgroup>
                      <tbody>
                      <tr>
                          <td align="center" colspan="8">无需参数</td>
                      </tr>
                      </tbody>
                  </table>
              </form>
              <form id="form_model_hl_6">
                  <table id="model_hl_6" lay-filter="model_hl_6" class="model_param_hl layui-table" style="margin:0;<c:if test="${plan.MODEL_HL==6}">display:table;</c:if>;">
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
                          <td>NA</td>
                          <td class="input-tr"><input type="text" name="NA"  autocomplete="off" class="layui-input" value="${plan.NA}"></td>
                          <td>NU</td>
                          <td class="input-tr"><input type="text" name="NU"  autocomplete="off" class="layui-input" value="${plan.NU}"></td>
                          <td>KG</td>
                          <td class="input-tr"><input type="text" name="KG"  autocomplete="off" class="layui-input" value="${plan.KG}"></td>
                          <td>KU</td>
                          <td class="input-tr"><input type="text" name="KU"  autocomplete="off" class="layui-input" value="${plan.KU}"></td>
                      </tr>
                      </tbody>
                  </table>
              </form>
          </div>
        </div>

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
  }).use(['index', 'form', 'laydate'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;

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
                    var html = '';
                    $.each(data, function (key, value) {
                        html += '<option value="' + value.stcd + '">' + value.stname + '</option>';
                    });
                    $("select[name=station]").append(html);
                    form.render('select');
                    layer.close(loading);
                }
            );
        }
    });
    <%--form.on('select(modelCl)', function(data){--%>
        <%--$("select[name=model]").html('<option value="">请选择</option>');--%>
        <%--form.render('select');--%>
        <%--$(".model_param").hide();--%>
        <%--$("#model_").show();--%>
        <%--var mtype = $("select[name=mtype]").val();--%>
        <%--if( mtype != "" ){--%>
            <%--var loading = layer.load(0);--%>
            <%--$.post(--%>
                <%--'${pageContext.request.contextPath}/model/getType',--%>
                <%--{mtype: mtype},--%>
                <%--function (data) {--%>
                    <%--var html = '';--%>
                    <%--$.each(data, function (key, value) {--%>
                        <%--html += '<option value="' + value.id + '">' + value.text + '</option>';--%>
                    <%--});--%>
                    <%--$("select[name=model]").append(html);--%>
                    <%--form.render('select');--%>
                    <%--layer.close(loading);--%>
                <%--}--%>
            <%--);--%>
        <%--}--%>
    <%--});--%>
    form.on('select(modelCl)', function(data){
        $(".model_param_cl").hide();
        $("#model_cl_" + data.value).show();
    });
    form.on('select(modelHl)', function(data){
        $(".model_param_hl").hide();
        $("#model_hl_" + data.value).show();
    });
    
    // form.render(null, 'component-form-group');

    // laydate.render({
    //   elem: '#LAY-component-form-group-date'
    // });
    
    // /* 自定义验证规则 */
    // form.verify({
    //   title: function(value){
    //     if(value.length < 5){
    //       return '标题至少得5个字符啊';
    //     }
    //   }
    //   ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    //   ,content: function(value){
    //     layedit.sync(editIndex);
    //   }
    // });

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
        var action = $("input[name=id]").val() == '' ? 'insert' : 'update/' + $("input[name=id]").val();
        var data = {};
        var cl = $("#form_model_cl_" + $("select[name=modelCl]").val()).serializeArray();
        var hl = $("#form_model_hl_" + $("select[name=modelHl]").val()).serializeArray();
        $.each(cl, function() {
            data[this.name] = this.value;
        });
        $.each(hl, function() {
            data[this.name] = this.value;
        });
        data.name = $("input[name=name]").val();
        data.stcd = $("select[name=station]").val();
        data.modelCl = $("select[name=modelCl]").val();
        data.modelHl = $("select[name=modelHl]").val();

        $.post({
            url: "${pageContext.request.contextPath}/plan/" + action,
            contentType: "application/x-www-form-urlencoded",
            data: data,
            success : function(result) {
                parent.layer.alert("数据保存成功", function (index) {
                    parent.layer.close(index);
                    parent.layui.admin.events.closeThisTabs();
                })
            }
        }).fail(function(response) {
            parent.layer.alert("数据保存失败");
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
