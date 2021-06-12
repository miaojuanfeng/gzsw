package gz.sw.controller;


import gz.sw.service.read.ReadService;
import gz.sw.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private ReadService readService;

//	@GetMapping("{stcd}")
//	public String station(ModelMap map, @PathVariable("stcd") String stcd) {
////		map.put("basePath", BASE_PATH);
////		map.put("stcd", stcd);
//        map.put("chart1", chart1(stcd));
//        map.put("chart2", chart2(stcd));
//        map.put("chart3", chart3(stcd));
//		map.put("chart4", chart4(stcd));
//		return "views/home/station";
//	}

	@GetMapping("list")
	public String list() {
		return "user/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(Integer page, Integer limit) {
		Map retval = new HashMap();
		retval.put("code", 0);
//		retval.put("count", stationService.selectCount());
//		retval.put("data", stationService.selectList(page, limit));
		return retval;
	}
}
