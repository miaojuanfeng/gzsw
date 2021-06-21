package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.entity.write.Model;
import gz.sw.entity.write.Plan;
import gz.sw.enums.ModelTypeEnum;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("plan")
public class PlanController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private PlanService planService;

	@Autowired
	private ModelStationService modelStationService;

	@Autowired
	private RainRunService rainRunService;

	@Autowired
	private UnitLineService unitLineService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "plan/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(String sttp, String stcd, String name, Integer page, Integer limit) {
		Map retval = new HashMap();
		retval.put("code", 0);
		retval.put("count", planService.selectCount(sttp, stcd, name));
		retval.put("data", planService.selectList(sttp, stcd, name, page, limit));
		return retval;
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("modelCls", ModelTypeEnum.getList(ModelTypeEnum.MODEL_TYPE_CL));
		map.put("modelHls", ModelTypeEnum.getList(ModelTypeEnum.MODEL_TYPE_HL));
		return "plan/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	public JSONObject insert(String name, String stcd, Plan plan) {
		JSONObject retval = new JSONObject();
		plan.setName(name);
		plan.setStcd(stcd);
		plan.setCreateTime(new Date());
		planService.insert(plan);
		return retval;
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("modelCls", ModelTypeEnum.getList(ModelTypeEnum.MODEL_TYPE_CL));
		map.put("modelHls", ModelTypeEnum.getList(ModelTypeEnum.MODEL_TYPE_HL));
		Map plan = planService.selectMap(id);
		map.put("stations", stationService.selectListByType(String.valueOf(plan.get("sttype"))));
		map.put("rainRuns", rainRunService.selectListByStcd(String.valueOf(plan.get("STCD"))));
		map.put("unitLines", unitLineService.selectListByStcd(String.valueOf(plan.get("STCD"))));
		map.put("plan", plan);
		return "plan/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	public JSONObject update(@PathVariable("id") Integer id, Plan plan) {
		JSONObject retval = new JSONObject();
		plan.setId(id);
		planService.update(plan);
		return retval;
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		JSONObject retval = new JSONObject();
		int count = modelStationService.selectCountByPlan(id);
		if( count > 0 ){
			retval.put("code", 500);
			retval.put("msg", "有" + count + "个河系正在使用该方案，请取消使用后再删除");
			return retval;
		}
		planService.delete(id);
		retval.put("code", 200);
		retval.put("msg", "");
		return retval;
	}

    @PostMapping("getPlan")
    @ResponseBody
    public List getPlan(String stcd) {
        return planService.selectListByStcd(stcd);
    }

	@PostMapping("getUnitLine")
	@ResponseBody
	public List getUnitLine(String stcd) {
		return unitLineService.selectListByStcd(stcd);
	}
}
