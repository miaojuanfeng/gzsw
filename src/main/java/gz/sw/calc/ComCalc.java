package gz.sw.calc;

import gz.sw.constant.NumberConst;
import gz.sw.common.Forecast;
import gz.sw.util.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用算法
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月17日
 */
public class ComCalc {
    /**
     * 参数
     */
//    public static Plan plan = new Plan();
    /**
     * 初始化
     */
    private static List<BigDecimal> Z_CUR = new ArrayList<>();
    private static List<BigDecimal> V_CUR = new ArrayList<>();
    private static List<BigDecimal> Z0 = new ArrayList<>();
    private static List<BigDecimal> HCOQ = new ArrayList<>();
    private static BigDecimal RZ = NumberConst.ZERO;
    private static BigDecimal OTQ = NumberConst.ZERO;
    private static BigDecimal FSLTDZ = NumberConst.ZERO;
    /**
     * 结果
     */
//    public static List<BigDecimal> listP = new ArrayList<>();
//    public static List<BigDecimal> listR = new ArrayList<>();
//    public static List<BigDecimal> listQTRR = new ArrayList<>();
//    public static List<BigDecimal> listOQ = new ArrayList<>();
//    public static List<BigDecimal> listQT = new ArrayList<>();
//    public static Forecast forecast = new Forecast();

    /**
     * 初始化数据
     */
    public static void init(List<BigDecimal> z_cur,
                            List<BigDecimal> v_cur,
                            List<BigDecimal> z0,
                            List<BigDecimal> hcoq,
                            BigDecimal rz,
                            BigDecimal otq,
                            BigDecimal fsltdz){
        Z_CUR = z_cur;
        V_CUR = v_cur;
        Z0 = z0;
        HCOQ = hcoq;
        RZ = rz;
        OTQ = otq;
        FSLTDZ = fsltdz;
    }

