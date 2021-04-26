package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.entity.write.Model;
import gz.sw.entity.write.Plan;
import gz.sw.enums.ModelTypeEnum;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.PlanService;
import gz.sw.service.write.StationService;
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

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "plan/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(Integer page, Integer limit, String sttp, String stcd, String name) {
		Map retval = new HashMap();
		retval.put("code", 0);
		retval.put("count", planService.selectCount(sttp, stcd, name));
		retval.put("data", planService.selectList(page, limit, sttp, stcd, name));
		return retval;
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
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
		Map plan = planService.selectMap(id);
		List stations = stationService.selectListByType(String.valueOf(plan.get("sttype")));
        map.put("stations", stations);
		map.put("models", ModelTypeEnum.getList(Integer.valueOf(String.valueOf(plan.get("MTYPE")))));
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
		planService.delete(id);
		return retval;
	}

    @PostMapping("getPlan")
    @ResponseBody
    public List getPlan(String stcd, Integer model) {
        return planService.selectListByStcd(stcd, model);
    }
}
