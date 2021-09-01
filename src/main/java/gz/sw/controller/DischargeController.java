package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.entity.write.Discharge;
import gz.sw.entity.write.UnitLine;
import gz.sw.enums.StationTypeEnum;
import gz.sw.mapper.write.DischargeDao;
import gz.sw.service.write.DischargeService;
import gz.sw.service.write.StationService;
import gz.sw.service.write.UnitLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("discharge")
public class DischargeController {
	
	@Autowired
	private DischargeService dischargeService;

	@Autowired
	private StationService stationService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("stations", stationService.selectListByType(StationTypeEnum.getCode(StationTypeEnum.RR.getId())));
		return "discharge/list";
	}

	@PostMapping("list")
	@ResponseBody
	public JSONObject list(Integer page, Integer limit, String sttp, String stcd, String name) {
		return RetVal.OK(dischargeService.selectCount(sttp, stcd, name), dischargeService.selectList(page, limit, sttp, stcd, name));
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		dischargeService.deletePoint(id);
		dischargeService.delete(id);
		return RetVal.OK();
	}

	@PostMapping("getDischarge")
	@ResponseBody
	public JSONObject getDischarge(String stcd) {
		return RetVal.OK(dischargeService.selectListByStcd(stcd));
	}

	@PostMapping("pointList")
	@ResponseBody
	public JSONObject pointList(Integer lid) {
		List listDischarge = dischargeService.selectPointList(lid);
		return RetVal.OK(listDischarge.size(), listDischarge);
	}
}
