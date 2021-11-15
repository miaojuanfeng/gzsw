package gz.sw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gz.sw.calc.ApiCalc;
import gz.sw.calc.ComCalc;
import gz.sw.calc.ExpCalc;
import gz.sw.calc.XajCalc;
import gz.sw.common.Forecast;
import gz.sw.common.RetVal;
import gz.sw.common.SessionUser;
import gz.sw.common.XajParam;
import gz.sw.constant.CommonConst;
import gz.sw.constant.NumberConst;
import gz.sw.entity.read.Rainfall;
import gz.sw.entity.read.River;
import gz.sw.entity.read3.Zq;
import gz.sw.enums.ModelTypeEnum;
import gz.sw.enums.StationTypeEnum;
import gz.sw.exception.ParamException;
import gz.sw.service.read.RainfallService;
import gz.sw.service.read.ReadService;
import gz.sw.service.read3.Read3Service;
import gz.sw.service.read.RiverService;
import gz.sw.service.read3.ZqService;
import gz.sw.service.read2.Read2Service;
import gz.sw.service.write.*;
import gz.sw.util.DateUtil;
import gz.sw.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("forecast")
//@Slf4j
public class ForecastController {

	@Autowired
	private RainfallService rainfallService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private RiverService riverService;

	@Autowired
	private RainRunService rainRunService;

	@Autowired
	private UnitLineService unitLineService;

	@Autowired
	private DischargeService dischargeService;

	@Autowired
	private ZqService zqService;

	@Autowired
	private RainService rainService;

    @Autowired
    private RainfService rainfService;

	@Autowired
	private ReadService readService;

	@Autowired
	private Read2Service read2Service;

	@Autowired
	private Read3Service read3Service;

	@GetMapping("home")
	public String home(ModelMap map) {
		map.put("date", DateUtil.getDate());
		map.put("models", modelService.selectAll());
		Date date = new Date();
		map.put("forecastTime", DateUtil.date2str(date, "yyyy-MM-dd HH:00:00"));
		map.put("affectTime", DateUtil.date2str(DateUtil.addMonth(date, -1), "yyyy-MM-dd HH:00:00"));
//		map.put("forecastTime", "2021-05-20 08:00:00");
//		map.put("affectTime", "2021-05-15 08:00:00");
//		map.put("forecastTime", "2020-03-11 08:00:00");
//		map.put("affectTime", "2020-02-11 08:00:00");

		return "home/forecast";
	}

	private JSONObject getRetval(HttpServletRequest request, String stcd, JSONArray model){
        JSONObject data = new JSONObject();
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Forecast forecast = sessionUser.getForecast();
		data.put("timeArr", forecast.getListTime(stcd));
		data.put("P", forecast.getListP(stcd));
		data.put("R", forecast.getListR(stcd));
		data.put("QTRR", forecast.getListQTRR(stcd));
		data.put("River", forecast.getListRiver(stcd));
		data.put("rainfallMax", forecast.getListMaxP(stcd).intValue());
		data.put("forecastText", forecast.getForecastText(stcd));
		data.put("forecastUnit", forecast.getForecastUnit(stcd));
		data.put("forecastColor", forecast.getForecastColor(stcd));
		data.put("riverMax", forecast.getRiverMax(stcd).intValue());
		data.put("riverMin", forecast.getRiverMin(stcd).intValue());
		data.put("QT", forecast.getListQT(stcd));
		data.put("stname", forecast.getStname(stcd));
		data.put("stcd", stcd);
		data.put("sttp", forecast.getSttp(stcd));
		if( model != null ){
			data.put("model", model);
		}
		if( forecast.getListChildStcd(stcd) != null && forecast.getListChildStcd(stcd).size() > 0 ){
			data.put("hasChild", true);
		}else{
			data.put("hasChild", false);
		}
		if( StationTypeEnum.getCode(StationTypeEnum.RR.getId()).equals(forecast.getSttp(stcd)) ){
			data.put("INQ", forecast.getListINQ(stcd));
			data.put("Z", forecast.getListZ(stcd));
			data.put("OQ", forecast.getListOQ(stcd));
			data.put("yMax", forecast.getYMax(stcd).intValue());
			data.put("yMin", forecast.getYMin(stcd).intValue());
		}

        return RetVal.OK(data);
    }

    /**
     * 单站结果
     * @return
     */
    @PostMapping("station")
    @ResponseBody
    public JSONObject station(
    		HttpServletRequest request,
            @RequestParam("stcd") String stcd
    ) {
        return getRetval(request, stcd, null);
    }

