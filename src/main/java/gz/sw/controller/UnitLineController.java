package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.RainRunService;
import gz.sw.service.write.UnitLineService;
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
@RequestMapping("unitLine")
public class UnitLineController {
	
	@Autowired
	private UnitLineService unitLineService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "unitline/list";
	}

	@PostMapping("list")
	@ResponseBody
	public JSONObject list(Integer page, Integer limit, String sttp, String stcd, String name) {
		return RetVal.OK(unitLineService.selectCount(sttp, stcd, name), unitLineService.selectList(page, limit, sttp, stcd, name));
	}

	@PostMapping("getUnitLine")
	@ResponseBody
	public JSONObject getUnitLine(String stcd) {
		return RetVal.OK(unitLineService.selectListByStcd(stcd));
	}

	@PostMapping("pointList")
	@ResponseBody
	public JSONObject pointList(Integer lid) {
		List listRainRun = unitLineService.selectPointList(lid);
		return RetVal.OK(listRainRun.size(), listRainRun);
	}
}
