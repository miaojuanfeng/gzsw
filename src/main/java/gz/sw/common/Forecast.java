package gz.sw.common;

import gz.sw.constant.NumberConst;
import gz.sw.entity.write.Plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2021年02月01日
 */
public class Forecast {

    private Map<String, List<BigDecimal>> listP = new HashMap<>();

    private Map<String, List<BigDecimal>> listR = new HashMap<>();

    private Map<String, List<BigDecimal>> listQT = new HashMap<>();

    private Map<String, List<BigDecimal>> listQTR = new HashMap<>();

    private Map<String, List<BigDecimal>> listQTRR = new HashMap<>();

    private Map<String, List<BigDecimal>> listSelfQTRR = new HashMap<>();

    private Map<String, List<BigDecimal>> listOQ = new HashMap<>();

    private Map<String, List<BigDecimal>> listW = new HashMap<>();

    private Map<String, List<BigDecimal>> listINQ = new HashMap<>();

    private Map<String, List<BigDecimal>> listZ = new HashMap<>();

    private Map<String, List<String>> listTime = new HashMap<>();

    private Map<String, List<BigDecimal>> listRiver = new HashMap<>();

    private Map<String, Integer> listMaxP = new HashMap<>();

    private Map<String, String> listStname = new HashMap<>();

    private Map<String, String> listSttp = new HashMap<>();

    private Map<String, String> listForecastText = new HashMap<>();

    private Map<String, String> listForecastUnit = new HashMap<>();

    private Map<String, String> listForecastColor = new HashMap<>();

    private Map<String, BigDecimal> listRiverMax = new HashMap<>();

    private Map<String, BigDecimal> listRiverMin = new HashMap<>();

    private Map<String, BigDecimal> listYMax = new HashMap<>();

    private Map<String, BigDecimal> listYMin = new HashMap<>();

    private Map<String, List<String>> listChildStcd = new HashMap<>();

    public List<BigDecimal> getListP(String stcd) {
        return listP.get(stcd);
    }

    public void setListP(String stcd, List<BigDecimal> listP) {
        this.listP.put(stcd, listP);
    }

    public List<BigDecimal> getListR(String stcd) {
        return listR.get(stcd);
    }

    public void setListR(String stcd, List<BigDecimal> listR) {
        this.listR.put(stcd, listR);
    }

    public List<BigDecimal> getListQT(String stcd) {
        return listQT.get(stcd);
    }

    public void setListQT(String stcd, List<BigDecimal> listQT) {
        this.listQT.put(stcd, listQT);
    }

    public Map<String, List<BigDecimal>> getListQTR() {
        return listQTR;
    }

    public void setListQTR(String stcd, List<BigDecimal> listQTR) {
        this.listQTR.put(stcd, listQTR);
    }

    public List<BigDecimal> getListQTRR(String stcd) {
        return listQTRR.get(stcd);
    }

    public void setListQTRR(String stcd, List<BigDecimal> listQTRR) {
        this.listQTRR.put(stcd, listQTRR);
    }

    public List<BigDecimal> getListSelfQTRR(String stcd) {
        return listSelfQTRR.get(stcd);
    }

    public void setListSelfQTRR(String stcd, List<BigDecimal> listSelfQTRR) {
        this.listSelfQTRR.put(stcd, listSelfQTRR);
    }

    public List<BigDecimal> getListOQ(String stcd) {
        return listOQ.get(stcd);
    }

    public void setListOQ(String stcd, List<BigDecimal> listOQ) {
        this.listOQ.put(stcd, listOQ);
    }

    public List<BigDecimal> getListW(String stcd) {
        return listW.get(stcd);
    }

    public void setListW(String stcd, List<BigDecimal> listW) {
        this.listW.put(stcd, listW);
    }

    public List<BigDecimal> getListINQ(String stcd) {
        return listINQ.get(stcd);
    }

    public void setListINQ(String stcd, List<BigDecimal> listINQ) {
        this.listINQ.put(stcd, listINQ);
    }

    public List<BigDecimal> getListZ(String stcd) {
        return listZ.get(stcd);
    }

    public void setListZ(String stcd, List<BigDecimal> listZ) {
        this.listZ.put(stcd, listZ);
    }

    public List<String> getListTime(String stcd) {
        return listTime.get(stcd);
    }

    public void setListTime(String stcd, List<String> listTime) {
        this.listTime.put(stcd, listTime);
    }

    public List<BigDecimal> getListRiver(String stcd) {
        return listRiver.get(stcd);
    }

    public void setListRiver(String stcd, List<BigDecimal> listRiver) {
        this.listRiver.put(stcd, listRiver);
    }

    public Integer getListMaxP(String stcd) {
        return listMaxP.get(stcd);
    }

    public void setListMaxP(String stcd, Integer maxP) {
        this.listMaxP.put(stcd, maxP);
    }

    public String getStname(String stcd) {
        return listStname.get(stcd);
    }

    public void setStname(String stcd, String stname) {
        this.listStname.put(stcd, stname);
    }

    public String getSttp(String stcd) {
        return listSttp.get(stcd);
    }

    public void setSttp(String stcd, String listSttp) {
        this.listSttp.put(stcd, listSttp);
    }

    public String getForecastText(String stcd) {
        return listForecastText.get(stcd);
    }

    public void setForecastText(String stcd, String forecastText) {
        this.listForecastText.put(stcd, forecastText);
    }

    public String getForecastUnit(String stcd) {
        return this.listForecastUnit.get(stcd);
    }

    public void setForecastUnit(String stcd, String forecastUnit) {
        this.listForecastUnit.put(stcd, forecastUnit);
    }

    public String getForecastColor(String stcd) {
        return listForecastColor.get(stcd);
    }

    public void setForecastColor(String stcd, String forecastColor) {
        this.listForecastColor.put(stcd, forecastColor);
    }

    public BigDecimal getRiverMax(String stcd) {
        return listRiverMax.get(stcd);
    }

    public void setRiverMax(String stcd, BigDecimal riverMax) {
        this.listRiverMax.put(stcd, riverMax);
    }

    public BigDecimal getRiverMin(String stcd) {
        return listRiverMin.get(stcd);
    }

    public void setRiverMin(String stcd, BigDecimal riverMin) {
        this.listRiverMin.put(stcd, riverMin);
    }

    public BigDecimal getYMax(String stcd) {
        return listYMax.get(stcd);
    }

    public void setYMax(String stcd, BigDecimal listYMax) {
        this.listYMax.put(stcd, listYMax);
    }

    public BigDecimal getYMin(String stcd) {
        return listYMin.get(stcd);
    }

    public void setYMin(String stcd, BigDecimal listYMin) {
        this.listYMin.put(stcd, listYMin);
    }

    public List<String> getListChildStcd(String stcd) {
        return listChildStcd.get(stcd);
    }

    public void setListChildStcd(String stcd, String childStcd) {
        if( !this.listChildStcd.containsKey(stcd) ){
            this.listChildStcd.put(stcd, new ArrayList<>());
        }
        this.listChildStcd.get(stcd).add(childStcd);
    }
}
