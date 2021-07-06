package gz.sw.calc;

import com.alibaba.fastjson.JSONObject;
import gz.sw.common.XajParam;
import gz.sw.constant.CommonConst;
import gz.sw.constant.NumberConst;
import gz.sw.entity.write.Plan;
import gz.sw.util.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 新安江算法
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月20日
 */
public class XajCalc {

    /**
     * 产流
     * @return
     */
    public static List<BigDecimal> getR(JSONObject plan, List<BigDecimal> listP, XajParam xajParam, Integer returnType){
//        List<BigDecimal> listR = new ArrayList<>();
        /**
         * 中间数据
         */
        List<BigDecimal> listPE = new ArrayList<>();
        List<BigDecimal> listR = new ArrayList<>();
//        List<BigDecimal> listRd = new ArrayList<>();
        List<BigDecimal> listA = new ArrayList<>();
        List<BigDecimal> listWU = new ArrayList<>();
        List<BigDecimal> listWL = new ArrayList<>();
        List<BigDecimal> listWD = new ArrayList<>();
        List<BigDecimal> listEU = new ArrayList<>();
        List<BigDecimal> listEL = new ArrayList<>();
        List<BigDecimal> listED = new ArrayList<>();
        List<BigDecimal> listW = new ArrayList<>();
        List<BigDecimal> listES = new ArrayList<>();
//        List<BigDecimal> listRs = new ArrayList<>();
        List<BigDecimal> listFR = new ArrayList<>();
//        List<BigDecimal> listRss = new ArrayList<>();
//        List<BigDecimal> listRg = new ArrayList<>();
        List<BigDecimal> listS = new ArrayList<>();
        List<BigDecimal> listN = new ArrayList<>();
        List<BigDecimal> listQ = new ArrayList<>();
        List<BigDecimal> listKSSDD = new ArrayList<>();
        List<BigDecimal> listKGDD = new ArrayList<>();
        List<BigDecimal> listAU = new ArrayList<>();
        List<BigDecimal> listRSD = new ArrayList<>();
        List<BigDecimal> listRSSD = new ArrayList<>();
        List<BigDecimal> listRGD = new ArrayList<>();
//        List<BigDecimal> listQRS = new ArrayList<>();
//        List<BigDecimal> listQRSS = new ArrayList<>();
//        List<BigDecimal> listQRG = new ArrayList<>();
        List<BigDecimal> listQTR = new ArrayList<>();
        for (int i = 0; i < listP.size(); i++){
            listPE.add(NumberConst.ZERO);
            listR.add(NumberConst.ZERO);
            listA.add(NumberConst.ZERO);
            listWU.add(NumberConst.ZERO);
            listWL.add(NumberConst.ZERO);
            listWD.add(NumberConst.ZERO);
            listEU.add(NumberConst.ZERO);
            listEL.add(NumberConst.ZERO);
            listED.add(NumberConst.ZERO);
            listW.add(NumberConst.ZERO);
            listES.add(NumberConst.ZERO);
            listFR.add(NumberConst.ZERO);
            listS.add(NumberConst.ZERO);
            listN.add(NumberConst.ZERO);
            listQ.add(NumberConst.ZERO);
            listKSSDD.add(NumberConst.ZERO);
            listKGDD.add(NumberConst.ZERO);
            listAU.add(NumberConst.ZERO);
            listRSD.add(NumberConst.ZERO);
            listRSSD.add(NumberConst.ZERO);
            listRGD.add(NumberConst.ZERO);
            listQTR.add(NumberConst.ZERO);
            xajParam.listRs.add(NumberConst.ZERO);
            xajParam.listRss.add(NumberConst.ZERO);
            xajParam.listRg.add(NumberConst.ZERO);
            xajParam.listQRS.add(NumberConst.ZERO);
            xajParam.listQRSS.add(NumberConst.ZERO);
            xajParam.listQRG.add(NumberConst.ZERO);
            xajParam.listRd.add(NumberConst.ZERO);
        }
        /**
         * 读取参数
         */
//        BigDecimal F = plan.getBigDecimal("F");
        BigDecimal K = plan.getBigDecimal("K");
        BigDecimal E = plan.getBigDecimal("E");
//        BigDecimal IM = plan.getBigDecimal("IM");
        BigDecimal WUM = plan.getBigDecimal("WUM");
        BigDecimal WLM = plan.getBigDecimal("WLM");
        BigDecimal WDM = plan.getBigDecimal("WDM");
        BigDecimal B = plan.getBigDecimal("B");
        BigDecimal C = plan.getBigDecimal("C");
        BigDecimal KSS = plan.getBigDecimal("KSS");
        BigDecimal KG = plan.getBigDecimal("KG");
        BigDecimal SM = plan.getBigDecimal("SM");
        BigDecimal EX = plan.getBigDecimal("EX");
//        BigDecimal CI = plan.getBigDecimal("CI");
//        BigDecimal CG = plan.getBigDecimal("CG");
//        BigDecimal CS = plan.getBigDecimal("CS");
//        Integer LAG = plan.getBigDecimal("L").intValue();
        BigDecimal T = plan.getBigDecimal("T");
        BigDecimal KE = plan.getBigDecimal("KE");
        BigDecimal XE = plan.getBigDecimal("XE");
        /**
         * 读初始状态
         */
        BigDecimal WUup = plan.getBigDecimal("WU0");
        BigDecimal WLup = plan.getBigDecimal("WL0");
        BigDecimal WDup = plan.getBigDecimal("WD0");
        BigDecimal Wup = WUup.add(WLup).add(WDup);
        BigDecimal Sup = plan.getBigDecimal("S0");
        BigDecimal FRup = plan.getBigDecimal("FR0");
//        BigDecimal QRSup = plan.getBigDecimal("QRS0");
//        BigDecimal QRSSup = plan.getBigDecimal("QRSS0");
//        BigDecimal QRGup = plan.getBigDecimal("QRG0");
//        BigDecimal QTRup = QRSup.add(QRSSup).add(QRGup);
        /**
         * 参数转换
         */
        BigDecimal Wm = WUM.add(WLM).add(WDM);
        BigDecimal Wmm = Wm.multiply(B.add(NumberConst.ONE));
        BigDecimal KSSD = getKSSD(KG, KSS, T);
        BigDecimal KGD = KSSD.multiply(KG).divide(KSS, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal SMM = NumberConst.ZERO;
        BigDecimal SMF = NumberConst.ZERO;
        BigDecimal SMMF = SM.multiply(EX.add(NumberConst.ONE));
//        BigDecimal U = F.divide(T.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE);
        /**
         * C0 = (0.5 * T - KE * XE) / (KE - KE * XE + 0.5 * T)
         * C1 = (0.5 * T + KE * XE) / (KE - KE * XE + 0.5 * T)
         * C2 = (KE - KE * XE - 0.5 * T) / (KE - KE * XE + 0.5 * T)
         * temp1 = 0.5 * T
         * temp2 = KE * XE
         * temp3 = KE - KE * XE + 0.5 * T
         */
        T = KE;
        BigDecimal temp1 = T.multiply(new BigDecimal("0.5"));
        BigDecimal temp2 = KE.multiply(XE);
        BigDecimal temp3 = KE.subtract(temp2).add(temp1);
        BigDecimal C0 = temp1.subtract(temp2).divide(temp3, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal C1 = temp1.add(temp2).divide(temp3, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal C2 = KE.subtract(temp2).subtract(temp1).divide(temp3, NumberConst.DIGIT, NumberConst.MODE);
        /**
         * 循环雨量P
         */
        for (int i = 0; i < listP.size(); i++){
            listPE.set(i, listP.get(i).subtract(K.multiply(E)));
            listA.set(i, getA(Wup, Wm, Wmm, B));
            /**
             * 产流量计算
             */
            if( NumberUtil.gt(listPE.get(i), NumberConst.ZERO) ){
                xajParam.listRd.set(i, listPE.get(i));
                if( NumberUtil.ge(listPE.get(i).add(listA.get(i)), Wmm) ){
                    listR.set(i, listPE.get(i).subtract(Wm.subtract(Wup)));
                }else{
                    BigDecimal base = NumberConst.ONE.subtract(listPE.get(i).add(listA.get(i)).divide(Wmm, NumberConst.DIGIT, NumberConst.MODE));
                    BigDecimal power = NumberConst.ONE.add(B);
                    BigDecimal R = listPE.get(i).add(Wup).subtract(Wm).add(Wm.multiply(NumberUtil.pow(base, power)));
                    listR.set(i, R);
                }
            }else{
                xajParam.listRd.set(i, NumberConst.ZERO);
                listR.set(i, NumberConst.ZERO);
            }
            /**
             * 蒸发计算
             */
            if( NumberUtil.gt(listPE.get(i), NumberConst.ZERO) ){
                if( NumberUtil.gt(WUup.add(listPE.get(i)).subtract(listR.get(i)), WUM) ){
                    if( NumberUtil.gt(WUup.add(WLup).add(listPE.get(i)).subtract(listR.get(i)).subtract(WUM), WLM) ){
                        listWU.set(i, WUM);
                        listWL.set(i, WLM);
                        listWD.set(i, Wup.add(listPE.get(i)).subtract(listR.get(i)).subtract(listWU.get(i)).subtract(listWL.get(i)));
                    }else{
                        listWU.set(i, WUM);
                        listWL.set(i, WUup.add(WLup).add(listPE.get(i)).subtract(listR.get(i)).subtract(WUM));
                        listWD.set(i, WDup);
                    }
                }else{
                    listWU.set(i, WUup.add(listPE.get(i).subtract(listR.get(i))));
                    listWL.set(i, WLup);
                    listWD.set(i, WDup);
                }
                listEU.set(i, K.multiply(E));
                listEL.set(i, NumberConst.ZERO);
                listED.set(i, NumberConst.ZERO);
            }else{
                if( NumberUtil.gt(WUup, new BigDecimal(Math.abs(listPE.get(i).doubleValue()))) ){
                    listEU.set(i, K.multiply(E));
                    listED.set(i, NumberConst.ZERO);
                    listEL.set(i, NumberConst.ZERO);
                    listWU.set(i, WUup.add(listPE.get(i)));
                    listWL.set(i, WLup);
                    listWD.set(i, WDup);
                }else{
                    listWU.set(i, NumberConst.ZERO);
                    listEU.set(i, WUup.add(listP.get(i)));
                    if( NumberUtil.gt(WLup, WLM.multiply(C)) ){
                        listEL.set(i, (K.multiply(E).subtract(listEU.get(i))).multiply(WLup).divide(WLM, NumberConst.DIGIT, NumberConst.MODE));
                        listWL.set(i, WLup.subtract(listEL.get(i)));
                        listED.set(i, NumberConst.ZERO);
                        listWD.set(i, WDup);
                    }else{
                        BigDecimal temp = (K.multiply(E).subtract(listEU.get(i))).multiply(C);
                        if( NumberUtil.gt(WLup, temp) ){
                            listEL.set(i, temp);
                            listWL.set(i, WLup.subtract(listEL.get(i)));
                            listED.set(i, NumberConst.ZERO);
                            listWD.set(i, WDup);
                        }else{
                            listEL.set(i, WLup);
                            listWL.set(i, NumberConst.ZERO);
                            listED.set(i, temp.subtract(listEL.get(i)));
                            listWD.set(i, WDup.subtract(listED.get(i)));
                        }
                    }
                }
            }
            if( NumberUtil.gt(listWU.get(i), WUM) ){
                listWU.set(i, WUM);
            }
            if( NumberUtil.gt(listWL.get(i), WLM) ){
                listWL.set(i, WLM);
            }
            if( NumberUtil.gt(listWD.get(i), WDM) ){
                listWD.set(i, WDM);
            }

            listW.set(i, listWU.get(i).add(listWL.get(i)).add(listWD.get(i)));
            listES.set(i, listEU.get(i).add(listEL.get(i)).add(listED.get(i)));

            WUup = listWU.get(i);
            WLup = listWL.get(i);
            WDup = listWD.get(i);
            Wup = listW.get(i);
            /**
             * 分水源计算
             */
            if( NumberUtil.le(listPE.get(i), NumberConst.ZERO) ){
                xajParam.listRs.set(i, NumberConst.ZERO);
                listFR.set(i, getFR(listW.get(i), Wm, B));
                FRup = listFR.get(i);
                xajParam.listRss.set(i, Sup.multiply(KSSD).multiply(FRup));
                xajParam.listRg.set(i, Sup.multiply(KGD).multiply(FRup));
                listS.set(i, Sup.subtract(xajParam.listRss.get(i).add(xajParam.listRg.get(i)).divide(FRup, NumberConst.DIGIT, NumberConst.MODE)));
            }else{
                listFR.set(i, getFR(listPE.get(i), listR.get(i)));
                listS.set(i, FRup.multiply(Sup).divide(listFR.get(i), NumberConst.DIGIT, NumberConst.MODE));
                listQ.set(i, listR.get(i).divide(listFR.get(i), NumberConst.DIGIT, NumberConst.MODE));
                listN.set(i, new BigDecimal(listQ.get(i).divide(new BigDecimal(5), NumberConst.DIGIT, NumberConst.MODE).intValue()).add(NumberConst.ONE));
                listQ.set(i, listQ.get(i).divide(listN.get(i), NumberConst.DIGIT, NumberConst.MODE));
                listKSSDD.set(i, getKSSDD(KGD, KSSD, listN.get(i)));
                listKGDD.set(i, listKSSDD.get(i).multiply(KGD).divide(KSSD, NumberConst.DIGIT, NumberConst.MODE));
                xajParam.listRs.set(i, NumberConst.ZERO);
                xajParam.listRss.set(i, NumberConst.ZERO);
                xajParam.listRg.set(i, NumberConst.ZERO);
                SMM = SM.multiply(EX.add(NumberConst.ONE));
                if( NumberUtil.et(EX, NumberConst.ZERO) ){
                    SMMF = SMM;
                }else{
                    SMMF = getSMMF(listFR.get(i), EX, SMM);
                }
                SMF = SMMF.divide(EX.add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
                for(int j = 1; j <= listN.get(i).intValue(); j++ ){
                    if( NumberUtil.gt(listS.get(i), SMF) ){
                        listS.set(i, SMF);
                    }
                    listAU.set(i, getAU(SMMF, listS.get(i), SMF, EX));
                    if( NumberUtil.le(listQ.get(i).add(listAU.get(i)), NumberConst.ZERO) ){
                        listRSD.set(j, NumberConst.ZERO);
                        listRSSD.set(j, NumberConst.ZERO);
                        listRGD.set(j, NumberConst.ZERO);
                        listS.set(j, NumberConst.ZERO);
                    }else{
                        if( NumberUtil.ge(listQ.get(i).add(listAU.get(i)), SMMF) ){
                            listRSD.set(j, listFR.get(i).multiply(listQ.get(i).add(listS.get(i).subtract(SMF))));
                            listRSSD.set(j, SMF.multiply(listKSSDD.get(i)).multiply(listFR.get(i)));
                            listRGD.set(j, SMF.multiply(listFR.get(i)).multiply(listKGDD.get(i)));
                            listS.set(i, SMF.subtract(listRSSD.get(j).add(listRGD.get(j)).divide(listFR.get(i), NumberConst.DIGIT, NumberConst.MODE)));
                        }else{
                            BigDecimal base = NumberConst.ONE.subtract((listQ.get(i).add(listAU.get(i))).divide(SMMF, NumberConst.DIGIT, NumberConst.MODE));
                            BigDecimal power = NumberConst.ONE.add(EX);
                            listRSD.set(j, listQ.get(i).subtract(SMF).add(listS.get(i)).add(SMF.multiply(NumberUtil.pow(base, power))).multiply(listFR.get(i)));
                            listRSSD.set(j, listS.get(i).add(listQ.get(i)).subtract(listRSD.get(j).divide(listFR.get(i), NumberConst.DIGIT, NumberConst.MODE)).multiply(listKSSDD.get(i)).multiply(listFR.get(i)));
                            listRGD.set(j, listS.get(i).add(listQ.get(i)).subtract(listRSD.get(j).divide(listFR.get(i), NumberConst.DIGIT, NumberConst.MODE)).multiply(listKGDD.get(i)).multiply(listFR.get(i)));
                            listS.set(i, listS.get(i).add(listQ.get(i)).subtract(listRSD.get(j).add(listRSSD.get(j)).add(listRGD.get(j)).divide(listFR.get(i), NumberConst.DIGIT, NumberConst.MODE)));
                        }
                    }
                    xajParam.listRs.set(i, xajParam.listRs.get(i).add(listRSD.get(j)));
                    xajParam.listRss.set(i, xajParam.listRss.get(i).add(listRSSD.get(j)));
                    xajParam.listRg.set(i, xajParam.listRg.get(i).add(listRGD.get(j)));
                }
            }
            Sup = listS.get(i);
            FRup = listFR.get(i);
            /**
             * 坡面汇流
             */
//            listQRS.set(i, (listRs.get(i).multiply(NumberConst.ONE.subtract(IM)).add(listRd.get(i).multiply(IM))).multiply(F).divide(new BigDecimal("3.6"), NumberConst.DIGIT, NumberConst.MODE));
//            listQRSS.set(i, QRSSup.multiply(CI).add(listRss.get(i).multiply(NumberConst.ONE.subtract(CI)).multiply(U)));
//            listQRG.set(i, QRGup.multiply(CG).add(listRg.get(i).multiply(NumberConst.ONE.subtract(CG)).multiply(U)));
//            listQTR.set(i, listQRS.get(i).add(listQRSS.get(i)).add(listQRG.get(i)));
//            listQTR.set(i, QTRup.multiply(CS).add(listQTR.get(i).multiply(NumberConst.ONE.subtract(CS))));
//
//            QRSSup = listQRSS.get(i);
//            QRGup = listQRG.get(i);
//            QTRup = listQTR.get(i);
        }
        return returnType.equals(CommonConst.RETURN_TYPE_R) ? listR : listQTR;
    }

    public static List<BigDecimal> getQTRR(JSONObject plan, List<BigDecimal> listP, List<BigDecimal> listQTR, XajParam xajParam){
        List<BigDecimal> listQTRR = new ArrayList<>();

        BigDecimal T = plan.getBigDecimal("T");
        BigDecimal F = plan.getBigDecimal("F");
        BigDecimal CI = plan.getBigDecimal("CI");
        BigDecimal CG = plan.getBigDecimal("CG");
        BigDecimal CS = plan.getBigDecimal("CS");
        BigDecimal QRSup = plan.getBigDecimal("QRS0");
        BigDecimal QRSSup = plan.getBigDecimal("QRSS0");
        BigDecimal QRGup = plan.getBigDecimal("QRG0");
        BigDecimal QTRup = QRSup.add(QRSSup).add(QRGup);
        BigDecimal IM = plan.getBigDecimal("IM");
        Integer LAG = plan.getBigDecimal("L").intValue();
        BigDecimal KE = plan.getBigDecimal("KE");
        BigDecimal U = F.divide(T.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE);

        for (int i = 0; i < listP.size(); i++){
            xajParam.listQRS.set(i, (xajParam.listRs.get(i).multiply(NumberConst.ONE.subtract(IM)).add(xajParam.listRd.get(i).multiply(IM))).multiply(F).divide(new BigDecimal("3.6"), NumberConst.DIGIT, NumberConst.MODE));
            xajParam.listQRSS.set(i, QRSSup.multiply(CI).add(xajParam.listRss.get(i).multiply(NumberConst.ONE.subtract(CI)).multiply(U)));
            xajParam.listQRG.set(i, QRGup.multiply(CG).add(xajParam.listRg.get(i).multiply(NumberConst.ONE.subtract(CG)).multiply(U)));
            listQTR.set(i, xajParam.listQRS.get(i).add(xajParam.listQRSS.get(i)).add(xajParam.listQRG.get(i)));
            listQTR.set(i, QTRup.multiply(CS).add(listQTR.get(i).multiply(NumberConst.ONE.subtract(CS))));

            QRSSup = xajParam.listQRSS.get(i);
            QRGup = xajParam.listQRG.get(i);
            QTRup = listQTR.get(i);
        }

        for(int i = 0; i < LAG; i++){
            listQTRR.add(listQTR.get(0).setScale(NumberConst.DIGIT, NumberConst.MODE));
        }
        for(int i = LAG; i < listQTR.size() + LAG; i++){
            listQTRR.add(listQTR.get(i-LAG).setScale(NumberConst.DIGIT, NumberConst.MODE));
        }
        if( NumberUtil.gt(KE, new BigDecimal(LAG)) ){
            for(int i = listQTR.size() + LAG; i < listQTR.size() + KE.intValue(); i++){
                listQTRR.add(listQTR.get(listQTR.size()-1).setScale(NumberConst.DIGIT, NumberConst.MODE));
            }
        }
        return listQTRR;
    }

    /**
     * 蒸发量是写死还是动态读取?
     * @return
     */
    private static BigDecimal getE() {
		/*
			月分取P的月份/天数/小时
		 */
//		BigDecimal E = NumberConfig.E.get(9).divide(new BigDecimal(31), NumberConst.DIGIT, NumberConst.MODE).divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
//		return E;
        return new BigDecimal("0.14");
    }

    private static BigDecimal getKSSD(BigDecimal KG, BigDecimal KSS, BigDecimal T){
        // KSSD = (1 - (1 - (KG + KSS)) ^ (T / 24)) / (1 + KG / KSS)
        BigDecimal base = NumberConst.ONE.subtract(KG.add(KSS));
        BigDecimal power = T.divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
        return (NumberConst.ONE.subtract(NumberUtil.pow(base, power))).divide(NumberConst.ONE.add(KG.divide(KSS, NumberConst.DIGIT, NumberConst.MODE)), NumberConst.DIGIT, NumberConst.MODE);
    }

    private static BigDecimal getA(BigDecimal Wup, BigDecimal Wm, BigDecimal Wmm, BigDecimal B){
        // A = Wmm*[1-(1-Wup/Wm)^(1/(B+1))]
        BigDecimal base = NumberConst.ONE.subtract(Wup.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
        BigDecimal power = NumberConst.ONE.divide(B.add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
        return Wmm.multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));

    }

    private static BigDecimal getFR(BigDecimal PE, BigDecimal R) {
        // FR=R/PE
        return R.divide(PE, NumberConst.DIGIT, NumberConst.MODE);
    }

    private static BigDecimal getFR(BigDecimal W, BigDecimal Wm, BigDecimal B) {
        // FR=1-(1-Wi/Wm)^[B/(1+B)]
        BigDecimal base = NumberConst.ONE.subtract(W.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
        BigDecimal power = B.divide(B.add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
        return NumberConst.ONE.subtract(NumberUtil.pow(base, power));
    }

    private static BigDecimal getKSSDD(BigDecimal KGD, BigDecimal KSSD, BigDecimal N){
        // KSSDD = (1 - (1 - (KGD + KSSD)) ^ (1 / N)) / (1 + KGD / KSSD)
        BigDecimal base = NumberConst.ONE.subtract(KGD.add(KSSD));
        BigDecimal power = NumberConst.ONE.divide(N, NumberConst.DIGIT, NumberConst.MODE);
        return (NumberConst.ONE.subtract(NumberUtil.pow(base, power))).divide(NumberConst.ONE.add(KGD.divide(KSSD, NumberConst.DIGIT, NumberConst.MODE)), NumberConst.DIGIT, NumberConst.MODE);
    }

    private static BigDecimal getSMMF(BigDecimal FR, BigDecimal EX, BigDecimal SMM) {
        // SMMF = (1 - (1 - FR(i)) ^ (1 / EX)) * SMM
        BigDecimal base = NumberConst.ONE.subtract(FR);
        BigDecimal power = NumberConst.ONE.divide(EX, NumberConst.DIGIT, NumberConst.MODE);
        return (NumberConst.ONE.subtract(NumberUtil.pow(base, power))).multiply(SMM);
    }

    private static BigDecimal getAU(BigDecimal SMMF, BigDecimal S, BigDecimal SMF, BigDecimal EX) {
        // AU = SMMF * (1 - ( 1 - S / SMF ) ^ [ 1 / ( 1 + EX ) ] )
        BigDecimal base = NumberConst.ONE.subtract(S.divide(SMF, NumberConst.DIGIT, NumberConst.MODE));
        BigDecimal power = NumberConst.ONE.divide(NumberConst.ONE.add(EX), NumberConst.DIGIT, NumberConst.MODE);
        return SMMF.multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
    }

//    public static void main(String[] args){
//        Plan plan = new Plan();
//        plan.setF(new BigDecimal("911"));
//        plan.setK(new BigDecimal("0.735"));
//        plan.setIM(new BigDecimal("0.044"));
//        plan.setWUM(new BigDecimal("10.562"));
//        plan.setWLM(new BigDecimal("68.412"));
//        plan.setWDM(new BigDecimal("1.042"));
//        plan.setB(new BigDecimal("0.5"));
//        plan.setC(new BigDecimal("0.199"));
//        plan.setKSS(new BigDecimal("0.403"));
//        plan.setKG(new BigDecimal("0.297"));
//        plan.setSM(new BigDecimal("25.174"));
//        plan.setEX(new BigDecimal("1.5"));
//        plan.setCI(new BigDecimal("0.112"));
//        plan.setCG(new BigDecimal("0.95"));
//        plan.setCS(new BigDecimal("0.948"));
//        plan.setL(new BigDecimal("4"));
//        plan.setT(new BigDecimal("1"));
//        plan.setKE(new BigDecimal("11"));
//        plan.setXE(new BigDecimal("0.335"));
//        plan.setWU0(new BigDecimal("10"));
//        plan.setWL0(new BigDecimal("60"));
//        plan.setWD0(new BigDecimal("10"));
//        plan.setS0(new BigDecimal("6"));
//        plan.setFR0(new BigDecimal("0.7"));
//        plan.setQRS0(new BigDecimal("3"));
//        plan.setQRSS0(new BigDecimal("38"));
//        plan.setQRG0(new BigDecimal("7"));
//
//        List<BigDecimal> listP = new ArrayList<>();
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("1.62"));
//        listP.add(new BigDecimal("0.20"));
//        listP.add(new BigDecimal("0.08"));
//        listP.add(new BigDecimal("0.48"));
//        listP.add(new BigDecimal("0.25"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.13"));
//        listP.add(new BigDecimal("0.33"));
//        listP.add(new BigDecimal("0.23"));
//        listP.add(new BigDecimal("0.48"));
//        listP.add(new BigDecimal("0.36"));
//        listP.add(new BigDecimal("3.90"));
//        listP.add(new BigDecimal("4.54"));
//        listP.add(new BigDecimal("1.34"));
//        listP.add(new BigDecimal("0.17"));
//        listP.add(new BigDecimal("0.13"));
//        listP.add(new BigDecimal("0.17"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.03"));
//        listP.add(new BigDecimal("4.19"));
//        listP.add(new BigDecimal("10.26"));
//        listP.add(new BigDecimal("8.01"));
//        listP.add(new BigDecimal("2.74"));
//        listP.add(new BigDecimal("2.33"));
//        listP.add(new BigDecimal("1.01"));
//        listP.add(new BigDecimal("0.46"));
//        listP.add(new BigDecimal("3.19"));
//        listP.add(new BigDecimal("4.04"));
//        listP.add(new BigDecimal("0.22"));
//        listP.add(new BigDecimal("0.84"));
//        listP.add(new BigDecimal("2.22"));
//        listP.add(new BigDecimal("6.25"));
//        listP.add(new BigDecimal("4.58"));
//        listP.add(new BigDecimal("3.33"));
//        listP.add(new BigDecimal("4.54"));
//        listP.add(new BigDecimal("2.66"));
//        listP.add(new BigDecimal("1.10"));
//        listP.add(new BigDecimal("0.21"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.16"));
//        listP.add(new BigDecimal("2.54"));
//        listP.add(new BigDecimal("3.16"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.22"));
//        listP.add(new BigDecimal("0.26"));
//        listP.add(new BigDecimal("0.79"));
//        listP.add(new BigDecimal("1.48"));
//        listP.add(new BigDecimal("2.05"));
//        listP.add(new BigDecimal("2.00"));
//        listP.add(new BigDecimal("0.95"));
//        listP.add(new BigDecimal("0.33"));
//        listP.add(new BigDecimal("0.89"));
//        listP.add(new BigDecimal("0.68"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.02"));
//        listP.add(new BigDecimal("0.24"));
//        listP.add(new BigDecimal("0.15"));
//        listP.add(new BigDecimal("0.05"));
//        listP.add(new BigDecimal("0.32"));
//        listP.add(new BigDecimal("0.12"));
//        listP.add(new BigDecimal("1.45"));
//        listP.add(new BigDecimal("0.14"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.07"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.03"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.03"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.12"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.13"));
//        listP.add(new BigDecimal("0.05"));
//        listP.add(new BigDecimal("0.28"));
//        listP.add(new BigDecimal("0.68"));
//        listP.add(new BigDecimal("0.33"));
//        listP.add(new BigDecimal("0.47"));
//        listP.add(new BigDecimal("0.88"));
//        listP.add(new BigDecimal("2.07"));
//        listP.add(new BigDecimal("0.62"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.02"));
//        listP.add(new BigDecimal("0.02"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.15"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("3.10"));
//        listP.add(new BigDecimal("12.21"));
//        listP.add(new BigDecimal("16.30"));
//        listP.add(new BigDecimal("7.46"));
//        listP.add(new BigDecimal("18.24"));
//        listP.add(new BigDecimal("15.13"));
//        listP.add(new BigDecimal("8.72"));
//        listP.add(new BigDecimal("4.68"));
//        listP.add(new BigDecimal("2.35"));
//        listP.add(new BigDecimal("0.89"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.03"));
//        listP.add(new BigDecimal("0.11"));
//        listP.add(new BigDecimal("0.06"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.01"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.25"));
//        listP.add(new BigDecimal("0.69"));
//        listP.add(new BigDecimal("0.14"));
//        listP.add(new BigDecimal("0.26"));
//        listP.add(new BigDecimal("0.03"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.12"));
//        listP.add(new BigDecimal("0.04"));
//        listP.add(new BigDecimal("0.67"));
//        listP.add(new BigDecimal("1.86"));
//        listP.add(new BigDecimal("1.57"));
//        listP.add(new BigDecimal("0.91"));
//        listP.add(new BigDecimal("1.01"));
//        listP.add(new BigDecimal("0.14"));
//        listP.add(new BigDecimal("0.13"));
//        listP.add(new BigDecimal("0.28"));
//        listP.add(new BigDecimal("3.43"));
//        listP.add(new BigDecimal("1.36"));
//        listP.add(new BigDecimal("0.07"));
//        listP.add(new BigDecimal("0.02"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.14"));
//        listP.add(new BigDecimal("0.08"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.25"));
//        listP.add(new BigDecimal("0.85"));
//
//        List<BigDecimal> listR = getR(plan, listP, CommonConst.RETURN_TYPE_QTR);
//        List<BigDecimal> listQTRR = getQTRR(plan, listR);
////        CommonUtil.getQT();
//    }
}
