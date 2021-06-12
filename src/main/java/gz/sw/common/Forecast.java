package gz.sw.common;

import gz.sw.constant.NumberConst;
import gz.sw.entity.write.Plan;

import java.math.BigDecimal;
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

    private Map<String, List<BigDecimal>> listQTR = new HashMap<>();

    private Map<String, List<BigDecimal>> listQTRR = new HashMap<>();

    private Map<String, List<String>> listTime = new HashMap<>();

    private Map<String, List<BigDecimal>> listQT = new HashMap<>();

    private Map<String, Integer> listMaxP = new HashMap<>();

    private Map<String, Plan> planCl;

    private Map<String, Plan> planHl;

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

    public List<String> getListTime(String stcd) {
        return listTime.get(stcd);
    }

    public void setListTime(String stcd, List<String> listTime) {
        this.listTime.put(stcd, listTime);
    }

    public List<BigDecimal> getListQT(String stcd) {
        return listQT.get(stcd);
    }

    public void setListQT(String stcd, List<BigDecimal> listQT) {
        this.listQT.put(stcd, listQT);
    }

    public Integer getListMaxP(String stcd) {
        return listMaxP.get(stcd);
    }

    public void setListMaxP(String stcd, Integer maxP) {
        this.listMaxP.put(stcd, maxP);
    }

    public Plan getPlanCl(String stcd) {
        return planCl.get(stcd);
    }

    public void setPlanCl(String stcd, Plan planCl) {
        this.planCl.put(stcd, planCl);
    }

    public Plan getPlanHl(String stcd) {
        return planHl.get(stcd);
    }

    public void setPlanHl(String stcd, Plan planHl) {
        this.planHl.put(stcd, planHl);
    }
}