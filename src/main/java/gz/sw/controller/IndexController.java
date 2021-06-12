package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.SessionUser;
import gz.sw.constant.CommonConst;
import gz.sw.entity.write.User;
import gz.sw.service.write.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年09月04日
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request, ModelMap map) {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
        map.put("sessionUser", sessionUser);
        return "index";
    }

    @GetMapping("login")
    public String login(ModelMap map) {
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) throws IOException {
        JSONObject retval = new JSONObject();
        User user = userService.selectByPhone(username);
        if( user != null ) {
            if( user.getPassword().equals(password) ){
                SessionUser sessionUser = new SessionUser();
                sessionUser.setUserPhone(user.getPhone());
                sessionUser.setUserName(user.getName());
                request.getSession().setAttribute(CommonConst.SESSION_USER, sessionUser);

                retval.put("code", 200);
                retval.put("msg", "");
                return retval;
            }
        }
        retval.put("code", 500);
        retval.put("msg", "账号或密码错误");
        return retval;
    }

    @PostMapping("logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject retval = new JSONObject();
        request.getSession().removeAttribute(CommonConst.SESSION_USER);
        retval.put("code", 200);
        retval.put("msg", "");
        return retval;
    }

    @GetMapping("home/console")
    public String console(ModelMap map) {
        map.put("startTime", "2019-06-01 08:00:00");
        map.put("endTime", "2019-06-04 08:00:00");
        return "home/console";
    }

//    @GetMapping("views/{folder}/{file}")
//    public String view(@PathVariable("folder") String folder, @PathVariable("file") String file) {
//        return folder + "/" + file;
//    }


}
