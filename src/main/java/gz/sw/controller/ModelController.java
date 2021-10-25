package gz.sw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.entity.write.Model;
import gz.sw.entity.write.ModelStation;
import gz.sw.entity.write.Plan;
import gz.sw.enums.ModelTypeEnum;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.ModelService;
import gz.sw.service.write.ModelStationService;
import gz.sw.service.write.PlanService;
import gz.sw.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("model")
public class ModelController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private PlanService planService;

	@Autowired
	private ModelStationService modelStationService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "model/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(Integer page, Integer limit, String sttp, String stcd, String name) {
		return RetVal.OK(modelService.selectCount(sttp, stcd, name), modelService.selectList(page, limit, sttp, stcd, name));
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("data", new JSONArray().toString());
		return "model/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	@Transactional
	public JSONObject insert(String name, String stcd, String data) {
		Model model = new Model();
		model.setName(name);
		model.setStcd(stcd);
		model.setCreateTime(new Date());
		modelService.insert(model);

		List<ModelStation> modelStationList = setModelStationData(model.getId(), "0", JSONArray.parseArray(data));
		if( modelStationList.size() > 0 ) {
			modelStationService.insertBatch(modelStationList);
		}

		return RetVal.OK();
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		map.put("sttps", StationTypeEnum.getList());
		Model model = modelService.select(id);
		List t = modelStationService.selectByModel(id);
		JSONArray m = getModelStationData("0", t);
		map.put("id", model.getId());
		map.put("name", model.getName());
		map.put("stcd", model.getStcd().trim());
		map.put("data", m.toString());
		return "model/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	@Transactional
	public JSONObject update(@RequestParam("name") String name, @RequestParam("stcd") String stcd, @RequestParam("data") String data, @PathVariable("id") Integer id) {
		Model model = new Model();
		model.setId(id);
		model.setName(name);
		model.setStcd(stcd);
		modelService.update(model);

		modelStationService.deleteByModel(id);
		List<ModelStation> modelStationList = setModelStationData(model.getId(), "0", JSONArray.parseArray(data));
		if( modelStationList.size() > 0 ) {
			modelStationService.insertBatch(modelStationList);
		}

		return RetVal.OK();
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		modelService.delete(id);
		modelStationService.deleteByModel(id);
		return RetVal.OK();
	}

	private JSONArray getModelStationData(String fatherId, List<Map> modelStationList){
		JSONArray retval = new JSONArray();
		for (int i = 0; i < modelStationList.size(); i++){
			Map modelStation = modelStationList.get(i);
			String m = (String)modelStation.get("FA_STCD");
			if( fatherId.trim().equals(String.valueOf(modelStation.get("FA_STCD")).trim()) ){
				JSONObject station = new JSONObject();
				String title = String.valueOf(modelStation.get("STNAME")).trim();
				title += "（";
				title += StationTypeEnum.getText(String.valueOf(modelStation.get("STTP")).trim());
				title += "）";
				station.put("title", title);
				station.put("stcd", String.valueOf(modelStation.get("STCD")).trim());
				station.put("stname", String.valueOf(modelStation.get("STNAME")).trim());
				station.put("sttp", String.valueOf(modelStation.get("STTP")).trim());
                station.put("sttpName", StationTypeEnum.getText(String.valueOf(modelStation.get("STTP")).trim()));
				station.put("planId", modelStation.get("PLAN_ID"));
				station.put("planName", modelStation.get("PLAN_NAME"));
				station.put("intv", modelStation.get("INTV"));
				station.put("ke", modelStation.get("KE"));
				station.put("xe", modelStation.get("XE"));
				station.put("id", modelStation.get("ID"));
				station.put("spread", true);
				JSONArray children = getModelStationData((String)modelStation.get("STCD"), modelStationList);
				if( children.size() > 0 ){
					station.put("children", children);
				}
				retval.add(station);
			}
		}
		return retval;
	}

	private List<ModelStation> setModelStationData(Integer modelId, String fatherId, JSONArray data){
		List<ModelStation> retval = new ArrayList<>();
		for (int i = 0; i < data.size(); i++){
			JSONObject station = (JSONObject) data.get(i);
			ModelStation modelStation = new ModelStation();
			modelStation.setModelId(modelId);
			modelStation.setStcd(station.getString("stcd"));
			modelStation.setPlanId(station.getInteger("planId"));
			modelStation.setKe(station.getBigDecimal("ke"));
			modelStation.setXe(station.getBigDecimal("xe"));
			modelStation.setIntv(station.getBigDecimal("intv"));
			modelStation.setFaStcd(fatherId);
			retval.add(modelStation);

			JSONArray children = station.getJSONArray("children");
			if( children != null && children.size() > 0 ){
				retval.addAll(setModelStationData(modelId, station.getString("stcd"), children));
			}
		}
		return retval;
	}

	@PostMapping("getType")
	@ResponseBody
	public JSONObject getType(Integer type) {
		return RetVal.OK(ModelTypeEnum.getList(type));
	}

	@PostMapping("getModel")
	@ResponseBody
	public JSONObject getModel(String stcd) {
		return RetVal.OK(modelService.selectListByStcd(stcd));
	}

	@PostMapping("getArea")
	@ResponseBody
	public JSONObject getArea(@RequestParam("modelId") Integer modelId) {
		JSONArray modelStationList = getModelStationData("0", modelStationService.selectByModel(modelId));
		setModelStationPlan(modelStationList);
		return RetVal.OK(modelStationList);
	}

	private void setModelStationPlan(JSONArray modelStationList){
		for (int i = 0; i < modelStationList.size(); i++){
			JSONObject modelStation = (JSONObject) modelStationList.get(i);
			if( modelStation.containsKey("children") && modelStation.getJSONArray("children").size() > 0 ) {
				setModelStationPlan(modelStation.getJSONArray("children"));
			}
			Map plan = planService.selectMap(modelStation.getInteger("planId"));
			plan.put("update", false);
			modelStation.put("plan", plan);
		}
	}
}
