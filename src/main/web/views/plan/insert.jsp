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
            <label class="layui-form-label">实测雨量方案</label>
            <div class="layui-input-block">
                <select name="rain" lay-filter="rain" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${rains}" var="rain" varStatus="id">
                        <option value="${rain.id}" <c:if test="${plan.RAIN==rain.id}">selected</c:if>>${rain.name}</option>
                    </c:forEach>
                </select>
            </div>
          </div>

        <div class="layui-form-item">
            <label class="layui-form-label">未来雨量方案</label>
            <div class="layui-input-block">
                <select name="rainf" lay-filter="rainf" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${rainfs}" var="rainf" varStatus="id">
                        <option value="${rainf.id}" <c:if test="${plan.RAINF==rainf.id}">selected</c:if>>${rainf.name}</option>
                    </c:forEach>
                </select>
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

          <div id="rainRun" class="layui-form-item" style="<c:if test="${plan.MODEL_CL==3}">display:block;</c:if>">
            <label class="layui-form-label">降径关系线</label>
            <div class="layui-input-block">
                <select name="rainRun" lay-filter="rainRun" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${rainRuns}" var="rainRun" varStatus="id">
                        <option value="${rainRun.id}" <c:if test="${plan.RAINRUN==rainRun.id}">selected</c:if>>${rainRun.name}</option>
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

          <div id="unitLine" class="layui-form-item" style="<c:if test="${plan.MODEL_HL==4}">display:block;</c:if>">
            <label class="layui-form-label">经验单位线</label>
            <div class="layui-input-block">
                <select name="unitLine" lay-filter="unitLine" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${unitLines}" var="unitLine" varStatus="id">
                        <option value="${unitLine.id}" <c:if test="${plan.UNITLINE==unitLine.id}">selected</c:if>>${unitLine.name}</option>
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
                  <td class="input-tr"><input type="text" name="WU0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.WU0}">${plan.WU0}</c:when><c:otherwise>10</c:otherwise></c:choose>"></td>
                  <td>WL0</td>
                  <td class="input-tr"><input type="text" name="WL0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.WL0}">${plan.WL0}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
                  <td>WD0</td>
                  <td class="input-tr"><input type="text" name="WD0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.WD0}">${plan.WD0}</c:when><c:otherwise>5</c:otherwise></c:choose>"></td>
                  <td>WUM</td>
                  <td class="input-tr"><input type="text" name="WUM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.WUM}">${plan.WUM}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
                </tr>
                <tr>
                  <td>WLM</td>
                  <td class="input-tr"><input type="text" name="WLM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.WLM}">${plan.WLM}</c:when><c:otherwise>80</c:otherwise></c:choose>"></td>
                  <td>WDM</td>
                  <td class="input-tr"><input type="text" name="WDM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.WDM}">${plan.WDM}</c:when><c:otherwise>10</c:otherwise></c:choose>"></td>
                  <td>B</td>
                  <td class="input-tr"><input type="text" name="B" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.B}">${plan.B}</c:when><c:otherwise>0.2</c:otherwise></c:choose>"></td>
                  <td>K</td>
                  <td class="input-tr"><input type="text" name="K" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.K}">${plan.K}</c:when><c:otherwise>0.98</c:otherwise></c:choose>"></td>
                </tr>
                <tr>
                  <td>C</td>
                  <td class="input-tr"><input type="text" name="C" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.C}">${plan.C}</c:when><c:otherwise>0.2</c:otherwise></c:choose>"></td>
                  <td>SM</td>
                  <td class="input-tr"><input type="text" name="SM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.SM}">${plan.SM}</c:when><c:otherwise>20</c:otherwise></c:choose>"></td>
                  <td>EX</td>
                  <td class="input-tr"><input type="text" name="EX" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.EX}">${plan.EX}</c:when><c:otherwise>1.5</c:otherwise></c:choose>"></td>
                  <td>KSS</td>
                  <td class="input-tr"><input type="text" name="KSS" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.KSS}">${plan.KSS}</c:when><c:otherwise>0.15</c:otherwise></c:choose>"></td>
                </tr>
                <tr>
                  <td>KG</td>
                  <td class="input-tr"><input type="text" name="KG" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.KG}">${plan.KG}</c:when><c:otherwise>0.55</c:otherwise></c:choose>"></td>
                  <td>IM</td>
                  <td class="input-tr"><input type="text" name="IM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.IM}">${plan.IM}</c:when><c:otherwise>0.02</c:otherwise></c:choose>"></td>
                  <td>T</td>
                  <td class="input-tr"><input type="text" name="T" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.T}">${plan.T}</c:when><c:otherwise>1</c:otherwise></c:choose>"></td>
                  <td>S0</td>
                  <td class="input-tr"><input type="text" name="S0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.S0}">${plan.S0}</c:when><c:otherwise>3</c:otherwise></c:choose>"></td>
                </tr>
                <tr>
                  <td>FR0</td>
                  <td class="input-tr"><input type="text" name="FR0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.FR0}">${plan.FR0}</c:when><c:otherwise>0.06</c:otherwise></c:choose>"></td>
                  <td>E</td>
                  <td class="input-tr"><input type="text" name="E" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.E}">${plan.E}</c:when><c:otherwise>0.16</c:otherwise></c:choose>"></td>
                  <td colspan="4"></td>
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
                  <td class="input-tr"><input type="text" name="PA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.PA}">${plan.PA}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
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
                  <td class="input-tr"><input type="text" name="KR" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.KR}">${plan.KR}</c:when><c:otherwise>0.65</c:otherwise></c:choose>"></td>
                  <td>IM</td>
                  <td class="input-tr"><input type="text" name="IM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.IM}">${plan.IM}</c:when><c:otherwise>60</c:otherwise></c:choose>"></td>
                  <td>IMM</td>
                  <td class="input-tr"><input type="text" name="IMM" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.IMM}">${plan.IMM}</c:when><c:otherwise>270</c:otherwise></c:choose>"></td>
                  <td>PA</td>
                  <td class="input-tr"><input type="text" name="PA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.PA}">${plan.PA}</c:when><c:otherwise>30</c:otherwise></c:choose>"></td>
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
                          <td>F</td>
                          <td class="input-tr"><input type="text" name="F" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.F}">${plan.F}</c:when><c:otherwise>2373</c:otherwise></c:choose>"></td>
                          <td>CS</td>
                          <td class="input-tr"><input type="text" name="CS" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.CS}">${plan.CS}</c:when><c:otherwise>0.9</c:otherwise></c:choose>"></td>
                          <td>CI</td>
                          <td class="input-tr"><input type="text" name="CI" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.CI}">${plan.CI}</c:when><c:otherwise>0.8</c:otherwise></c:choose>"></td>
                          <td>CG</td>
                          <td class="input-tr"><input type="text" name="CG" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.CG}">${plan.CG}</c:when><c:otherwise>0.99</c:otherwise></c:choose>"></td>
                      </tr>
                      <tr>
                          <td>QRS0</td>
                          <td class="input-tr"><input type="text" name="QRS0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.QRS0}">${plan.QRS0}</c:when><c:otherwise>1</c:otherwise></c:choose>"></td>
                          <td>QRSS0</td>
                          <td class="input-tr"><input type="text" name="QRSS0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.QRSS0}">${plan.QRSS0}</c:when><c:otherwise>1</c:otherwise></c:choose>"></td>
                          <td>QRG0</td>
                          <td class="input-tr"><input type="text" name="QRG0" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.QRG0}">${plan.QRG0}</c:when><c:otherwise>1</c:otherwise></c:choose>"></td>
                          <td>L</td>
                          <td class="input-tr"><input type="text" name="L" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.L}">${plan.L}</c:when><c:otherwise>1</c:otherwise></c:choose>"></td>
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
                          <td class="input-tr"><input type="text" name="NA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.NA}">${plan.NA}</c:when><c:otherwise>4</c:otherwise></c:choose>"></td>
                          <td>NU</td>
                          <td class="input-tr"><input type="text" name="NU" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.NU}">${plan.NU}</c:when><c:otherwise>150</c:otherwise></c:choose>"></td>
                          <td>KG</td>
                          <td class="input-tr"><input type="text" name="KG" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.KG}">${plan.KG}</c:when><c:otherwise>120</c:otherwise></c:choose>"></td>
                          <td>KU</td>
                          <td class="input-tr"><input type="text" name="KU" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.KU}">${plan.KU}</c:when><c:otherwise>2.7</c:otherwise></c:choose>"></td>
                      </tr>
                      <tr>
                          <td>AREA</td>
                          <td class="input-tr"><input type="text" name="AREA" onblur="value=zhzs(this.value)" autocomplete="off" class="layui-input" value="<c:choose><c:when test="${not empty plan.AREA}">${plan.AREA}</c:when><c:otherwise>3338</c:otherwise></c:choose>"></td>
                          <td colspan="6"></td>
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
        $("select[name=rain]").html('<option value="">请选择</option>');
        $("select[name=rainf]").html('<option value="">请选择</option>');
        $("select[name=modelCl]").val('');
        $("select[name=rainRun]").html('<option value="">请选择</option>');
        $("select[name=modelHl]").val('');
        $("select[name=unitLine]").html('<option value="">请选择</option>');
        form.render('select');
        var stcd = $("select[name=station]").val();
        if( stcd != "" ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/rain/getRain',
                {stcd: stcd},
                function (data) {
                    if( data.code == 200 ) {
                        var html = '';
                        $.each(data.data, function (key, value) {
                            html += '<option value="' + value.id + '">' + value.name + '</option>';
                        });
                        $("select[name=rain]").append(html);
                        form.render('select');
                    }
                    $.post(
                        '${pageContext.request.contextPath}/rainf/getRain',
                        {stcd: stcd},
                        function (data) {
                            if( data.code == 200 ) {
                                var html = '';
                                $.each(data.data, function (key, value) {
                                    html += '<option value="' + value.id + '">' + value.name + '</option>';
                                });
                                $("select[name=rainf]").append(html);
                                form.render('select');
                            }
                            layer.close(loading);
                        }
                    );
                }
            );
        }
        form.render('select');
    });

    form.on('select(modelCl)', function(data){
        if(!checkModel()){
            $("select[name=modelCl]").val('');
            $("#rainRun").hide();
            $(".model_param_cl").hide();
            $("#model_cl_").show();
            form.render('select');
            return;
        }
        if( data.value == 3 ){
            $("select[name=rainRun]").html('<option value="">请选择</option>');
            form.render('select');
            var stcd = $("select[name=station]").val();
            if( stcd != "" ){
                var loading = layer.load(0);
                $.post(
                    '${pageContext.request.contextPath}/rainRun/getRainRun',
                    {stcd: stcd},
                    function (data) {
                        if( data.code == 200 ) {
                            var html = '';
                            $.each(data.data, function (key, value) {
                                html += '<option value="' + value.id + '">' + value.name + '</option>';
                            });
                            $("select[name=rainRun]").append(html);
                            form.render('select');
                        }
                        layer.close(loading);
                    }
                );
            }
            $("#rainRun").show();
        }else{
            $("#rainRun").hide();
        }
        $(".model_param_cl").hide();
        $("#model_cl_" + data.value).show();
    });

    form.on('select(modelHl)', function(data){
        if(!checkModel()){
            $("select[name=modelHl]").val('');
            $("#unitLine").hide();
            $(".model_param_hl").hide();
            $("#model_hl_").show();
            form.render('select');
            return;
        }
        if( data.value == 4 ){
            $("select[name=unitLine]").html('<option value="">请选择</option>');
            form.render('select');
            var stcd = $("select[name=station]").val();
            if( stcd != "" ){
                var loading = layer.load(0);
                $.post(
                    '${pageContext.request.contextPath}/unitLine/getUnitLine',
                    {stcd: stcd},
                    function (data) {
                        if( data.code == 200 ) {
                            var html = '';
                            $.each(data.data, function (key, value) {
                                html += '<option value="' + value.id + '">' + value.name + '</option>';
                            });
                            $("select[name=unitLine]").append(html);
                            form.render('select');
                        }
                        layer.close(loading);
                    }
                );
            }
            $("#unitLine").show();
        }else{
            $("#unitLine").hide();
        }
        $(".model_param_hl").hide();
        $("#model_hl_" + data.value).show();
    });

    function checkModel() {
        if( $("select[name=modelCl]").val() != 1 && $("select[name=modelHl]").val() == 2 ){
            layer.msg('滞后演算法只可与蓄满产流法组合计算');
            return false;
        }
        return true;
    }

    form.on('submit(save)', function(data){
        if( $("input[name=name]").val() == "" ){
            layer.msg('请填妥方案名称');
            return false;
        }
        if( $("select[name=station]").val() == "" ){
            layer.msg('请填妥预报站点');
            return false;
        }
        if( $("select[name=rain]").val() == "" ){
            layer.msg('请填妥实测雨量方案');
            return false;
        }
        if( $("select[name=rainf]").val() == "" ){
            layer.msg('请填妥未来雨量方案');
            return false;
        }
        if( $("select[name=modelCl]").val() == "" ){
            layer.msg('请填妥产流方案');
            return false;
        }
        if( $("select[name=modelCl]").val() == 3 && $("select[name=rainrun]").val() == '' ){
            layer.msg('请填妥降雨径流关系线');
            return false;
        }
        if( $("select[name=modelHl]").val() == "" ){
            layer.msg('请填妥汇流方案');
            return false;
        }
        if( $("select[name=modelHl]").val() == 4 && $("select[name=unitline]").val() == '' ){
            layer.msg('请填妥单位线');
            return false;
        }
        var action = $("input[name=id]").val() == '' ? 'insert' : 'update/' + $("input[name=id]").val();
        var data = {};
        var cl = $("#form_model_cl_" + $("select[name=modelCl]").val()).serializeArray();
        var hl = $("#form_model_hl_" + $("select[name=modelHl]").val()).serializeArray();
        var emptyCl = false;
        var emptyHl = false;
        $.each(cl, function() {
            if( this.value == "" ){
                emptyCl = true;
                return false;
            }
            data[this.name] = this.value;
        });
        $.each(hl, function() {
            if( this.value == "" ){
                emptyHl = true;
                return false;
            }
            data[this.name] = this.value;
        });
        if( emptyCl || emptyHl ){
            layer.msg('请填妥相关参数');
            return false;
        }
        data.name = $("input[name=name]").val();
        data.stcd = $("select[name=station]").val();
        data.rain = $("select[name=rain]").val();
        data.rainf = $("select[name=rainf]").val();
        data.modelCl = $("select[name=modelCl]").val();
        data.rainRun = $("select[name=rainRun]").val();
        data.modelHl = $("select[name=modelHl]").val();
        data.unitLine = $("select[name=unitLine]").val();

        var loading = layer.load(0);
        $.post({
            url: "${pageContext.request.contextPath}/plan/" + action,
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
