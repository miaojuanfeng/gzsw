package gz.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年09月04日
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index() {
        return "index";
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
