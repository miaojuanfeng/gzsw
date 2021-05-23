package gz.sw.calc;

import com.alibaba.fastjson.JSONObject;
import gz.sw.constant.NumberConst;
import gz.sw.entity.write.Plan;
import gz.sw.util.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * API算法
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年07月31日
 */
public class ApiCalc {

    /**
     * 产流
     * @return
     */
    public static List<BigDecimal> getR(JSONObject plan, List<BigDecimal> listP){
        List<BigDecimal> listR = new ArrayList<>();
        /**
         * 读取参数
         */
        BigDecimal KR = plan.getBigDecimal("KR");
        BigDecimal IM = plan.getBigDecimal("IM");
        BigDecimal IMM = plan.getBigDecimal("IMM");
        BigDecimal PA = plan.getBigDecimal("PA");
        /**
         * B = Imm / Im
         * B = Round(B, 6)
         */
        BigDecimal B = IMM.divide(IM, NumberConst.DIGIT, NumberConst.MODE);
        /**
         * A = Imm * (1 - (1 - Pa / Im) ^ (1 / B))
         * A = Round(A, 6)
         */
        BigDecimal base = NumberConst.ONE.subtract(PA.divide(IM, NumberConst.DIGIT, NumberConst.MODE));
        BigDecimal power = NumberConst.ONE.divide(B, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal A = IMM.multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));

        for(int i = 0; i < listP.size(); i++){
            BigDecimal p = listP.get(i);
            BigDecimal r;
            if( NumberUtil.lt(p.add(A), IMM) ){
                /**
                 * R(i) = Kr * (P(i) + Pa - Im + Im * (1 - (P(i) + A) / Imm) ^ B)
                 */
                base = NumberConst.ONE.subtract(p.add(A).divide(IMM, NumberConst.DIGIT, NumberConst.MODE));
                power = B;
                r = KR.multiply(p.add(PA).subtract(IM).add(IM.multiply(NumberUtil.pow(base, power))));
            }else{
                /**
                 * R(i) = Kr * (P(i) - Im + Pa)
                 */
                r = KR.multiply(p.subtract(IM).add(PA));
            }
            if( NumberUtil.lt(r, NumberConst.ZERO) ){
                r = NumberConst.ZERO;
            }
            listR.add(i, r);
        }
        listR.add(NumberConst.ZERO);

