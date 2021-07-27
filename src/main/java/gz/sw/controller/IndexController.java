package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.common.SessionUser;
import gz.sw.constant.CommonConst;
import gz.sw.entity.write.Station;
import gz.sw.entity.write.User;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.StationService;
import gz.sw.service.write.UserService;
import gz.sw.util.DateUtil;
import gz.sw.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    @Autowired
    private StationService stationService;

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
        User user = userService.selectByPhone(username);
//        System.out.println(Md5Util.execute(CommonConst.SECREAT_KEY + password));
        if( user != null ) {
            if( user.getPassword().equals(Md5Util.execute(CommonConst.SECREAT_KEY + password)) ){
                SessionUser sessionUser = new SessionUser();
                sessionUser.setId(user.getId());
                sessionUser.setPhone(user.getPhone());
                sessionUser.setName(user.getName());
                sessionUser.setAdmin(user.getAdmin());
                request.getSession().setAttribute(CommonConst.SESSION_USER, sessionUser);

                return RetVal.OK();
            }
        }
        return RetVal.Error("账号或密码错误");
    }

    @PostMapping("logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(CommonConst.SESSION_USER);
        return RetVal.OK();
    }

    @GetMapping("home/console")
    public String console(ModelMap map) {
        Date date = new Date();
        map.put("endTime", DateUtil.date2str(date, "yyyy-MM-dd HH:00:00"));
        map.put("startTime", DateUtil.date2str(DateUtil.addMonth(date, -1), "yyyy-MM-dd HH:00:00"));
//        map.put("startTime", "2019-06-01 08:00:00");
//        map.put("endTime", "2019-06-04 08:00:00");
        List<Map> baseStation = stationService.selectListByType(StationTypeEnum.getCode(StationTypeEnum.ZQ.getId()));
        List<List> stations = new ArrayList<>();
        List<Map> child = new ArrayList<>();
        for (Map station : baseStation){
            child.add(station);
            if( child.size() >= 4 ){
                stations.add(child);
                child = new ArrayList();
            }
        }
        if( child.size() > 0 ){
            stations.add(child);
            child = new ArrayList();
        }
        map.put("stations", stations);
        return "home/console";
    }

//    @GetMapping("views/{folder}/{file}")
//    public String view(@PathVariable("folder") String folder, @PathVariable("file") String file) {
//        return folder + "/" + file;
//    }


}
