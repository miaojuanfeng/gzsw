package gz.sw.interceptor;

import gz.sw.common.SessionUser;
import gz.sw.constant.CommonConst;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年03月06日
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("接口拦截器");
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
        if( sessionUser == null ) {
//            System.out.println("拦截");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
//        System.out.println("放行");
        return true;
    }

//    private void error(HttpServletResponse response) throws IOException {
//        response.reset();
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=UTF-8");
//
//        PrintWriter pw = response.getWriter();
//        JSONObject retval = new JSONObject();
//        retval.put("code", 500);
//        retval.put("msg", "无访问权限");
//        pw.write(retval.toString());
//        pw.flush();
//        pw.close();
//    }
}
