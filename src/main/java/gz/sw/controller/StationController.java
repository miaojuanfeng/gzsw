package gz.sw.controller;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.constant.CommonConst;
import gz.sw.constant.NumberConst;
import gz.sw.entity.read.Rainfall;
import gz.sw.entity.read.River;
import gz.sw.entity.write.Station;
import gz.sw.enums.StationTypeEnum;
import gz.sw.service.read.RainfallService;
import gz.sw.service.read.RiverService;
import gz.sw.service.read.ReadService;
import gz.sw.service.write.StationService;
import gz.sw.util.DateUtil;
import gz.sw.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("station")
public class StationController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private ReadService readService;

	@Autowired
	private RainfallService rainfallService;

    @Autowired
    private RiverService riverService;

	@GetMapping("list")
	public String list(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		return "station/list";
	}

	@PostMapping("list")
	@ResponseBody
	public JSONObject list(Integer page, Integer limit, String sttp, String stcd, String name) {
		return RetVal.OK(stationService.selectCount(sttp, stcd, name), stationService.selectList(page, limit, sttp, stcd, name));
	}

	@PostMapping("getStation")
	@ResponseBody
	public JSONObject getStation(String sttp) {
		return RetVal.OK(stationService.selectListByType(sttp));
	}

	@GetMapping("unusual")
	public String unusual(ModelMap map) {
		map.put("sttps", StationTypeEnum.getList());
		map.put("date", DateUtil.date2str(new Date(), "yyyy-MM-dd HH:00:00"));
		return "station/unusual";
	}

	@PostMapping("unusual")
	@ResponseBody
	public JSONObject unusual(Integer page, Integer limit, String selfP, String diffP, String stcd) {
		return RetVal.OK(stationService.selectRainCount(selfP, diffP, stcd), stationService.selectRainList(page, limit, selfP, diffP, stcd));
	}

	@PostMapping("refresh")
	@ResponseBody
	@Transactional
	public JSONObject refresh() {
		synchronized (CommonConst.stationLock) {
			List<Map> stbprpList = readService.selectStbprpList();
			if (stbprpList.size() > 0) {
				List<Station> stationList = new ArrayList<>();
				stationService.clear();
				stationService.dbcc();
				for (Map stbprp : stbprpList) {
					if( String.valueOf(stbprp.get("STCD")).equals("55555555") ){
						continue;
					}
					Station station = new Station();
					station.setStcd(String.valueOf(stbprp.get("STCD")));
					station.setStname(String.valueOf(stbprp.get("STNM")));
					station.setType(String.valueOf(stbprp.get("STTP")));
					if (stbprp.get("LGTD") != null) {
						station.setLgtd(new BigDecimal(String.valueOf(stbprp.get("LGTD"))));
					}
					if (stbprp.get("LTTD") != null) {
						station.setLttd(new BigDecimal(String.valueOf(stbprp.get("LTTD"))));
					}
					stationList.add(station);
					if (stationList.size() > 100) {
						stationService.insertBatch(stationList);
						stationList.clear();
					}
				}
				if (stationList.size() > 0) {
					stationService.insertBatch(stationList);
					stationList.clear();
				}
			}
			return RetVal.OK();
		}
	}

	@PostMapping("adsorb")
	@ResponseBody
	@Transactional
	public JSONObject adsorb() {
		synchronized (CommonConst.stationLock) {
			List<Station> stations = stationService.selectAll();
			if (stations.size() > 0) {
				stationService.clear();
				stationService.dbcc();
				for (Station s1 : stations) {
					BigDecimal lgtd1 = s1.getLgtd();
					BigDecimal lttd1 = s1.getLttd();
					if (lgtd1 == null || lttd1 == null ||
							NumberUtil.le(lgtd1, NumberConst.ZERO) ||
							NumberUtil.le(lttd1, NumberConst.ZERO)) {
						continue;
					}
					for (Station s2 : stations) {
						BigDecimal lgtd2 = s2.getLgtd();
						BigDecimal lttd2 = s2.getLttd();
						if (s1.getStcd().equals(s2.getStcd()) ||
								lgtd2 == null || lttd2 == null ||
								NumberUtil.le(lgtd2, NumberConst.ZERO) ||
								NumberUtil.le(lttd2, NumberConst.ZERO)) {
							continue;
						}
						BigDecimal dis = getDistance(lgtd1.doubleValue(), lttd1.doubleValue(), lgtd2.doubleValue(), lttd2.doubleValue());
						if (s1.getDis() == null || NumberUtil.gt(s1.getDis(), dis)) {
							s1.setDis(dis);
							s1.setNearStcd(s2.getStcd());
						}
						if (s2.getDis() == null || NumberUtil.gt(s2.getDis(), dis)) {
							s2.setDis(dis);
							s2.setNearStcd(s1.getStcd());
						}
					}
				}
				List<Station> stationList = new ArrayList<>();
				for (Station s : stations) {
					stationList.add(s);
					if (stationList.size() > 100) {
						stationService.insertBatch(stationList);
						stationList.clear();
					}
				}
				if (stationList.size() > 0) {
					stationService.insertBatch(stationList);
					stationList.clear();
				}
			}
			return RetVal.OK();
		}
	}

	@PostMapping("reload")
	@ResponseBody
	@Transactional
	public JSONObject reload(String date) {
		if( date != null ){
			Date d = DateUtil.str2date(date, "yyyy-MM-dd HH:mm:ss");
			stationService.unusual(DateUtil.date2str(d, "yyyy-MM-dd HH:00:00"));
		}else{
			stationService.unusual();
		}
		return RetVal.OK();
	}

	private BigDecimal getDistance(double lon1, double lat1, double lon2, double lat2) {
		double EARTH_RADIUS = 6378137;
		double radLat1 = lat1 * Math.PI / 180.0;
		double radLat2 = lat2 * Math.PI / 180.0;
		double a = radLat1 - radLat2;
		double b = (lon1 * Math.PI / 180.0) - ( lon2 * Math.PI / 180.0 );
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b/2), 2)));
		s = s * EARTH_RADIUS / 1000;
		return new BigDecimal(s).setScale(3, NumberConst.MODE);
	}

	@PostMapping("chart1")
	@ResponseBody
    public JSONObject chart1(String stcd, String startTime, String endTime) {
		startTime = DateUtil.date2str(DateUtil.str2date(startTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
		endTime = DateUtil.date2str(DateUtil.str2date(endTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");

		List<String> stcdList = new ArrayList<>();
        stcdList.add(stcd);
        List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdList, endTime, startTime);

//        retval.put("hour", new SimpleDateFormat("H时").format(new Date()));
//
//        List<Rainfall> rainfallTotal = cacheRainfallTotalService.selectByStcd(stcd);
//
        List<String> timeList = new ArrayList<>();
        List<BigDecimal> rainfallList = new ArrayList<>();
        Integer max = Integer.MIN_VALUE;
        for (int i=0;i<rainfalls.size();i++){
            timeList.add(rainfalls.get(i).getDate());
            rainfallList.add(rainfalls.get(i).getRainfall());
            if( max < rainfalls.get(i).getRainfall().intValue() ){
                max = rainfalls.get(i).getRainfall().intValue() + 10;
            }
        }
		JSONObject data = new JSONObject();
		data.put("timeList", timeList);
		data.put("rainfallList", rainfallList);
		data.put("max", max);

        return RetVal.OK(data);
    }

    @PostMapping("chart2")
    @ResponseBody
    public JSONObject chart2(String stcd, String startTime, String endTime) {
		startTime = DateUtil.date2str(DateUtil.str2date(startTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
		endTime = DateUtil.date2str(DateUtil.str2date(endTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");

		List<River> rivers = riverService.selectRiverTime(stcd, startTime, endTime);

		List<String> timeList = new ArrayList<>();
		List<BigDecimal> riverList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
		BigDecimal max = NumberConst.ZERO;
		BigDecimal min = NumberConst.ZERO;
		for (int i = 0; i < rivers.size(); i++) {
			String time = format.format(rivers.get(i).getTm());
            timeList.add(time);
			BigDecimal z = rivers.get(i).getZ();
            riverList.add(z);
			if (NumberUtil.lt(max, z)) {
				max = z;
			}
			if (NumberUtil.gt(min, z) || NumberUtil.et(min, NumberConst.ZERO)) {
				min = z;
			}
		}

		JSONObject data = new JSONObject();
		data.put("timeList", timeList);
		data.put("riverList", riverList);
		data.put("max", max.add(NumberConst.ONE).intValue());
		data.put("min", min.intValue() - 1);

		return RetVal.OK(data);
	}
}