        return listR;
    }

    /**
     * 汇流
     * @return
     */
    public static List<BigDecimal> getQTRR(JSONObject plan, List<BigDecimal> listR){
        List<BigDecimal> listQTRR = new ArrayList<>();
        /**
         * 读取参数
         */
        BigDecimal NA = plan.getBigDecimal("NA");
        BigDecimal NU = plan.getBigDecimal("NU");
        BigDecimal KG = plan.getBigDecimal("KG");
        BigDecimal KU = plan.getBigDecimal("KU");
        BigDecimal AREA = plan.getBigDecimal("AREA");

        List<BigDecimal> listQu = new ArrayList<>();

        BigDecimal DECIMAL_0_15 = new BigDecimal("0.15");
        BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
        BigDecimal DECIMAL_0_88 = new BigDecimal("0.88");
        BigDecimal DECIMAL_3 = new BigDecimal("3");
        BigDecimal DECIMAL_3_6 = new BigDecimal("3.6");
        BigDecimal DECIMAL_6 = new BigDecimal("6");

        /**
         * For i = 0 To Nu -> Qu(i) = 0
         */
        for(int i = 0; i <= NU.intValue(); i++){
            listQu.add(i, NumberConst.ZERO);
        }
        /**
         * For i = 0 To sum + 24 -> QTRR(i) = 0
         * 这里如果sum+24，则会出现下标越界问题
         */
        for(int i = 0; i < listR.size() + listQu.size(); i++){
            listQTRR.add(i, NumberConst.ZERO);
        }
        /**
         * V = 1
         * E = Exp(1)
         */
        BigDecimal v = NumberConst.ONE;
        BigDecimal e = new BigDecimal(Math.E);
        /**
         * For i = 1 To Na - 1 -> V = V * i
         */
        for(int i = 1; i <= NA.intValue()-1; i++){
            v = v.multiply(new BigDecimal(i));
        }
        /**
         * For j = 1 To Nu
         */
        BigDecimal Yp = NumberConst.ZERO;
        BigDecimal Qu = NumberConst.ZERO;
        for(int j = 1; j <= NU.intValue(); j++){
            if( j == 1 ){
                /**
                 * Yp = 0.15 / 3.6 / (Kg + 0.5)
                 * Qu(J) = 0.88 / (Ku * 6 * 3.6) * Exp(1) ^ (-J / Ku) * (J / Ku) ^ 3 + 0.15 / 3.6 / (Kg + 0.5)
                 */
                Yp = DECIMAL_0_15.divide(DECIMAL_3_6.multiply(KG.add(DECIMAL_0_5)), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp1 = DECIMAL_0_88.divide(KU.multiply(DECIMAL_6).multiply(DECIMAL_3_6), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp2 = NumberUtil.pow(e, NumberConst.ZERO.subtract(new BigDecimal(j)).divide(KU, NumberConst.DIGIT, NumberConst.MODE));
                BigDecimal temp3 = NumberUtil.pow(new BigDecimal(j).divide(KU, NumberConst.DIGIT, NumberConst.MODE), DECIMAL_3);
                BigDecimal temp4 = DECIMAL_0_15.divide(DECIMAL_3_6, NumberConst.DIGIT, NumberConst.MODE).divide(KG.add(DECIMAL_0_5), NumberConst.DIGIT, NumberConst.MODE);
                Qu = temp1.multiply(temp2).multiply(temp3).add(temp4);
            }else{
                /**
                 * Yp = (Kg - 0.5) * Yp / (Kg + 0.5)
                 * Qu(J) = 0.88 / (Ku * V * 3.6) * Exp(1) ^ (-J / Ku) * (J / Ku) ^ 3 + Yp
                 */
                Yp = KG.subtract(DECIMAL_0_5).multiply(Yp).divide(KG.add(DECIMAL_0_5), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp1 = DECIMAL_0_88.divide(KU.multiply(v).multiply(DECIMAL_3_6), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp2 = NumberUtil.pow(e, NumberConst.ZERO.subtract(new BigDecimal(j)).divide(KU, NumberConst.DIGIT, NumberConst.MODE));
                BigDecimal temp3 = NumberUtil.pow(new BigDecimal(j).divide(KU, NumberConst.DIGIT, NumberConst.MODE), DECIMAL_3);
                Qu = temp1.multiply(temp2).multiply(temp3).add(Yp);
            }
            listQu.set(j, Qu);
        }
        /**
         * Yp = 0
         * For i = 0 To Nu -> Yp = Yp + Qu(i)
         */
        Yp = NumberConst.ZERO;
        for(int i = 0; i <= NU.intValue(); i++){
            Yp = Yp.add(listQu.get(i));
        }
        /**
         * Yp = 1 / (Yp * 3.6)
         */
        Yp = NumberConst.ONE.divide(Yp.multiply(DECIMAL_3_6), NumberConst.DIGIT, NumberConst.MODE);
        /**
         * For i = 0 To Nu -> Qu(i) = Qu(i) * Yp
         */
        for(int i = 0; i <= NU.intValue(); i++){
            listQu.set(i, listQu.get(i).multiply(Yp));
        }
        /**
         * For i = 0 To sum
         *   For J = 1 To 50
         *      QTRR(i + J - 1) = QTRR(i + J - 1) + R(i) * Qu(J) * Area
         */
        for(int i = 0; i < listR.size(); i++){
            for(int j = 1; j <= 50; j++){
                BigDecimal temp1 = listQTRR.get(i + j - 1);
                BigDecimal temp2 = listR.get(i).multiply(listQu.get(j)).multiply(AREA);
                listQTRR.set(i + j - 1, temp1.add(temp2));
            }
        }
        /**
         * 把多余的数组元素移除
         */
        Iterator<BigDecimal> it = listQTRR.iterator();
        int i = 0;
        while(it.hasNext()){
            it.next();
            if( i++ >= listR.size() + 24 ) {
                it.remove();
            }
        }

        return listQTRR;
    }

    public static void main(String[] args){
        List<BigDecimal> listP = new ArrayList<>();
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("10.00"));
//        listP.add(new BigDecimal("20.00"));
//        listP.add(new BigDecimal("30.00"));
//        listP.add(new BigDecimal("50.00"));
//        listP.add(new BigDecimal("100.00"));
//        listP.add(new BigDecimal("80.00"));
//        listP.add(new BigDecimal("70.00"));
//        listP.add(new BigDecimal("60.00"));
//        listP.add(new BigDecimal("50.00"));
//        listP.add(new BigDecimal("4.00"));
//        listP.add(new BigDecimal("3.00"));
//        listP.add(new BigDecimal("2.00"));
//        listP.add(new BigDecimal("1.00"));
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
//        listP.add(new BigDecimal("2.00"));
//        listP.add(new BigDecimal("0.50"));
//        listP.add(new BigDecimal("0.40"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.20"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("2.60"));
//        listP.add(new BigDecimal("2.80"));
//        listP.add(new BigDecimal("1.80"));
//        listP.add(new BigDecimal("2.30"));
//        listP.add(new BigDecimal("2.80"));
//        listP.add(new BigDecimal("0.90"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.20"));
//        listP.add(new BigDecimal("1.10"));
//        listP.add(new BigDecimal("2.10"));
//        listP.add(new BigDecimal("2.90"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.80"));
//        listP.add(new BigDecimal("2.40"));
//        listP.add(new BigDecimal("2.40"));
//        listP.add(new BigDecimal("1.90"));
//        listP.add(new BigDecimal("1.60"));
//        listP.add(new BigDecimal("1.10"));
//        listP.add(new BigDecimal("1.20"));
//        listP.add(new BigDecimal("2.10"));
//        listP.add(new BigDecimal("1.50"));
//        listP.add(new BigDecimal("0.90"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.40"));
//        listP.add(new BigDecimal("0.70"));
//        listP.add(new BigDecimal("1.30"));
//        listP.add(new BigDecimal("1.50"));
//        listP.add(new BigDecimal("0.90"));
//        listP.add(new BigDecimal("0.30"));
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
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
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
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
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
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));

        listP.add(new BigDecimal("20"));
        listP.add(new BigDecimal("40"));
        listP.add(new BigDecimal("60"));
        listP.add(new BigDecimal("80"));
        listP.add(new BigDecimal("100"));
        listP.add(new BigDecimal("80"));
        listP.add(new BigDecimal("60"));
        listP.add(new BigDecimal("40"));
        listP.add(new BigDecimal("20"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));

//        getR(plan, );
//        getQTRR();
//        CommonUtil.getQT();
//        CommonUtil.getOQ();
    }
}
