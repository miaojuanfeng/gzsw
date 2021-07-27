package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
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
	public JSONObject list(Integer page, Integer limit, String sttp, String stcd, String name) {
		return RetVal.OK(rainRunService.selectCount(sttp, stcd, name), rainRunService.selectList(page, limit, sttp, stcd, name));
	}

	@PostMapping("getRainRun")
	@ResponseBody
	public JSONObject getRainRun(String stcd) {
		return RetVal.OK(rainRunService.selectListByStcd(stcd));
	}

	@PostMapping("pointList")
	@ResponseBody
	public Map pointList(Integer pid) {
		List listRainRun = rainRunService.selectPointList(pid);
		return RetVal.OK(listRainRun.size(), listRainRun);
	}
}
