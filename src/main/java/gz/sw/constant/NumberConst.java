package gz.sw.constant;

import java.math.BigDecimal;

public class NumberConst {
	
	/*
	 * 0值
	 */
	public static final BigDecimal ZERO = new BigDecimal(0);
	
	/*
	 * 1值
	 */
	public static final BigDecimal ONE = new BigDecimal(1);

	/*
	 * 小数保留位数
	 */
	public static final int DIGIT = 6;
	
	/*
	 * 小数舍弃模式
	 */
	public static final int MODE = BigDecimal.ROUND_HALF_UP;
}
