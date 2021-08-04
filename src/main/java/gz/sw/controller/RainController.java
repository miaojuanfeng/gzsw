package gz.sw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.entity.write.Rain;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("rain")
public class RainController {

	@Autowired
	private RainService rainService;

	@Autowired
	private StationService stationService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "rain/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(String sttp, String stcd, String name, Integer page, Integer limit) {
		return RetVal.OK(rainService.selectCount(sttp, stcd, name), rainService.selectList(page, limit, sttp, stcd, name));
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("area", stationService.selectRain());
		return "rain/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	@Transactional
	public JSONObject insert(String name, String stcd, String rainStation) {
		Rain rain = new Rain();
		rain.setName(name);
		rain.setStcd(stcd);
		rain.setCreateTime(new Date());
		rainService.insert(rain);

		JSONArray area = JSONArray.parseArray(rainStation);
		List<Map> areaStationList = new ArrayList<>();
		for (int i=0; i<area.size(); i++){
			JSONObject temp = area.getJSONObject(i);
			Map station = new HashMap();
			station.put("rain", rain.getId());
			station.put("stcd", temp.getString("value"));
			areaStationList.add(station);
		}
		if (areaStationList.size() > 0){
			rainService.insertPointList(areaStationList);
		}
		return RetVal.OK();
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		map.put("sttps", StationTypeEnum.getList());
		Map rainArea = rainService.selectMap(id);
		List<Map> rainAreaPoint = rainService.selectRainPoint(id);
		String stcds = null;
		for(Map point : rainAreaPoint){
			if( stcds == null){
				stcds = "'" + (String)point.get("stcd") + "'";
			}else{
				stcds += ",'" + (String)point.get("stcd") + "'";
			}
		}
		map.put("stcds", stcds);
		map.put("stations", stationService.selectListByType(String.valueOf(rainArea.get("sttype"))));
		map.put("rainArea", rainArea);
		return "rain/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	@Transactional
	public JSONObject update(@PathVariable("id") Integer id, String name, String stcd, String rainStation) {
		Rain rain = new Rain();
		rain.setId(id);
		rain.setStcd(stcd);
		rain.setName(name);
		rainService.update(rain);

		rainService.deleteListById(id);
		JSONArray area = JSONArray.parseArray(rainStation);
		List<Map> areaStationList = new ArrayList<>();
		for (int i=0; i<area.size(); i++){
			JSONObject temp = area.getJSONObject(i);
			Map station = new HashMap();
			station.put("rain", rain.getId());
			station.put("stcd", temp.getString("value"));
			areaStationList.add(station);
		}
		if (areaStationList.size() > 0){
			rainService.insertPointList(areaStationList);
		}
		return RetVal.OK();
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		rainService.deleteListById(id);
		rainService.delete(id);
		return RetVal.OK();
	}

	@PostMapping("station")
	@ResponseBody
	public JSONObject station() {
		return RetVal.OK(stationService.selectRain());
	}

	@PostMapping("getRain")
	@ResponseBody
	public JSONObject getRain(String stcd) {
		return RetVal.OK(rainService.selectListByStcd(stcd));
	}
}
