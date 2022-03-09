package gz.sw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date str2date(String date, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		Date retval = null;
		try {
			retval = fmt.parse(date);
		} catch (ParseException e) {
			System.out.println("日期计算错误" + e.getMessage());
		}
		return retval;
	}

	/**
	 * 返回两个日期相差的小时数
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static Integer hoursBetween(String startDate,String endDate) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate));
		long time2 = cal.getTimeInMillis();
		long between_hours=(time2-time1)/(1000*3600);
		return Integer.parseInt(String.valueOf(between_hours));
	}

	public static Date addHour(Date date, Integer num){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.HOUR, num);
		return ca.getTime();
	}

	/****
	 * 返回具体日期增加num天。
	 * @param date 日期
	 * @param num  月数
	 * @throws ParseException
	 */
	public static Date addDay(Date date, Integer num){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, num);
		return ca.getTime();
	}

	/****
	 * 返回具体日期增加num个月。
	 * @param date 日期
	 * @param num  月数
	 * @throws ParseException
	 */
	public static Date addMonth(Date date, Integer num){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, num);
		return ca.getTime();
	}

	public static String date2str(Date date, String format){
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}

	public static String date2str(Date date){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}

	public static String getDate() {
		return new SimpleDateFormat("YYYY年MM月dd日 - EEEE").format(new Date());
	}
}
