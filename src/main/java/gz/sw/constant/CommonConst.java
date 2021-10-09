package gz.sw.constant;

import org.springframework.context.ApplicationContext;

public class CommonConst {
//    public static final int HTTP_OK = 200;
//    public static ApplicationContext APPLICATION_CONTEXT = null;

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Integer FUTURE_RAINFALL_MEASURE = 0;
    public static final Integer FUTURE_RAINFALL_MANUAL = 99;
//    public static final Integer FUTURE_RAINFALL_JAPAN = 2;
//    public static final Integer FUTURE_RAINFALL_EUROPE = 6;
//    public static final Integer FORECAST_LIULIANG = 1;
//    public static final Integer FORECAST_SHUIWEI = 2;
    public static final Integer RETURN_TYPE_R = 1;
    public static final Integer RETURN_TYPE_QTR = 2;
    public static final String SESSION_USER = "sessionUser";
    public static final String SECREAT_KEY = "#4dMs^a9)Y1(@DU9";
    public static Object stationLock = new Object();
}
