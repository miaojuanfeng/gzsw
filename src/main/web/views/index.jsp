<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>洪水预报 - 赣州水文</title>
  <%@ include file="linker.jsp" %>
  <script>
  /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
  </script>
</head>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          
          <%--<li class="layui-nav-item" lay-unselect>--%>
            <%--<a lay-href="app/message/index.html" layadmin-event="message" lay-text="消息中心">--%>
              <%--<i class="layui-icon layui-icon-notice"></i>  --%>
              <%----%>
              <%--<!-- 如果有新消息，则显示小圆点 -->--%>
              <%--<span class="layui-badge-dot"></span>--%>
            <%--</a>--%>
          <%--</li>--%>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="note">
              <i class="layui-icon layui-icon-note"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>${sessionUser.name}</cite>
            </a>
            <dl class="layui-nav-child">
              <%--<dd><a lay-href="set/user/info.html">基本资料</a></dd>--%>
              <dd><a lay-href="<c:url value="user/self"></c:url>">修改资料</a></dd>
              <%--<hr>--%>
              <dd style="text-align: center;"><a id="logout" href="javascript:;">退出</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block" style="width: 20px" lay-unselect></li>
          
          <%--<li class="layui-nav-item layui-hide-xs" lay-unselect>--%>
            <%--<a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>--%>
          <%--</li>--%>
          <%--<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>--%>
            <%--<a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>--%>
          <%--</li>--%>
        </ul>
      </div>
      
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="home/console.html">
            <span>赣州水文</span>
          </div>
          
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
            <li data-name="home" class="layui-nav-item layui-nav-itemed">
              <a href="javascript:;" lay-tips="预报" lay-direction="2">
                <i class="layui-icon layui-icon-water"></i>
                <cite>预报</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="forecast/home?show=1"></c:url>">河系预报</a>
                </dd>
              </dl>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="forecast/home?show=2"></c:url>">单站预报</a>
                </dd>
              </dl>
            </li>
            <li data-name="home" class="layui-nav-item layui-nav-itemed">
              <a href="javascript:;" lay-tips="站点" lay-direction="2">
                  <i class="layui-icon layui-icon-template"></i>
                  <cite>站点</cite>
              </a>
              <dl class="layui-nav-child">
                  <dd data-name="console">
                      <a lay-href="<c:url value="station/list"></c:url>">站点列表</a>
                  </dd>
              </dl>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="station/unusual"></c:url>">异常雨量</a>
                </dd>
              </dl>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="rainRun/list"></c:url>">降径关系线</a>
                </dd>
              </dl>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="unitLine/list"></c:url>">经验单位线</a>
                </dd>
              </dl>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="discharge/list"></c:url>">泄流曲线</a>
                </dd>
              </dl>
            </li>
            <li data-name="home" class="layui-nav-item">
              <a href="javascript:;" lay-tips="实测雨量方案" lay-direction="2">
                <i class="layui-icon layui-icon-water"></i>
                <cite>实测雨量方案</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="rain/list"></c:url>">实测雨量方案列表</a>
                </dd>
                <dd data-name="console">
                  <a lay-href="<c:url value="rain/insert"></c:url>">新建实测雨量方案</a>
                </dd>
              </dl>
            </li>
            <li data-name="home" class="layui-nav-item">
              <a href="javascript:;" lay-tips="未来雨量方案" lay-direction="2">
                <i class="layui-icon layui-icon-water"></i>
                <cite>未来雨量方案</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="rainf/list"></c:url>">未来雨量方案列表</a>
                </dd>
                <dd data-name="console">
                  <a lay-href="<c:url value="rainf/insert"></c:url>">新建未来雨量方案</a>
                </dd>
              </dl>
            </li>
            <li data-name="home" class="layui-nav-item">
              <a href="javascript:;" lay-tips="方案" lay-direction="2">
                <i class="layui-icon layui-icon-survey"></i>
                <cite>单站方案</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="plan/list"></c:url>">单站方案列表</a>
                </dd>
                <dd data-name="console">
                  <a lay-href="<c:url value="plan/insert"></c:url>">新建单站方案</a>
                </dd>
              </dl>
            </li>
            <li data-name="home" class="layui-nav-item">
              <a href="javascript:;" lay-tips="河系" lay-direction="2">
                <i class="layui-icon layui-icon-component"></i>
                <cite>河系方案</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="model/list"></c:url>">河系方案列表</a>
                </dd>
                <dd data-name="console">
                  <a lay-href="<c:url value="model/insert"></c:url>">新建河系方案</a>
                </dd>
              </dl>
            </li>
            <li data-name="home" class="layui-nav-item">
              <a href="javascript:;" lay-tips="用户" lay-direction="2">
                <i class="layui-icon layui-icon-user"></i>
                <cite>用户</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console">
                  <a lay-href="<c:url value="user/self"></c:url>">修改资料</a>
                </dd>
                <c:if test="${sessionUser.admin==1}">
                <dd data-name="console">
                  <a lay-href="<c:url value="user/list"></c:url>">用户列表</a>
                </dd>
                <dd data-name="console">
                  <a lay-href="<c:url value="user/insert"></c:url>">新增用户</a>
                </dd>
                </c:if>
              </dl>
            </li>
          </ul>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="<c:url value="/home/console"></c:url>" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
      
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>

  <script>
    layui.config({
        base: base //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index', function () {
        var $ = layui.$
            ,admin = layui.admin;
        ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

        $("#logout").click(function () {
            commonConfirm("logout", "确认登出吗？", null, function () {
                admin.exit(function(){
                    location.href = "login";
                });
            });
        });
    });
  </script>
</body>
</html>


