package gz.sw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.entity.write.Rain;
import gz.sw.entity.write.Rainf;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.read2.Read2Service;
import gz.sw.service.write.RainService;
import gz.sw.service.write.RainfService;
import gz.sw.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("rainf")
public class RainfController {

	@Autowired
	private RainfService rainfService;

	@Autowired
	private Read2Service read2Service;

	@Autowired
	private StationService stationService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "rainf/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(String sttp, String stcd, String name, Integer page, Integer limit) {
		return RetVal.OK(rainfService.selectCount(sttp, stcd, name), rainfService.selectList(page, limit, sttp, stcd, name));
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("area", stationService.selectRain());
		return "rainf/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	@Transactional
	public JSONObject insert(String name, String stcd, String rainStation) {
		Rainf rainf = new Rainf();
		rainf.setName(name);
		rainf.setStcd(stcd);
		rainf.setCreateTime(new Date());
		rainfService.insert(rainf);

		JSONArray area = JSONArray.parseArray(rainStation);
		List<Map> areaStationList = new ArrayList<>();
		for (int i=0; i<area.size(); i++){
			JSONObject temp = area.getJSONObject(i);
			Map station = new HashMap();
			station.put("rainf", rainf.getId());
			station.put("stid", temp.getString("value"));
			areaStationList.add(station);
		}
		if (areaStationList.size() > 0){
			rainfService.insertPointList(areaStationList);
		}
		return RetVal.OK();
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		map.put("sttps", StationTypeEnum.getList());
		Map rainArea = rainfService.selectMap(id);
		List<Map> rainAreaPoint = rainfService.selectRainfPoint(id);
		String stids = null;
		for(Map point : rainAreaPoint){
			if( stids == null){
				stids = "'" + (Integer)point.get("stid") + "'";
			}else{
				stids += ",'" + (Integer)point.get("stid") + "'";
			}
		}
		map.put("stids", stids);
		map.put("stations", stationService.selectListByType(String.valueOf(rainArea.get("sttype"))));
		map.put("rainArea", rainArea);
		return "rainf/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	@Transactional
	public JSONObject update(@PathVariable("id") Integer id, String name, String stcd, String rainStation) {
		Rainf rainf = new Rainf();
		rainf.setId(id);
		rainf.setStcd(stcd);
		rainf.setName(name);
		rainfService.update(rainf);

		rainfService.deleteListById(id);
		JSONArray area = JSONArray.parseArray(rainStation);
		List<Map> areaStationList = new ArrayList<>();
		for (int i=0; i<area.size(); i++){
			JSONObject temp = area.getJSONObject(i);
			Map station = new HashMap();
			station.put("rainf", rainf.getId());
			station.put("stid", temp.getString("value"));
			areaStationList.add(station);
		}
		if (areaStationList.size() > 0){
			rainfService.insertPointList(areaStationList);
		}
		return RetVal.OK();
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		rainfService.deleteListById(id);
		rainfService.delete(id);
		return RetVal.OK();
	}

	@PostMapping("station")
	@ResponseBody
	public JSONObject station() {
		return RetVal.OK(read2Service.selectGridStation());
	}

	@PostMapping("getRain")
	@ResponseBody
	public JSONObject getRain(String stcd) {
		return RetVal.OK(rainfService.selectListByStcd(stcd));
	}
}
