<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>预报</title>
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
  #model_hl_6{
    display: none;
  }
</style>
<body>
  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md3">
        <div class="layui-card layui-form" style="height: 532px;">
          <div class="layui-card-header">预报参数</div>
          <table class="layui-table" style="margin:0;">
            <colgroup>
              <col width="40%">
              <col width="60%">
            </colgroup>
            <tbody>
            <tr>
              <td>河系方案</td>
              <td class="input-tr">
                <select name="model" lay-filter="model" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <c:forEach items="${models}" var="model" varStatus="id">
                    <option value="${model.id}" stcd="${model.stcd}">${model.name}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td>预报时间</td>
              <td class="input-tr">
                <input id="forecastTime" type="text" name="forecastTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${forecastTime}">
              </td>
            </tr>
            <tr>
              <td>影响时间</td>
              <td class="input-tr">
                <input id="affectTime" type="text" name="affectTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${affectTime}">
              </td>
            </tr>
            <tr>
              <td>预报天数</td>
              <td class="input-tr">
                <select name="day" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <option value="3">3天</option>
                  <option value="5">5天</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>未来降雨</td>
              <td class="input-tr">
                <select name="unit" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <option value="0">实测雨量</option>
                  <option value="2">日本台</option>
                  <option value="6">欧洲台</option>
                </select>
              </td>
            </tr>
            </tbody>
          </table>

          <div class="layui-card-header">流域河系</div>
          <div id="area" class="demo-tree demo-tree-box" style="width: 100%; height: 240px; overflow-y: scroll; padding-top:10px;"></div>
        </div>
      </div>
      <div class="layui-col-md9">
        <div class="layui-card layui-form">
          <div class="layui-card-header">
            <button lay-submit="" lay-filter="forecast1" class="layui-btn layui-btn-sm">预报流量</button>
            <%--<button lay-submit="" lay-filter="forecast2" class="layui-btn layui-btn-sm">预报水位</button>--%>
          </div>
          <div class="layui-card-body layui-row">
            <div id="chart" style="width:100%;height:469px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <form class="layui-form" lay-filter="updateform" id="updateform" style="padding:15px;display: none;">
    <div class="layui-form-item">
      <table class="layui-table" style="margin:0;">
        <tr>
          <td colspan="8" align="center" style="border-bottom:none;">产流参数</td>
        </tr>
      </table>
      <form id="form_model_cl_1">
        <table id="model_cl_1" lay-filter="model_cl_1" class="model_param_cl layui-table" style="margin:0;">
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
            <td class="input-tr"><input type="text" name="WU0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.WU0}"></td>
            <td>WL0</td>
            <td class="input-tr"><input type="text" name="WL0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.WL0}"></td>
            <td>WD0</td>
            <td class="input-tr"><input type="text" name="WD0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.WD0}"></td>
            <td>WUM</td>
            <td class="input-tr"><input type="text" name="WUM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.WUM}"></td>
          </tr>
          <tr>
            <td>WLM</td>
            <td class="input-tr"><input type="text" name="WLM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.WLM}"></td>
            <td>WDM</td>
            <td class="input-tr"><input type="text" name="WDM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.WDM}"></td>
            <td>B</td>
            <td class="input-tr"><input type="text" name="B" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.B}"></td>
            <td>K</td>
            <td class="input-tr"><input type="text" name="K" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.K}"></td>
          </tr>
          <tr>
            <td>C</td>
            <td class="input-tr"><input type="text" name="C" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.C}"></td>
            <td>SM</td>
            <td class="input-tr"><input type="text" name="SM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.SM}"></td>
            <td>EX</td>
            <td class="input-tr"><input type="text" name="EX" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.EX}"></td>
            <td>KSS</td>
            <td class="input-tr"><input type="text" name="KSS" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.KSS}"></td>
          </tr>
          <tr>
            <td>KG</td>
            <td class="input-tr"><input type="text" name="KG" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.KG}"></td>
            <td>IM</td>
            <td class="input-tr"><input type="text" name="IM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.IM}"></td>
            <td>T</td>
            <td class="input-tr"><input type="text" name="T" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.T}"></td>
            <td>F</td>
            <td class="input-tr"><input type="text" name="F" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.F}"></td>
          </tr>
          <tr>
            <td>S0</td>
            <td class="input-tr"><input type="text" name="S0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.S0}"></td>
            <td>FR0</td>
            <td class="input-tr"><input type="text" name="FR0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.FR0}"></td>
            <td>CS</td>
            <td class="input-tr"><input type="text" name="CS" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.CS}"></td>
            <td>CI</td>
            <td class="input-tr"><input type="text" name="CI" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.CI}"></td>
          </tr>
          <tr>
            <td>CG</td>
            <td class="input-tr"><input type="text" name="CG" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.CG}"></td>
            <td>QRS0</td>
            <td class="input-tr"><input type="text" name="QRS0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.QRS0}"></td>
            <td>QRSS0</td>
            <td class="input-tr"><input type="text" name="QRSS0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.QRSS0}"></td>
            <td>QRG0</td>
            <td class="input-tr"><input type="text" name="QRG0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.QRG0}"></td>
          </tr>
          <tr>
            <td>E</td>
            <td class="input-tr"><input type="text" name="E" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.E}"></td>
            <td colspan="6"></td>
          </tr>
          </tbody>
        </table>
      </form>
      <form id="form_model_cl_3">
        <table id="model_cl_3" lay-filter="model_cl_3" class="model_param_cl layui-table" style="margin:0;">
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
            <td class="input-tr"><input type="text" name="PA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.PA}">${plan.PA}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
            <td colspan="6"></td>
          </tr>
          </tbody>
        </table>
      </form>
      <form id="form_model_cl_5">
        <table id="model_cl_5" lay-filter="model_cl_5" class="model_param_cl layui-table" style="margin:0;">
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
            <td class="input-tr"><input type="text" name="KR" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.KR}"></td>
            <td>IM</td>
            <td class="input-tr"><input type="text" name="IM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.IM}"></td>
            <td>IMM</td>
            <td class="input-tr"><input type="text" name="IMM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.IMM}"></td>
            <td>PA</td>
            <td class="input-tr"><input type="text" name="PA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.PA}">${plan.PA}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
          </tr>
          </tbody>
        </table>
      </form>
      <table class="layui-table" style="margin:0;margin-top:15px;">
        <tr>
          <td colspan="8" align="center" style="border-bottom:none;">汇流参数</td>
        </tr>
      </table>
      <form id="form_model_hl_2">
        <table id="model_hl_2" lay-filter="model_hl_2" class="model_param_hl layui-table" style="margin:0;">
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
            <td>L</td>
            <td class="input-tr"><input type="text" name="L" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.L}"></td>
            <td colspan="6"></td>
          </tr>
          </tbody>
        </table>
      </form>
      <form id="form_model_hl_4">
        <table id="model_hl_4" lay-filter="model_hl_4" class="model_param_hl layui-table" style="margin:0;">
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
        <table id="model_hl_6" lay-filter="model_hl_6" class="model_param_hl layui-table" style="margin:0;">
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
            <td class="input-tr"><input type="text" name="NA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.NA}"></td>
            <td>NU</td>
            <td class="input-tr"><input type="text" name="NU" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.NU}"></td>
            <td>KG</td>
            <td class="input-tr"><input type="text" name="KG" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.KG}"></td>
            <td>KU</td>
            <td class="input-tr"><input type="text" name="KU" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.KU}"></td>
          </tr>
          <tr>
            <td>AREA</td>
            <td class="input-tr"><input type="text" name="AREA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="${plan.AREA}"></td>
            <td colspan="6"></td>
          </tr>
          </tbody>
        </table>
      </form>
      <table class="layui-table" style="margin:0;margin-top:15px;">
        <tr>
          <td colspan="8" align="center" style="border-bottom:none;">演算参数</td>
        </tr>
      </table>
      <form id="form_model_hl_0">
        <table id="model_hl_0" lay-filter="model_hl_0" class="layui-table" style="margin:0;">
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
            <td>INTV</td>
            <td class="input-tr"><input type="text" name="INTV" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input"></td>
            <td>KE</td>
            <td class="input-tr"><input type="text" name="KE" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input"></td>
            <td>XE</td>
            <td class="input-tr"><input type="text" name="XE" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input"></td>
            <td colspan="2"></td>
          </tr>
          </tbody>
        </table>
      </form>
    </div>
  </form>

  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'admin', 'echarts', 'table', 'tree', 'form', 'laydate'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,tree = layui.tree
          ,form = layui.form
          ,laydate = layui.laydate
          ,echarts = layui.echarts;

      var data1 = [];
      var paramStcd = '';

      laydate.render({
          elem: '#forecastTime'
          ,type: 'datetime'
      });

      laydate.render({
          elem: '#affectTime'
          ,type: 'datetime'
      });

      tree.render({
          elem: '#area'
          ,id: 'area'
          ,data: data1
          ,onlyIconControl: true
          ,customOperate: true
          ,edit: ['update']
          ,click: function(obj){
              loadStation(obj.data.stcd);
          }
          ,operate: function (obj) {
              var type = obj.type;
              var data = obj.data;
              var elem = obj.elem;
              var deptId = data.id;
              var parentId = data.parentId;
              openForm(deptId);
          }
      });

      function openForm(deptId){
          layer.open({
              type: 1
              ,offset: 'auto'
              ,id: 'layerDemo1'
              ,content: $('#updateform')
              ,area:["900px","420px"]
              ,btn: ['确定', '取消']
              ,btnAlign: 'c' //按钮居中
              ,shade: 0.2 //不显示遮罩
              ,btn1: function(index, layero){
                  var e = each(data1, deptId);
                  $.each(e.plan, function(key, value){
                      var val = $("#updateform input[name=" + key + "]").val();
                      if( val !== undefined ) {
                          e.plan[key] = val;
                      }
                  });
                  e.intv = $("#updateform input[name=INTV]").val();
                  e.ke = $("#updateform input[name=KE]").val();
                  e.xe = $("#updateform input[name=XE]").val();
                  clearForm();
                  layer.closeAll();
              }
              ,btn2: function(index, layero){
                  clearForm();
                  layer.closeAll();
              }
              ,cancel: function(){
                  clearForm();
                  layer.closeAll();
              }
              ,success: function(layero, index){  //弹出成功的回调
                  clearForm();
                  var e = each(data1, deptId);
                  $.each(e.plan, function(key, value){
                      $("#updateform input[name=" + key + "]").val(value);
                  });
                  $("#updateform input[name=INTV]").val(e.intv);
                  $("#updateform input[name=KE]").val(e.ke);
                  $("#updateform input[name=XE]").val(e.xe);
                  $(".model_param_cl").hide();
                  $(".model_param_hl").hide();
                  $("#model_cl_" + e.plan.MODEL_CL).show();
                  $("#model_hl_" + e.plan.MODEL_HL).show();
                  form.render();
              }
          });
      }

      function each(data, deptId) {
          for(var i=0; i<data.length; i++){
              var e = data[i];
              if (e.id == deptId) {
                  return e;
              }
              if (e.children != undefined && e.children.length > 0) {
                  var e = each(e.children, deptId);
                  if( e != undefined ){
                      return e;
                  }
              }
          }
          return undefined;
      }

      function clearForm() {
          $("#addform input").val('');
          form.render();
      }

      <%--form.on('select(sttp)', function(data){--%>
          <%--$("select[name=station]").html('<option value="">请选择</option>');--%>
          <%--$("select[name=model]").html('<option value="">请选择</option>');--%>
          <%--form.render('select');--%>
          <%--var sttp = $("select[name=sttp]").val();--%>
          <%--if( sttp != "" ){--%>
              <%--var loading = layer.load(0);--%>
              <%--$.post(--%>
                  <%--'${pageContext.request.contextPath}/station/getStation',--%>
                  <%--{--%>
                      <%--sttp: sttp--%>
                  <%--},--%>
                  <%--function (data) {--%>
                      <%--var html = '';--%>
                      <%--$.each(data, function (key, value) {--%>
                          <%--html += '<option value="' + value.stcd + '">' + value.stname + '</option>';--%>
                      <%--});--%>
                      <%--$("select[name=station]").append(html);--%>
                      <%--form.render('select');--%>
                      <%--layer.close(loading);--%>
                  <%--}--%>
              <%--);--%>
          <%--}--%>
      <%--});--%>

      <%--/* 根据站点获取河系 */--%>
      <%--form.on('select(station)', function(data){--%>
          <%--var loading = layer.load(0);--%>
          <%--$("select[name=model]").html('<option value="">请选择</option>');--%>
          <%--form.render('select');--%>
          <%--$.post(--%>
              <%--'${pageContext.request.contextPath}/model/getModel',--%>
              <%--{--%>
                  <%--stcd: data.value--%>
              <%--},--%>
              <%--function (data) {--%>
                  <%--var html = '';--%>
                  <%--$.each(data, function (key, value) {--%>
                      <%--html += '<option value="' + value.id + '">' + value.name + '</option>';--%>
                  <%--});--%>
                  <%--$("select[name=model]").append(html);--%>
                  <%--form.render('select');--%>
                  <%--layer.close(loading);--%>
              <%--}--%>
          <%--);--%>
      <%--});--%>

      /* 根据河系获取流域河系 */
      form.on('select(model)', function(data){
          data1 = {};
          tree.reload('area', {
              data: data1
          });
          if( data.value != '' ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/model/getArea',
                {
                    modelId: data.value
                },
                function (data) {
                    data1 = data;
                    // if(data1.length > 0){
                    //     setParam(data1[0]);
                    // }else{
                    //     clearParam();
                    // }
                    tree.reload('area', {
                        data: data1
                    });
                    layer.close(loading);
                }
            );
          }
      });

      function loadStation(stcd) {
          var loading = layer.load(0);
          $.post({
              url: "${pageContext.request.contextPath}/forecast/station",
              contentType: "application/x-www-form-urlencoded",
              data: {
                  stcd: stcd,
              },
              success : function(data) {
                  if( data.code == 200 ) {
                      draw(data.data);
                  }else{
                      parent.layer.alert(data.msg, {title: '错误'});
                  }
                  layer.close(loading);
              }
          }).fail(function(response) {
              parent.layer.alert("计算出错，请重试", {title: '错误'});
              layer.close(loading);
          });
          return false;
      }

      // function setParam(data) {
      //     paramStcd = data.stcd;
      //     $(".stname").html(data.stname);
      //
      //     $("input[name=SM]").val(data.plan.SM);
      //     $("input[name=CI]").val(data.plan.CI);
      //     $("input[name=CS]").val(data.plan.CS);
      //     $("input[name=L]").val(data.plan.L);
      //     $("input[name=KE]").val(data.KE);
      //     $("input[name=XE]").val(data.XE);
      //
      //     $("input[name=WU0]").val(data.plan.WU0);
      //     $("input[name=WL0]").val(data.plan.WL0);
      //     $("input[name=WD0]").val(data.plan.WD0);
      //     $("input[name=S0]").val(data.plan.S0);
      //     $("input[name=FR0]").val(data.plan.FR0);
      //     $("input[name=QRS0]").val(data.plan.QRS0);
      //     $("input[name=QRSS0]").val(data.plan.QRSS0);
      //     $("input[name=QRG0]").val(data.plan.QRG0);
      // }
      //
      // function clearParam() {
      //     paramStcd = "";
      //     $(".stname").html("");
      //
      //     $("input[name=SM]").val("");
      //     $("input[name=CI]").val("");
      //     $("input[name=CS]").val("");
      //     $("input[name=L]").val("");
      //     $("input[name=KE]").val("");
      //     $("input[name=XE]").val("");
      //
      //     $("input[name=WU0]").val("");
      //     $("input[name=WL0]").val("");
      //     $("input[name=WD0]").val("");
      //     $("input[name=S0]").val("");
      //     $("input[name=FR0]").val("");
      //     $("input[name=QRS0]").val("");
      //     $("input[name=QRSS0]").val("");
      //     $("input[name=QRG0]").val("");
      // }
      //
      // function updateParam() {
      //     function each(data) {
      //         data.forEach(function (e, index) {
      //             if (e.stcd == paramStcd) {
      //                 e.plan.sm = $("input[name=SM]").val();
      //                 e.plan.ci = $("input[name=CI]").val();
      //                 e.plan.cs = $("input[name=CS]").val();
      //                 e.plan.l = $("input[name=L]").val();
      //                 e.ke = $("input[name=KE]").val();
      //                 e.xe = $("input[name=XE]").val();
      //
      //                 e.plan.wu0 = $("input[name=WU0]").val();
      //                 e.plan.wl0 = $("input[name=WL0]").val();
      //                 e.plan.wd0 = $("input[name=WD0]").val();
      //                 e.plan.s0 = $("input[name=S0]").val();
      //                 e.plan.fr0 = $("input[name=FR0]").val();
      //                 e.plan.qrs0 = $("input[name=QRS0]").val();
      //                 e.plan.qrss0 = $("input[name=QRSS0]").val();
      //                 e.plan.qrg0 = $("input[name=QRG0]").val();
      //
      //                 return;
      //             }
      //             if ( e.children != undefined && e.children.length > 0) {
      //                 each(e.children);
      //             }
      //         })
      //     }
      //     each(data1);
      // }
      //
      // $(".table-param input").keyup(function () {
      //     updateParam();
      // });

      var chart = echarts.init(document.getElementById('chart'));

      function draw(data) {
          console.log(data);
          var option = {
              title: {
                  text: '今日流量趋势',
                  x: 'center',
                  textStyle: {
                      fontSize: 14
                  }
              },
              tooltip: {
                  trigger: 'axis'
              },
              legend: {
                  data: ['', '']
              },
              xAxis: [
                  {
                      type: 'category',
                      boundaryGap: false,
                      data: data.timeArr
                  }
              ],
              yAxis: [
                  {
                      type: 'value'
                  },
                  {
                      name: '降雨量(mm)',
                      nameLocation: 'start',
                      max: data.rainfallMax,
                      type: 'value',
                      inverse: true
                  }
              ],
              series: [
                  {
                      name:'降雨量',
                      type:'bar',
                      // itemStyle:{
                      //     normal:{
                      //         color:'#7EC0EE',
                      //
                      //     }
                      // },
                      // barWidth: '40%',
                      yAxisIndex:1,
                      // animation: true,
                      data: data.P
                  },
                  {
                      name:'QTRR',
                      type:'line',
                      animation: true,
                      smooth: true,
                      symbol: 'circle',
                      symbolSize: 1,
                      itemStyle:{
                          normal:{
                              color:'#7EC0EE',
                          }
                      },
                      lineStyle: {
                          normal: {
                              color:'#7EC0EE',
                              width: 3,
                              shadowColor: 'rgba(0,0,0,0.4)',
                              shadowBlur: 10,
                              shadowOffsetY: 10
                          }
                      },
                      data: data.QTRR
                  },
                  {
                      name:'QT',
                      type:'line',
                      animation: true,
                      smooth: true,
                      symbol: 'circle',
                      symbolSize: 1,
                      itemStyle:{
                          normal:{
                              color:'#ff0000',

                          }
                      },
                      lineStyle: {
                          normal: {
                              color:'#ff0000',
                              width: 3,
                              shadowColor: 'rgba(0,0,0,0.4)',
                              shadowBlur: 10,
                              shadowOffsetY: 10
                          }
                      },
                      data: data.QT,
                      // markLine: {
                      //     itemStyle: {
                      //         normal: { lineStyle: { color:'#CD2626' },
                      //             label: { show: true , position:'middle' }
                      //         }
                      //     },
                      //     data: [
                      //
                      //         {
                      //             name: '警戒水位',
                      //             yAxis: 22
                      //         }
                      //     ]
                      // },
                      // markPoint: {
                      //     symbolSize: 65,
                      //     data: [
                      //         {type: 'max', name: '最大值'}
                      //
                      //     ]
                      // }
                  }
              ]
          };
          chart.setOption(option);
      }

      /* 预报流量 */
      form.on('submit(forecast1)', function(data){
          if ($("select[name=model]").val() == "" ||
              $("input[name=forecastTime]").val() == "" ||
              $("input[name=affectTime]").val() == "" ||
              $("select[name=day]").val() == "" ||
              $("select[name=unit]").val() == "" ||
              data1.length == 0
          ) {
              layer.msg('请填妥相关信息');
              return false;
          }
          var loading = layer.load(0);
          $.post({
              url: "${pageContext.request.contextPath}/forecast/compute",
              contentType: "application/x-www-form-urlencoded",
              data: {
                  type: 1,
                  stcd: data1[0].stcd,
                  forecastTime: $("input[name=forecastTime]").val(),
                  affectTime: $("input[name=affectTime]").val(),
                  day: $("select[name=day]").val(),
                  unit: $("select[name=unit]").val(),
                  data: JSON.stringify(data1)
              },
              success : function(data) {
                  if( data.code == 200 ) {
                      draw(data.data);
                  }else{
                      parent.layer.alert(data.msg, {title: '错误'});
                  }
                  layer.close(loading);
              }
          }).fail(function(response) {
              parent.layer.alert("计算出错，请重试", {title: '错误'});
              layer.close(loading);
          });
          return false;
      });

      /* 预报水位 */
      form.on('submit(forecast2)', function(data){
          // parent.layer.alert(JSON.stringify(data.field), {
          //   title: '最终的提交信息'
          // })
          var submit = true;
          if ($("input[name=name]").val() == "" ||
              data1.length == 0) {
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
          $.post({
              url: "${pageContext.request.contextPath}/model/insert" + update,
              contentType: "application/x-www-form-urlencoded",
              data: {
                  name: $("input[name=name]").val(),
                  stcd: $("input[name=stcd]").val(),
                  data: JSON.stringify(data1)
              },
              success : function(result) {
                  parent.layer.alert("数据保存成功", {
                      title: '成功'
                  }, function () {
                      alert('关闭当前页面');
                  })
              }
          }).fail(function(response) {
              parent.layer.alert("数据保存失败", {
                  title: '错误'
              })
          });
          return false;
      });

  });

  function zhzs(value) {
      value = parseFloat(value.replace(/^0{1,}/g, '')).toFixed(3);
      if( !isNaN(value) ){
          return value;
      }else{
          return parseFloat(0).toFixed(3);
      }
  }
  </script>
</body>
</html>