    /**
     * 马斯京根演算，河道站直接执行该算法
     * QTRR -> QT
     * @return listQT
     */
    public static List<BigDecimal> getQT(BigDecimal KE, BigDecimal XE, List<BigDecimal> listR, List<BigDecimal> listQTRR){
        List<BigDecimal> listQT = new ArrayList<>();

        BigDecimal K = KE;
        BigDecimal T = KE;
        BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
        /**
         * For i = 0 To sum + K -> QT(i) = 0
         */
        for(int i = 0; i < listR.size() + K.intValue(); i++){
            listQT.add(i, NumberConst.ZERO);
        }
        /**
         * C0 = (0.5 * T - KE * XE) / (KE - KE * XE + 0.5 * T)
         * C1 = (0.5 * T + KE * XE) / (KE - KE * XE + 0.5 * T)
         * C2 = (KE - KE * XE - 0.5 * T) / (KE - KE * XE + 0.5 * T)
         */
        BigDecimal divide = KE.subtract(KE.multiply(XE)).add(DECIMAL_0_5.multiply(T));
        BigDecimal C0 = DECIMAL_0_5.multiply(T).subtract(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal C1 = DECIMAL_0_5.multiply(T).add(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal C2 = DECIMAL_0_5.multiply(T).subtract(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
        /**
         * Dim QS1 As Single
         * Dim QS2 As Single
         * Dim QX1 As Single
         * Dim QX2 As Single
         * QX1 = QTRR(0)
         * For i = 0 To sum
         *   QS1 = QTRR(i)
         *   QS2 = QTRR(i + 1)
         *   For J = 1 To K
         *     QX2 = C0 * QS2 + C1 * QS1 + C2 * QX1
         *     QS1 = QX1
         *     QS2 = QX2
         *     QX1 = QX2
         *   Next
         *   QT(i + K) = QX2
         * Next
         */
        BigDecimal QS1;
        BigDecimal QS2;
        BigDecimal QX1 = listQTRR.get(0);
        BigDecimal QX2 = NumberConst.ZERO;
        for (int i = 0; i < listR.size(); i++){
            QS1 = listQTRR.get(i);
            QS2 = listQTRR.get(i+1);
            for (int j = 1; j <= K.intValue(); j++){
                QX2 = C0.multiply(QS2).add(C1.multiply(QS1)).add(C2.multiply(QX1));
                QS1 = QX1;
                QS2 = QX2;
                QX1 = QX2;
            }
            listQT.set(i + K.intValue(), QX2.setScale(NumberConst.DIGIT, NumberConst.MODE));
        }
        /**
         * For i = 0 To K - 1
         *   QT(i) = QT(K)
         * Next
         */
        for (int i = 0; i <= K.intValue() - 1; i++){
            listQT.set(i, listQT.get(K.intValue()));
        }

        return listQT;
    }

    /**
     * 调洪演算，水库站需要执行该算法
     * QTRR -> OQ -> QT
     * @return listQT
     */
    public static List<BigDecimal> getOQ(BigDecimal INTV, List<BigDecimal> listR, List<BigDecimal> listQTRR){
        List<BigDecimal> listOQ = new ArrayList<>();

        // 预见期水位过程
        List<BigDecimal> Z = new ArrayList<>();
        // 预见期库容过程
        List<BigDecimal> W = new ArrayList<>();
        // 汛限水位
        BigDecimal Z_lim;
        // 汛限水位对应的蓄水量
        BigDecimal W_lim;
        // 比汛限水位低0.5m的水位
        BigDecimal Z00;
        // 比汛限水位低0.5m的蓄水量
        BigDecimal W00;
        //
        BigDecimal Temp_W;
        BigDecimal Temp_Z;
        BigDecimal Temp_OQ;
        // 入库洪峰
        BigDecimal maxIQ;
        // 入库洪峰所在位置
        Integer maxIQ_xiuhao;
        // 最大泄流量
        BigDecimal maxOQ;

        /**
         * 初始化数据
         */
        for (int i = 0; i <= listR.size(); i++){
            Z.add(NumberConst.ZERO);
            listOQ.add(NumberConst.ZERO);
            W.add(NumberConst.ZERO);
        }
//        /**
//         * 取初始数据
//         */
//        List<BigDecimal> Z0 = new ArrayList<>();
//        List<BigDecimal> HCOQ = new ArrayList<>();
//        TempUtil.getXL(Z0, HCOQ);
//        List<BigDecimal> Z_CUR = new ArrayList<>();
//        List<BigDecimal> V_CUR = new ArrayList<>();

        /**
         * 读取起调水位，从实时数据库读取  ST_RSVR_R，预报时间的水位    字段RZ
         */
        Z.set(0, RZ);
        W.set(0, diffVal(Z.get(0), Z_CUR, V_CUR));
        /**
         * 读取初始出库流量，从实时数据库读取  ST_RSVR_R，预报时间往前最近的一个出库流量   字段OTQ
         */
        listOQ.set(0, OTQ);
        Temp_OQ = listOQ.get(0);
        /**
         * 读取汛限水位，从实时数据库的汛期水位表 ST_RSVRFSR_B 读取   字段FSLTDZ      BGMD EDMD两个字段为开始结束时间，根据这两个时间来读汛限水位
         */
        Z_lim = FSLTDZ;
        W_lim = diffVal(Z_lim, Z_CUR, V_CUR);
        Z00 = Z_lim.subtract(new BigDecimal("0.5"));
        W00 = diffVal(Z00, Z_CUR, V_CUR);
        /**
         * 找到入库流量洪峰所在位置
         */
        maxIQ = listQTRR.get(0);
        for (int i = 1; i < listR.size(); i++){
            if ( NumberUtil.gt(listQTRR.get(i), maxIQ) ){
                maxIQ = listQTRR.get(i);
                maxIQ_xiuhao = i;
            }
        }
        /**
         * 调洪演算
         */
        for (int i = 0; i < listR.size(); i++){
            /**
             * Temp_W = W(i) + (QTRR(i) + QTRR(i + 1)) * 0.0018 * INTV - OQ(i) * 0.0036 * INTV
             */
            Temp_W = W.get(i).add(temp1(listQTRR, i, INTV)).subtract(temp2(listOQ, i, INTV));
            if( NumberUtil.gt(Temp_W, W_lim) ){
                // OQ(i) = (W(i) + (QTRR(i + 1) + QTRR(i)) * 0.0018 * INTV - W00) / (0.0036 * INTV)
                listOQ.set(i, W.get(i).add(temp1(listQTRR, i, INTV)).subtract(W00).divide(temp3(INTV), NumberConst.DIGIT, NumberConst.MODE));
                if( NumberUtil.gt(listOQ.get(i), maxIQ) ){
                    listOQ.set(i, maxIQ);
                }
                Temp_W = W.get(i).add(temp1(listQTRR, i, INTV)).subtract(temp2(listOQ, i, INTV));
            }
            if( NumberUtil.lt(Temp_W, W_lim) ){
                // OQ(i) = (W(i) + (QTRR(i + 1) + QTRR(i)) * 0.0018 * INTV - W00) / (0.0036 * INTV)
                listOQ.set(i, W.get(i).add(temp1(listQTRR, i, INTV)).subtract(W00).divide(temp3(INTV), NumberConst.DIGIT, NumberConst.MODE));
                if( NumberUtil.gt(listOQ.get(i), maxIQ) ){
                    listOQ.set(i, maxIQ);
                }
                Temp_W = W.get(i).add(temp1(listQTRR, i, INTV)).subtract(temp2(listOQ, i, INTV));
                if( NumberUtil.le(listOQ.get(i), NumberConst.ZERO) ){
                    listOQ.set(i, Temp_OQ);
                    Temp_W = W.get(i).add(temp1(listQTRR, i, INTV)).subtract(temp2(listOQ, i, INTV));
                }
            }
            /**
             * Temp_Z = chazhi(W(i), CUR_sum, V_CUR, Z_CUR)
             */
            Temp_Z = diffVal(W.get(i), V_CUR, Z_CUR);
            maxOQ = diffVal(Temp_Z, Z0, HCOQ);

            if( NumberUtil.gt(listOQ.get(i), maxOQ) ){
                listOQ.set(i, maxOQ);
                Temp_W = W.get(i).add(temp1(listQTRR, i, INTV)).subtract(temp2(listOQ, i, INTV));
            }

            Temp_Z = diffVal(Temp_W, V_CUR, Z_CUR);
            W.set(i+1, Temp_W);
            Z.set(i+1, Temp_Z);
            listOQ.set(i+1, listOQ.get(i));
            Temp_OQ = listOQ.get(i+1);
        }

        return listOQ;
    }

    private static BigDecimal diffVal(BigDecimal x, List<BigDecimal> X0, List<BigDecimal> Y0){
        BigDecimal tempX = x;
        Integer x_wz = -1;
        if( NumberUtil.le(tempX, X0.get(0)) ){
            tempX = X0.get(0);
            x_wz = 0;
        }
        if( NumberUtil.ge(tempX, X0.get(X0.size()-1)) ){
            tempX = X0.get(X0.size()-1);
            x_wz = X0.size()-1;
        }
        for (int i = 0; i < X0.size()-1; i++){
            if( NumberUtil.ge(tempX, X0.get(i)) && NumberUtil.le(tempX, X0.get(i+1)) ){
                x_wz = i;
                break;
            }
        }
        /**
         * chazhi = (x_tmp - X0(x_wz)) / (X0(x_wz + 1) - X0(x_wz)) * (Y0(x_wz + 1) - Y0(x_wz)) + Y0(x_wz)
         */
        BigDecimal temp1 = tempX.subtract(X0.get(x_wz));
        BigDecimal temp2 = X0.get(x_wz+1).subtract(X0.get(x_wz));
        BigDecimal temp3 = Y0.get(x_wz+1).subtract(Y0.get(x_wz));
        BigDecimal temp4 = Y0.get(x_wz);
        return temp1.divide(temp2, NumberConst.DIGIT, NumberConst.MODE).multiply(temp3).add(temp4);
    }

    private static BigDecimal temp1(List<BigDecimal> listQTRR, Integer i, BigDecimal INTV){
        // return (QTRR(i) + QTRR(i + 1)) * 0.0018 * INTV;
        BigDecimal DECIMAL_0_0018 = new BigDecimal("0.0018");
        return INTV.multiply(DECIMAL_0_0018).multiply(listQTRR.get(i).add(listQTRR.get(i+1)));
    }

    private static BigDecimal temp2(List<BigDecimal> OQ, Integer i, BigDecimal INTV){
        // return OQ(i) * 0.0036 * INTV;
        BigDecimal DECIMAL_0_0036 = new BigDecimal("0.0036");
        return INTV.multiply(DECIMAL_0_0036).multiply(OQ.get(i));
    }

    private static BigDecimal temp3(BigDecimal INTV){
        // return 0.0036 * INTV;
        BigDecimal DECIMAL_0_0036 = new BigDecimal("0.0036");
        return INTV.multiply(DECIMAL_0_0036);
    }
}
