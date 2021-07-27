package gz.sw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
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
	private RainService rainService;

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
		return RetVal.OK(planService.selectCount(sttp, stcd, name), planService.selectList(sttp, stcd, name, page, limit));
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
		plan.setName(name);
		plan.setStcd(stcd);
		plan.setCreateTime(new Date());
		planService.insert(plan);
		return RetVal.OK();
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("modelCls", ModelTypeEnum.getList(ModelTypeEnum.MODEL_TYPE_CL));
		map.put("modelHls", ModelTypeEnum.getList(ModelTypeEnum.MODEL_TYPE_HL));
		Map plan = planService.selectMap(id);
		map.put("stations", stationService.selectListByType(String.valueOf(plan.get("sttype"))));
		map.put("rains", rainService.selectListByStcd(String.valueOf(plan.get("STCD"))));
		map.put("rainRuns", rainRunService.selectListByStcd(String.valueOf(plan.get("STCD"))));
		map.put("unitLines", unitLineService.selectListByStcd(String.valueOf(plan.get("STCD"))));
		map.put("plan", plan);
		return "plan/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	public JSONObject update(@PathVariable("id") Integer id, Plan plan) {
		plan.setId(id);
		planService.update(plan);
		return RetVal.OK();
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		int count = modelStationService.selectCountByPlan(id);
		if( count > 0 ){
			return RetVal.Error("有" + count + "个河系正在使用该方案，请取消使用后再删除");
		}
		planService.delete(id);
		return RetVal.OK();
	}

    @PostMapping("getPlan")
    @ResponseBody
    public JSONObject getPlan(String stcd) {
        return RetVal.OK(planService.selectListByStcd(stcd));
    }

	@PostMapping("getUnitLine")
	@ResponseBody
	public JSONObject getUnitLine(String stcd) {
		return RetVal.OK(unitLineService.selectListByStcd(stcd));
	}
}