	/**
	 * 调洪调度
	 * @return
	 */
	@PostMapping("oq")
	@ResponseBody
	public JSONObject oq(
			HttpServletRequest request,
			@RequestParam("stcd") String stcd,
			@RequestParam("forecastTime") String forecastTime
	) {
		JSONObject data = new JSONObject();
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Forecast forecast = sessionUser.getForecast();
		List<BigDecimal> oqList = forecast.getListOQ(stcd);

		data.put("stcd", stcd);
		data.put("stname", forecast.getStname(stcd));
		List list = new ArrayList();
		int id = 0;
		for (int i=0; i<forecast.getListTime(stcd).size(); i++) {
			if( oqList.size() == i ){
				break;
			}
			if( DateUtil.str2date(forecast.getListTime(stcd).get(i), "yyyy-MM-dd HH:mm").after(DateUtil.str2date(forecastTime, "yyyy-MM-dd HH:mm")) ) {
				Map t = new HashMap();
				t.put("id", ++id);
				t.put("index", i);
				t.put("oq", oqList.get(i));
				t.put("timeArr", forecast.getListTime(stcd).get(i));
				list.add(t);
			}
		}
		data.put("data", list);

		return RetVal.OK(data);
	}

	/**
	 * 输入贡献
	 * @return
	 */
	@PostMapping("devote")
	@ResponseBody
	public JSONObject devote(
			HttpServletRequest request,
			@RequestParam("stcd") String stcd
	) {
		JSONObject data = new JSONObject();
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Forecast forecast = sessionUser.getForecast();
		JSONArray listQT = new JSONArray();
		if( forecast.getListChildStcd(stcd) != null ) {
			for (int i = 0; i < forecast.getListChildStcd(stcd).size(); i++) {
				String childStcd = forecast.getListChildStcd(stcd).get(i);
				JSONObject temp = new JSONObject();
				temp.put("stname", forecast.getStname(childStcd));
				temp.put("QT", forecast.getListQT(childStcd));
				listQT.add(temp);
			}
			data.put("listQT", listQT);
			data.put("QTRR", forecast.getListQTRR(stcd));
			data.put("selfQTRR", forecast.getListSelfQTRR(stcd));
			data.put("timeArr", forecast.getListTime(stcd));
			data.put("P", forecast.getListP(stcd));
			data.put("rainfallMax", forecast.getListMaxP(stcd).intValue());
			data.put("stname", forecast.getStname(stcd));
			return RetVal.OK(data);
		}else{
			return RetVal.Error("当前站无输入贡献");
		}
	}

