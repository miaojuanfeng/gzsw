package gz.sw.util;

import gz.sw.constant.NumberConst;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NumberUtil {

	/**
	 * 指数计算
	 * @param base 底数
	 * @param power 指数
	 * @return
	 */
	public static BigDecimal pow(BigDecimal base, BigDecimal power) {
		return new BigDecimal(String.valueOf(Math.pow(base.doubleValue(), power.doubleValue())));
	}
	
	/**
	 * A大于B
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean gt(BigDecimal A, BigDecimal B) {
		return A.compareTo(B) == 1;
	}
	
	/**
	 * A小于B
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean lt(BigDecimal A, BigDecimal B) {
		return A.compareTo(B) == -1;
	}
	
	/**
	 * A等于B
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean et(BigDecimal A, BigDecimal B) {
		return A.compareTo(B) == 0;
	}

	/**
	 * A大于等于B
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean ge(BigDecimal A, BigDecimal B) {
		return A.compareTo(B) != -1;
	}

	/**
	 * A小于等于B
	 * @param A
	 * @param B
	 * @return
	 */
	public static boolean le(BigDecimal A, BigDecimal B) {
		return A.compareTo(B) != 1;
	}

	public static List find2Extremum(BigDecimal min, BigDecimal max, List<BigDecimal> list, boolean firstCheck){
		List<BigDecimal> retval = new ArrayList<>();
		for(BigDecimal l : list ){
			if( NumberUtil.gt(l, max) ){
				max = l;
			}
			/** 第一次比较最小值需要判0 */
			if( NumberUtil.lt(l, min) || (firstCheck && NumberUtil.et(min, NumberConst.ZERO)) ){
				min = l;
			}
		}
		retval.add(min);
		retval.add(max);
		return retval;
	}
}
