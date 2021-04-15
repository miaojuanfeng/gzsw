package gz.sw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
		Map retval = new HashMap();
		retval.put("code", 0);
		retval.put("count", modelService.selectCount(sttp, stcd, name));
		retval.put("data", modelService.selectList(page, limit, sttp, stcd, name));
		return retval;
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
		JSONObject retval = new JSONObject();

		Model model = new Model();
		model.setName(name);
		model.setStcd(stcd);
		model.setCreateTime(new Date());
		modelService.insert(model);

//		JSONArray temp = JSONArray.parseArray(data);
		List<ModelStation> modelStationList = setModelStationData(model.getId(), "0", JSONArray.parseArray(data));
		if( modelStationList.size() > 0 ) {
			modelStationService.insertBatch(modelStationList);
		}
		return retval;
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		map.put("sttps", StationTypeEnum.getList());
		Model model = modelService.select(id);
		List t = modelStationService.selectByModel(id);
		JSONArray m = getModelStationData("0", t);
		map.put("id", model.getId());
		map.put("name", model.getName());
		map.put("stcd", model.getStcd());
		map.put("data", m.toString());
		return "model/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	@Transactional
	public JSONObject update(@RequestParam("name") String name, @RequestParam("stcd") String stcd, @RequestParam("data") String data, @PathVariable("id") Integer id) {
		JSONObject retval = new JSONObject();

		Model model = new Model();
		model.setId(id);
		model.setName(name);
		model.setStcd(stcd);
		modelService.update(model);

//		JSONArray temp = JSONArray.parseArray(data);
		modelStationService.deleteByModel(id);
		List<ModelStation> modelStationList = setModelStationData(model.getId(), "0", JSONArray.parseArray(data));
		if( modelStationList.size() > 0 ) {
			modelStationService.insertBatch(modelStationList);
		}
		return retval;
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		JSONObject retval = new JSONObject();
		modelService.delete(id);
		modelStationService.deleteByModel(id);
		return retval;
	}

	private JSONArray getModelStationData(String fatherId, List<Map> modelStationList){
		JSONArray retval = new JSONArray();
		for (int i = 0; i < modelStationList.size(); i++){
			Map modelStation = modelStationList.get(i);
			String m = (String)modelStation.get("FA_STCD");
			if( fatherId.trim().equals(String.valueOf(modelStation.get("FA_STCD")).trim()) ){
				JSONObject station = new JSONObject();
				station.put("title", String.valueOf(modelStation.get("STNAME")).trim());
				station.put("stcd", String.valueOf(modelStation.get("STCD")).trim());
				station.put("stname", String.valueOf(modelStation.get("STNAME")).trim());
				station.put("sttype", String.valueOf(modelStation.get("STTYPE")).trim());
				station.put("modelClId", modelStation.get("MODEL_CL"));
				station.put("modelClName", ModelTypeEnum.getText((Integer)modelStation.get("MODEL_CL")));
				station.put("planClId", modelStation.get("PLAN_CL_ID"));
				station.put("planClName", modelStation.get("PLAN_CL_NAME"));
				station.put("modelHlId", modelStation.get("MODEL_HL"));
				station.put("modelHlName", ModelTypeEnum.getText((Integer)modelStation.get("MODEL_HL")));
				station.put("planHlId", modelStation.get("PLAN_HL_ID"));
				station.put("planHlName", modelStation.get("PLAN_HL_NAME"));
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
			modelStation.setKe(station.getBigDecimal("ke"));
			modelStation.setXe(station.getBigDecimal("xe"));
			modelStation.setIntv(station.getBigDecimal("intv"));
			modelStation.setModelCl(station.getInteger("modelClId"));
			modelStation.setPlanCl(station.getInteger("planClId"));
			modelStation.setModelHl(station.getInteger("modelHlId"));
			modelStation.setPlanHl(station.getInteger("planHlId"));
			modelStation.setFaStcd(fatherId);
			retval.add(modelStation);

			JSONArray children = station.getJSONArray("children");
			if( children != null && children.size() > 0 ){
				retval.addAll(setModelStationData(modelId, station.getString("stcd"), children));
			}
		}
		return retval;
	}

	@PostMapping("getModel")
	@ResponseBody
	public List getModel(String stcd) {
		return modelService.selectListByStcd(stcd);
	}

	@PostMapping("getArea")
	@ResponseBody
	public String getArea(@RequestParam("modelId") Integer modelId) {
		JSONArray modelStationList = getModelStationData("0", modelStationService.selectByModel(modelId));
		setModelStationPlan(modelStationList);
		return modelStationList.toString();
	}

	private void setModelStationPlan(JSONArray modelStationList){
		for (int i = 0; i < modelStationList.size(); i++){
			JSONObject modelStation = (JSONObject) modelStationList.get(i);
			if( modelStation.containsKey("children") && modelStation.getJSONArray("children").size() > 0 ) {
				setModelStationPlan(modelStation.getJSONArray("children"));
			}
			modelStation.put("planCl", setPlan(planService.select(modelStation.getInteger("planClId"))));
			modelStation.put("planHl", setPlan(planService.select(modelStation.getInteger("planHlId"))));
//			Plan plan = planService.selectById(modelStation.getInteger("planId"));
//			Map m = new HashMap();
//			m.put("name", plan.getName());
//			m.put("sm", plan.getSM());
//			m.put("ci", plan.getCI());
//			m.put("cs", plan.getCS());
//			m.put("l", plan.getL());
//			m.put("wu0", plan.getWU0());
//			m.put("wl0", plan.getWL0());
//			m.put("wd0", plan.getWD0());
//			m.put("s0", plan.getS0());
//			m.put("fr0", plan.getFR0());
//			m.put("qrs0", plan.getQRS0());
//			m.put("qrss0", plan.getQRSS0());
//			m.put("qrg0", plan.getQRG0());
//			modelStation.put("plan", m);
		}
	}

	private Map setPlan(Plan plan) {
		Map m = new HashMap();
 		if( ModelTypeEnum.XAJ.getId().equals(plan.getModel()) ){
			m.put("ID", plan.getId());
			m.put("STCD", plan.getStcd());
			m.put("NAME", plan.getName());
			m.put("MODEL", plan.getModel());
			m.put("WU0", plan.getWU0());
			m.put("WL0", plan.getWL0());
			m.put("WD0", plan.getWD0());
			m.put("WUM", plan.getWUM());
			m.put("WLM", plan.getWLM());
			m.put("WDM", plan.getWDM());
			m.put("B", plan.getB());
			m.put("K", plan.getK());
			m.put("C", plan.getC());
			m.put("SM", plan.getSM());
			m.put("EX", plan.getEX());
			m.put("KSS", plan.getKSS());
			m.put("KG", plan.getKG());
			m.put("IM", plan.getIM());
			m.put("CI", plan.getCI());
			m.put("CG", plan.getCG());
			m.put("CS", plan.getCS());
			m.put("L", plan.getL());
			m.put("T", plan.getT());
			m.put("F", plan.getF());
			m.put("S0", plan.getS0());
			m.put("FR0", plan.getFR0());
			m.put("QRS0", plan.getQRS0());
			m.put("QRSS0", plan.getQRSS0());
			m.put("QRG0", plan.getQRG0());
		}else if( ModelTypeEnum.EXP.getId().equals(plan.getModel()) ){
			m.put("ID", plan.getId());
			m.put("STCD", plan.getStcd());
			m.put("NAME", plan.getName());
			m.put("MODEL", plan.getModel());
			m.put("PA", plan.getPA());
		}else if( ModelTypeEnum.API.getId().equals(plan.getModel()) ){
			m.put("ID", plan.getId());
			m.put("STCD", plan.getStcd());
			m.put("NAME", plan.getName());
			m.put("MODEL", plan.getModel());
			m.put("PA", plan.getPA());
			m.put("KR", plan.getKR());
			m.put("IM", plan.getIM());
			m.put("IMM", plan.getIMM());
			m.put("NA", plan.getNA());
			m.put("NU", plan.getNU());
			m.put("KG", plan.getKG());
			m.put("KU", plan.getKU());
			m.put("AREA", plan.getAREA());
		}
		return m;
	}
}
