package gz.sw.controller;

import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.RainRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("rainRun")
public class RainRunController {
	
	@Autowired
	private RainRunService rainRunService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "rainrun/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(Integer page, Integer limit, String sttp, String stcd, String name) {
		Map retval = new HashMap();
		retval.put("code", 0);
		retval.put("count", rainRunService.selectCount(sttp, stcd, name));
		retval.put("data", rainRunService.selectList(page, limit, sttp, stcd, name));
		return retval;
	}

	@PostMapping("getRainRun")
	@ResponseBody
	public List getRainRun(String stcd) {
		return rainRunService.selectListByStcd(stcd);
	}

	@PostMapping("pointList")
	@ResponseBody
	public Map pointList(Integer pid) {
		Map retval = new HashMap();
		List listRainRun = rainRunService.selectPointList(pid);
		retval.put("code", 0);
		retval.put("count", listRainRun.size());
		retval.put("data", listRainRun);
		return retval;
	}
}