	/**
	 * 预报结果
	 * @return
	 */
	@PostMapping("compute")
	@ResponseBody
	public JSONObject compute(
			HttpServletRequest request,
			@RequestParam("type") Integer type,
			@RequestParam("stcd") String stcd,
			@RequestParam("forecastTime") String forecastTime,
			@RequestParam("affectTime") String affectTime,
			@RequestParam("day") Integer day,
			@RequestParam("unit") Integer unit,
			@RequestParam("data") String data,
			String oqStcd,
			String oqStr,
			String unitArr
	) {
		Long t = System.currentTimeMillis();
		JSONArray model = JSONArray.parseArray(data);
		JSONArray uArr = JSONArray.parseArray(unitArr);
		try {
			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
			Boolean updateOQ = false;
			if( oqStr == null ) {
				sessionUser.clearForecast();
			}else{
				Forecast forecast = sessionUser.getForecast();
				JSONObject oqObj = JSONObject.parseObject(oqStr);

				JSONArray oqList = oqObj.getJSONArray("data");
				List oq = forecast.getListOQ(oqObj.getString("stcd"));
				int index = 0;
				for (int i=0; i<oqList.size(); i++){
					JSONObject temp = oqList.getJSONObject(i);
					index = temp.getInteger("index");
					oq.set(index, temp.getBigDecimal("oq"));
				}
				if( index+1 < oq.size() ){
					for(int i=index+1; i<oq.size(); i++){
						oq.set(i, oq.get(index));
					}
				}

				updateOQ = true;
			}
			forecastTime = DateUtil.date2str(DateUtil.str2date(forecastTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
			affectTime = DateUtil.date2str(DateUtil.str2date(affectTime, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
			modelStation(request, stcd, null, forecastTime, affectTime, day, unit, type, model, updateOQ, uArr);
		} catch (ParamException e) {
			return RetVal.Error(e.getMessage());
		}
		System.out.println("--------------------河系计算时间: " + (System.currentTimeMillis() - t)/1000 + "秒");
		return getRetval(request, oqStcd != null ? oqStcd : stcd, model);
	}

	private Map getP(Integer rain, Integer rainf, String forecastTime, String affectTime, Integer day, Integer unit, JSONArray unitArr) throws ParamException {
		Map retval = new HashMap<>();
		/**
		 * 获取子站id
		 */
		List<String> stcdIds = new ArrayList<>();
		List<Map> rainPointList = rainService.selectRainPoint(rain);
		for (int i = 0; i < rainPointList.size(); i++) {
			stcdIds.add(String.valueOf(rainPointList.get(i).get("stcd")));
		}
		if (stcdIds.isEmpty()){
			throw new ParamException("实测雨量方案(ID=" + rain + ")雨量站点为空");
		}
		/**
		 * 实测雨量
		 */
		Map<String, BigDecimal> forecastMap = new TreeMap<>();
		String startDay = forecastTime;
		String endDay = DateUtil.date2str(DateUtil.addDay(DateUtil.str2date(forecastTime, CommonConst.DATETIME_FORMAT), day), CommonConst.DATETIME_FORMAT);
		if( CommonConst.FUTURE_RAINFALL_MEASURE.equals(unit) ) {
			List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdIds, endDay, startDay);
			for (Rainfall rainfall : rainfalls) {
				forecastMap.put(rainfall.getDate().substring(0, rainfall.getDate().length() - 2), rainfall.getRainfall());
			}
		/**
		 * 手动输入
		 */
		}else if( CommonConst.FUTURE_RAINFALL_MANUAL.equals(unit) ){
			for (int i=0; i<unitArr.size(); i++) {
				JSONObject unitObj = unitArr.getJSONObject(i);
				forecastMap.put(unitObj.getString("h").substring(0, unitObj.getString("h").length() - 2), new BigDecimal(unitObj.getString("p")));
			}
		/**
         * 欧洲台或日本台
         */
		}else{
			List<Map> gridList = rainfService.selectRainfPoint(rainf);
			List<Integer> gridId = new ArrayList<>();
			for (Map grid : gridList){
				gridId.add(Integer.valueOf(String.valueOf(grid.get("stid"))));
			}
			if (gridId.isEmpty()){
				throw new ParamException("未来雨量方案(ID=" + rainf + ")雨量站点为空");
			}
			String fymdh = "";
			Date date = DateUtil.str2date(forecastTime, CommonConst.DATETIME_FORMAT);
			Date startDate = DateUtil.str2date(forecastTime.substring(0, 10) + " 00:00:00", CommonConst.DATETIME_FORMAT);
            Date endDate   = DateUtil.str2date(forecastTime.substring(0, 10) + " 08:00:00", CommonConst.DATETIME_FORMAT);
			if( !date.before(startDate) && !date.after(endDate) ){
			    fymdh = DateUtil.date2str(DateUtil.addDay(date, -2)) + " 20:00:00";
            }else{
                fymdh = DateUtil.date2str(DateUtil.addDay(date, -1)) + " 20:00:00";
            }
			List<Map> forecastList = read2Service.selectGridPoint(gridId, fymdh, unit, startDay, endDay);

			if( forecastList.size() > 0 ) {
				for (Map forecast : forecastList) {
					forecastMap.put(String.valueOf(forecast.get("ymdh")).substring(0, 19), new BigDecimal(String.valueOf(forecast.get("rn"))));
				}
			}

				Date sDay = DateUtil.str2date(startDay, CommonConst.DATETIME_FORMAT);
				Date eDay = DateUtil.str2date(endDay, CommonConst.DATETIME_FORMAT);
				Date iDay = eDay;
				BigDecimal n = forecastList.size() > 0 ? new BigDecimal(String.valueOf(forecastList.get(forecastList.size()-1).get("rn"))).divide(new BigDecimal(3),2, NumberConst.MODE) : NumberConst.ZERO;
				//                    Date d = forecastList.get(forecastList.size()-1).getYmdh();
				Integer c = 0;
				while (iDay.after(sDay)) {
					String key = DateUtil.date2str(iDay, CommonConst.DATETIME_FORMAT);
					if( forecastMap.containsKey(key) ){
						n = forecastMap.get(key).divide(new BigDecimal(3),2, NumberConst.MODE);
						c = 0;
					}else{
						c++;
						if( c >= 3 ){
							n = NumberConst.ZERO;
						}
					}
					if( forecastList.isEmpty() || iDay.after(DateUtil.str2date(String.valueOf(forecastList.get(forecastList.size()-1).get("ymdh")), CommonConst.DATETIME_FORMAT)) ){
						n = NumberConst.ZERO;
					}
					forecastMap.put(key, n);
					iDay = DateUtil.addHour(iDay, -1);
				}
		}
		//            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, plusDay(day, forecastTime), affectTime);
		List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdIds, forecastTime, affectTime);

		// 拼接数据
		for(String key : forecastMap.keySet()){
			Rainfall r = new Rainfall();
			r.setDate(key);
			r.setRainfall(forecastMap.get(key));
			rainfalls.add(r);
		}

		List<BigDecimal> listRainfall = new ArrayList<>();
		List<String> listTime = new ArrayList<>();
		BigDecimal rainfallMax = NumberConst.ZERO;
		for (Rainfall rainfall : rainfalls) {
			BigDecimal r = rainfall.getRainfall().setScale(2, NumberConst.MODE);
			String t = rainfall.getDate().substring(0, 16);
			listRainfall.add(r);
			listTime.add(t);
			if( NumberUtil.gt(r, rainfallMax) ){
				rainfallMax = r;
			}
		}
		/**
		 * 测试代码开始
		 */
//		listRainfall.clear();
//		listRainfall = PUtil.getP3();
        /**
         * 测试代码结束
         */
        Date d = DateUtil.str2date(affectTime, CommonConst.DATETIME_FORMAT);
        Date c = DateUtil.str2date(affectTime.substring(0, 10) + " 08:00:00", CommonConst.DATETIME_FORMAT);
		d = d.before(c) ? DateUtil.addDay(c, -1) : c;
        Map map = read2Service.selectInit(stcdIds, DateUtil.date2str(d, CommonConst.DATETIME_FORMAT));

        retval.put("init", map);
		retval.put("rainfallMax", rainfallMax.multiply(new BigDecimal("3.5")).intValue());
		retval.put("timeList", listTime);
		retval.put("rainfallList", listRainfall);

		return retval;
	}

	private List<BigDecimal> modelStation(HttpServletRequest request, String rootStcd, String fatherStcd, String forecastTime, String affectTime, Integer day, Integer unit, Integer type, JSONArray model, Boolean updateOQ, JSONArray unitArr) throws ParamException {
		List<BigDecimal> retval = new ArrayList<>();
	    List<List<BigDecimal>> result = new ArrayList<>();
	    for( int i = 0; i < model.size(); i++ ){
            List<BigDecimal> childList = new ArrayList<>();
            JSONObject m = model.getJSONObject(i);
            String stcd = m.getString("stcd");
            String stname = m.getString("stname");
			String sttp = m.getString("sttp");
			/**
			 * 子站输出数据
			 */
			if( m.containsKey("children") && m.getJSONArray("children").size() > 0 ) {
                childList = modelStation(request, rootStcd, stcd, forecastTime, affectTime, day, unit, type, m.getJSONArray("children"), updateOQ, unitArr);
			}
			/**
			 * 子站数据和本站数据做运算
			 */
			List<BigDecimal> listR = new ArrayList<>();
			List<BigDecimal> listP = new ArrayList<>();
			List<String> listTime = new ArrayList<>();
			List<BigDecimal> listQTR = new ArrayList<>();
			List<BigDecimal> listQTRR = new ArrayList<>();
			Integer rainfallMax = 0;

			SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
			sessionUser.getForecast().setStname(stcd, stname);
			sessionUser.getForecast().setSttp(stcd, sttp);

			JSONObject plan = m.getJSONObject("plan");

			System.out.println("计算站点: " + stname + "(" + stcd + ")");
			Long pTime = System.currentTimeMillis();
			Map rainfallMap = getP(plan.getInteger("RAIN"), plan.getInteger("RAINF"), forecastTime, affectTime, day, unit, unitArr);
			System.out.println("读取雨量时间: " + (System.currentTimeMillis() - pTime) + "毫秒");

			listP = (List<BigDecimal>)rainfallMap.get("rainfallList");
			listTime = (List<String>)rainfallMap.get("timeList");
			rainfallMax = (Integer)rainfallMap.get("rainfallMax");

			/////////
			/////////
			/////////
			/////////
			/////////
//			if( stcd.equals("62302700") ){
//				Map<String, BigDecimal> init = (Map<String, BigDecimal>)rainfallMap.get("init");
//				System.out.println("WU0: " + init.get("wu"));
//				System.out.println("WL0: " + init.get("wl"));
//				System.out.println("WD0: " + init.get("wd"));
//				for( BigDecimal p : listP ){
//					System.out.println(p);
//				}
//				for( String t : listTime ){
//					System.out.println(t);
//				}
//			}
			/////////
			/////////
			/////////
			/////////
			/////////

			if( !plan.getBoolean("update") ) {
				Map<String, BigDecimal> init = (Map<String, BigDecimal>)rainfallMap.get("init");
				plan.put("WU0", init.get("wu"));
				plan.put("WL0", init.get("wl"));
				plan.put("WD0", init.get("wd"));
			}

//			if( stcd.trim().equals("62303130") ) {
//				for (BigDecimal pm : listP) {
//					System.out.println(pm);
//				}
//				System.out.println("------------");
//				for (String tm : listTime) {
//					System.out.println(tm);
//				}
//			}

			sessionUser.getForecast().setListP(stcd, listP);
			sessionUser.getForecast().setListMaxP(stcd, rainfallMax);
			sessionUser.getForecast().setListTime(stcd, listTime);

			List<River> rivers = new ArrayList<>();
			List<BigDecimal> riverArr = new ArrayList<>();
			BigDecimal riverMax = NumberConst.ZERO;
			BigDecimal riverMin = NumberConst.ZERO;
			Long qTime = System.currentTimeMillis();
			if( type == 1 ) {
				rivers = riverService.selectRiverQRange(stcd, plusDay(day, forecastTime), affectTime);
				BigDecimal lastQ = NumberConst.ZERO;
				for (int j = 0; j < rivers.size(); j++) {
					River r = rivers.get(j);
                    BigDecimal q = lastQ;
                    if( r.getQ() != null ){
					    q = r.getQ().setScale(2, NumberConst.MODE);
					    lastQ = q;
                    }
					riverArr.add(q);
					if( NumberUtil.gt(q, riverMax) ){
						riverMax = q;
					}
					if( NumberUtil.lt(q, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
						riverMin = q;
					}
				}
				sessionUser.getForecast().setForecastText(stcd, "流量");
				sessionUser.getForecast().setForecastUnit(stcd, "流量(m³/s)");
				sessionUser.getForecast().setForecastColor(stcd, "#FF5722");
			}else{
				rivers = riverService.selectRiverZRange(stcd, plusDay(day, forecastTime), affectTime);
				BigDecimal lastZ = NumberConst.ZERO;
				for (int j = 0; j < rivers.size(); j++) {
					River r = rivers.get(j);
					BigDecimal z = lastZ;
					if( r.getZ() != null ){
						z = r.getZ().setScale(2, NumberConst.MODE);
						lastZ = z;
					}
					riverArr.add(z);
					if( NumberUtil.gt(z, riverMax) ){
						riverMax = z;
					}
					if( NumberUtil.lt(z, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
						riverMin = z;
					}
				}
				sessionUser.getForecast().setForecastText(stcd, "水位");
				sessionUser.getForecast().setForecastUnit(stcd, "水位(m)");
				sessionUser.getForecast().setForecastColor(stcd, "#009688");
			}
			System.out.println("读取水位时间: " + (System.currentTimeMillis() - qTime) + "毫秒");
			sessionUser.getForecast().setListRiver(stcd, riverArr);

            /**
             * 累加子站降雨量
             */
//            if( m.containsKey("children") && m.getJSONArray("children").size() > 0 ) {
//                for( int j = 0; j < m.getJSONArray("children").size(); j++ ){
//                    JSONObject c = m.getJSONArray("children").getJSONObject(j);
//                    String cstcd = m.getString("stcd");
//                    List<BigDecimal> childP = sessionUser.getForecast().getListP(cstcd);
//                    listP = addList(listP, childP);
//                }
//            }

            plan.put("KE", m.getBigDecimal("ke"));
			plan.put("XE", m.getBigDecimal("xe"));
			plan.put("INTV", m.getBigDecimal("intv"));


			Integer planModelCl = plan.getInteger("MODEL_CL");
			Integer planModelHl = plan.getInteger("MODEL_HL");

			XajParam xajParam = new XajParam();
			/**
			 * 产流
			 */
			Long clTime = System.currentTimeMillis();
			if( ModelTypeEnum.XAJ_CL.getId().equals(planModelCl) ) {
				if( ModelTypeEnum.XAJ_HL.getId().equals(planModelHl) ){
					listQTR = XajCalc.getR(plan, listP, xajParam, CommonConst.RETURN_TYPE_QTR);
				}else{
					listR = XajCalc.getR(plan, listP, xajParam, CommonConst.RETURN_TYPE_R);
				}
			}else if( ModelTypeEnum.EXP_CL.getId().equals(planModelCl) ){
				List<Map> listRainRun = rainRunService.selectRainRunPoint(plan.getInteger("RAINRUN"));
				if( listRainRun.isEmpty() ){
					throw new ParamException("降径关系线为空(RAINRUN: PA,R,D)");
				}
				List<BigDecimal> Pa0 = new ArrayList<>();
				List<List<BigDecimal>> R0 = new ArrayList<>();
				List<List<BigDecimal>> P0 = new ArrayList<>();
				for(Map rainRun : listRainRun){
					BigDecimal pa = new BigDecimal(String.valueOf(rainRun.get("PA")));
					BigDecimal p = new BigDecimal(String.valueOf(rainRun.get("R")));
					BigDecimal r = new BigDecimal(String.valueOf(rainRun.get("D")));
					Integer index = pa.intValue()/10;
					if( !Pa0.contains(pa) ){
						Pa0.add(pa);
					}
					if( R0.size() <= index ){
						R0.add(new ArrayList<>());
					}
					if( P0.size() <= index ){
						P0.add(new ArrayList<>());
					}
					R0.get(index).add(r);
					P0.get(index).add(p);
				}
				ExpCalc.init(Pa0, R0, P0);
				listR = ExpCalc.getR(plan, listP);
			}else if( ModelTypeEnum.API_CL.getId().equals(planModelCl) ) {
				listR = ApiCalc.getR(plan, listP);
			}
			System.out.println("计算产流时间: " + (System.currentTimeMillis() - clTime) + "毫秒");

			sessionUser.getForecast().setListR(stcd, listR);
			sessionUser.getForecast().setListQTR(stcd, listQTR);

			/**
			 * 汇流
			 */
			Long hlTime = System.currentTimeMillis();
			if( ModelTypeEnum.XAJ_HL.getId().equals(planModelHl) ) {
				if( ModelTypeEnum.XAJ_CL.getId().equals(planModelCl) ){
					listQTRR = XajCalc.getQTRR(plan, listP, xajParam);
				}else{
					listQTRR = XajCalc.getQTRR(plan, listP, xajParam);
				}
			}else if( ModelTypeEnum.EXP_HL.getId().equals(planModelHl) ){
				List listUnitLine = unitLineService.selectLinePoint(plan.getInteger("UNITLINE"));
				if( listUnitLine.isEmpty() ){
					throw new ParamException("经验单位线为空(UNITLINE: F)");
				}
				ExpCalc.init(listUnitLine);
				listQTRR = ExpCalc.getQTRR(plan, listR);
			}else if( ModelTypeEnum.API_HL.getId().equals(planModelHl) ) {
				listQTRR = ApiCalc.getQTRR(plan, listR);
			}
			System.out.println("计算汇流时间: " + (System.currentTimeMillis() - hlTime) + "毫秒");
			/**
			 * 保存self qtrr
			 */
			List<BigDecimal> listSelfQTRR = new ArrayList<>();
			for(int j=0; j<listQTRR.size(); j++){
				listSelfQTRR.add(listQTRR.get(j));
			}
			sessionUser.getForecast().setListSelfQTRR(stcd, listSelfQTRR);
			/**
			 * 合并子站QTRR
			 * QTRR汾坑 = QT宁都 + QT石城 + QTRR汾坑
			 * 如果QT和QTRR数组数量不一致怎么处理
			 */
			if( childList.size() > 0 ) {
				listQTRR = addList(childList, listQTRR);
			}
//			/** 找坐标极值 */
//			List<BigDecimal> extremum = NumberUtil.find2Extremum(riverMin, riverMax, listQTRR, true);
//			riverMin = extremum.get(0);
//			riverMax = extremum.get(1);
			List<BigDecimal> extremum;
			sessionUser.getForecast().setListQTRR(stcd, listQTRR);

			/**
			 * 转换成水位
			 */
			Long swTime = System.currentTimeMillis();
			if( type == 1 ){
				/**
				 * 寻找流量线最大值
				 */
//				for (int j = 0; j < listQTRR.size(); j++) {
//					BigDecimal r = listQTRR.get(j);
//					if( NumberUtil.gt(r, riverMax) ){
//						riverMax = r;
//					}
//					if( NumberUtil.lt(r, riverMin) ){
//						riverMin = r;
//					}
//				}
				extremum = NumberUtil.find2Extremum(riverMin, riverMax, listQTRR, false);
				riverMin = extremum.get(0);
				riverMax = extremum.get(1);
				riverMin = riverMin.multiply(new BigDecimal("0.8"));
				riverMax = riverMax.multiply(new BigDecimal("1.2"));
			}else if( type == 2 && !StationTypeEnum.getCode(StationTypeEnum.RR.getId()).equals(sttp) ){
				List<Zq> zqList = zqService.selectZq(stcd, zqService.selectYear(stcd));
//				System.out.println(stname);
//				for(int k=0; k<zqList.size(); k++) {
//					Zq zq = zqList.get(k);
//					System.out.println("x: " + zq.getX() + " y: " + zq.getY());
//				}
				for(int j=0; j<listQTRR.size(); j++){
					BigDecimal r = listQTRR.get(j);
					Zq zqMin = null;
					Zq zqMax = null;
					for(int k=0; k<zqList.size(); k++){
						Zq zq = zqList.get(k);
						if( NumberUtil.ge(r, zq.getX()) ){
							zqMin = new Zq();
							zqMin.setX(zq.getX());
							zqMin.setY(zq.getY());
							break;
						}
					}
					for(int k=zqList.size()-1; k>=0; k--){
						Zq zq = zqList.get(k);
						if( NumberUtil.le(r, zq.getX()) ){
							zqMax = new Zq();
							zqMax.setX(zq.getX());
							zqMax.setY(zq.getY());
							break;
						}
					}
					if( zqMin == null && zqMax != null ){
						BigDecimal y = zqMax.getY();
						r = y;
					}else if( zqMin != null && zqMax == null ){
						BigDecimal y = zqMin.getY();
						r = y;
					}else if( zqMin != null && zqMax != null ){
						BigDecimal x = r;
						BigDecimal x1 = zqMin.getX();
						BigDecimal y1 = zqMin.getY();
						BigDecimal x2 = zqMax.getX();
						BigDecimal y2 = zqMax.getY();
						BigDecimal y = null;
						if( x2.equals(x1) ) {
							y = y1;
						}else{
							y = y1.add(x.subtract(x1).multiply(y2.subtract(y1)).divide(x2.subtract(x1), NumberConst.DIGIT, NumberConst.MODE)).setScale(2, NumberConst.MODE);
						}
						r = y;
					}else{
						throw new ParamException("ZQ转换错误");
					}
					listQTRR.set(j, r);
					if( NumberUtil.gt(r, riverMax) ){
						riverMax = r;
					}
					if( NumberUtil.lt(r, riverMin) ){
						riverMin = r;
					}
				}
				riverMax = riverMax.add(new BigDecimal(3));
				riverMin = riverMin.subtract(new BigDecimal(1));
			}
			System.out.println("转换水位时间: " + (System.currentTimeMillis() - swTime) + "毫秒");
 			sessionUser.getForecast().setRiverMax(stcd, riverMax);
			sessionUser.getForecast().setRiverMin(stcd, riverMin);

            /**
             * 根据站的类型选择马斯京根或调洪演算
			 * 根节点站不需要计算QT
             */
			Long qtTime = System.currentTimeMillis();
            if( !stcd.equals(rootStcd) ) {
				List<BigDecimal> listQT = new ArrayList<>();
				if (StationTypeEnum.getCode(StationTypeEnum.RR.getId()).equals(sttp)) {
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					SimpleDateFormat monthDayFormat = new SimpleDateFormat("MMdd");
					List<Map> listZvarl = read3Service.selectZvarlList(stcd, forecastTime);
					Date fTime = DateUtil.str2date(forecastTime, "yyyy-MM-dd");
					Map initData = readService.selectInitData(stcd, forecastTime, yearFormat.format(fTime), monthDayFormat.format(fTime));
					List<Map> listDischarge = dischargeService.selectListByStcd(stcd);
					List<BigDecimal> Z_CUR = new ArrayList<>();
					List<BigDecimal> V_CUR = new ArrayList<>();
					List<BigDecimal> Z0 = new ArrayList<>();
					List<BigDecimal> HCOQ = new ArrayList<>();
					if (initData.get("rz") == null && initData.get("rz_bk") == null) {
						throw new ParamException(stname + "(" + stcd + ")起调水位参数为空(表ST_RSVR_R字段RZ)");
					}
					if (initData.get("oq") == null) {
						throw new ParamException(stname + "(" + stcd + ")初始出库流量参数为空(表ST_RSVR_R字段OTQ)");
					}
					if (initData.get("lim") == null && initData.get("lim_bk") == null) {
						throw new ParamException(stname + "(" + stcd + ")汛限水位参数为空(表ST_RSVRFSR_B字段FSLTDZ)");
					}
					if (listDischarge.size() == 0) {
						throw new ParamException(stname + "(" + stcd + ")泄流曲线不存在");
					}
					for (Map zvarl : listZvarl) {
						Z_CUR.add(new BigDecimal(String.valueOf(zvarl.get("rz"))));
						V_CUR.add(new BigDecimal(String.valueOf(zvarl.get("w"))));
					}
					for (Map discharge : listDischarge) {
						Z0.add(new BigDecimal(String.valueOf(discharge.get("z0"))));
						HCOQ.add(new BigDecimal(String.valueOf(discharge.get("hcoq"))));
					}
					BigDecimal RZ = new BigDecimal(String.valueOf(initData.get("rz") != null ? initData.get("rz") : initData.get("rz_bk")));
					BigDecimal OTQ = new BigDecimal(String.valueOf(initData.get("oq")));
					BigDecimal FSLTDZ = new BigDecimal(String.valueOf(initData.get("lim") != null ? initData.get("lim") : initData.get("lim_bk")));
					ComCalc.init(Z_CUR, V_CUR, Z0, HCOQ, RZ, OTQ, FSLTDZ);
					List<BigDecimal> listOQ = null;
					List<BigDecimal> listW = new ArrayList<>();
					List<BigDecimal> listZ = new ArrayList<>();
					if( !updateOQ ) {
						listOQ = ComCalc.getOQ(m.getBigDecimal("intv"), listR.isEmpty() ? listQTR : listR, listQTRR, listW, listZ);
						sessionUser.getForecast().setListOQ(stcd, listOQ);
						sessionUser.getForecast().setListW(stcd, listW);
						sessionUser.getForecast().setListZ(stcd, listZ);
					}else{
						listOQ = sessionUser.getForecast().getListOQ(stcd);
						listQTRR = sessionUser.getForecast().getListQTRR(stcd);
						listW = sessionUser.getForecast().getListW(stcd);
						listZ = sessionUser.getForecast().getListZ(stcd);
						ComCalc.rvrOQ(m.getBigDecimal("intv"), listOQ, listQTRR, listW, listZ);
					}
					List<Map> listInq = readService.selectInqList(stcd, forecastTime, affectTime);
					BigDecimal lastInq = NumberConst.ZERO;
					if( listInq.size() > 0 ){
						lastInq = (BigDecimal) listInq.get(0).get("inq");
					}
					List<BigDecimal> listINQ = new ArrayList<>();
					for( int k=0; k<listTime.size(); k++ ){
						String time = listTime.get(k);
						listINQ.add(lastInq);
						for( Map inq : listInq ){
							String t = DateUtil.date2str((Date)inq.get("tm"), "yyyy-MM-dd HH:mm");
							if( time.equals(t) && inq.get("inq") != null ){
								listINQ.set(k, (BigDecimal) inq.get("inq"));
								lastInq = listINQ.get(k);
								break;
							}
						}
					}
					sessionUser.getForecast().setListINQ(stcd, listINQ);
					/** 寻找最大值 */
					BigDecimal yMin = NumberConst.ZERO;
					BigDecimal yMax = NumberConst.ZERO;
					extremum = NumberUtil.find2Extremum(yMin, yMax, listQTRR, true);
					yMin = extremum.get(0);
					yMax = extremum.get(1);
					extremum = NumberUtil.find2Extremum(yMin, yMax, listOQ, true);
					yMin = extremum.get(0);
					yMax = extremum.get(1);
					extremum = NumberUtil.find2Extremum(yMin, yMax, listINQ, true);
					yMin = extremum.get(0);
					yMax = extremum.get(1);
					yMin = yMin.multiply(new BigDecimal("0.05"));
					yMax = yMax.multiply(new BigDecimal("1.8"));
					sessionUser.getForecast().setYMax(stcd, yMax);
					sessionUser.getForecast().setYMin(stcd, yMin);
					riverMin = NumberConst.ZERO;
					riverMax = NumberConst.ZERO;
					extremum = NumberUtil.find2Extremum(riverMin, riverMax, listZ, true);
					riverMin = extremum.get(0);
					riverMax = extremum.get(1);
					riverMin = riverMin.subtract(new BigDecimal(1));
					riverMax = riverMax.add(new BigDecimal(3));
					sessionUser.getForecast().setRiverMax(stcd, riverMax);
					sessionUser.getForecast().setRiverMin(stcd, riverMin);

					listQT = ComCalc.getQT(m.getBigDecimal("ke"), m.getBigDecimal("xe"), listR.isEmpty() ? listQTR : listR, listOQ);
				} else {
					listQT = ComCalc.getQT(m.getBigDecimal("ke"), m.getBigDecimal("xe"), listR.isEmpty() ? listQTR : listR, listQTRR);
				}
				result.add(listQT);

				sessionUser.getForecast().setListQT(stcd, listQT);
			}
			System.out.println("调洪演算时间: " + (System.currentTimeMillis() - qtTime) + "毫秒");
			if( fatherStcd != null ){
            	sessionUser.getForecast().setListChildStcd(fatherStcd, stcd);
			}
		}
		/**
		 * 返回本站结果
		 * QTRR汾坑 = QT宁都 + QT石城 + QTRR汾坑
		 */
        for(int i = 0; i < result.size(); i++){
            if( retval.size() == 0 ){
                retval = result.get(0);
                continue;
            }
            retval = addList(retval, result.get(i));
        }
		return retval;
	}

	private List<BigDecimal> addList(List<BigDecimal> list1, List<BigDecimal> list2){
	    List<BigDecimal> retval = new ArrayList<>();
	    if( list1.size() != list2.size() ) {
			System.out.println("list1.size() != list2.size() : " + list1.size() + ", " + list2.size());
	    }
	    int size = list1.size() < list2.size() ? list1.size() : list2.size();
		for (int i = 0; i < size; i++) {
			retval.add(list1.get(i).add(list2.get(i)));
		}
        return retval;
    }

	private String plusDay(Integer num, String newDate){
		SimpleDateFormat format = new SimpleDateFormat(CommonConst.DATETIME_FORMAT);
		Date currdate = null;
		try {
			currdate = format.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(currdate);
		ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
		currdate = ca.getTime();
		String enddate = format.format(currdate);
		return enddate;
	}
}
