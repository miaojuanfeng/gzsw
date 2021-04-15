package gz.sw.util;

import java.math.BigDecimal;

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

}
