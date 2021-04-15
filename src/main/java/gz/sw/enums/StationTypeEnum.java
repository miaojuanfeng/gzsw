package gz.sw.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站类型
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年11月24日
 */
public enum StationTypeEnum {

    ZQ(1, "ZQ", "水文站"),

    ZZ(2, "ZZ", "水位站"),

    RR(3, "RR", "水库站"),

    PP(4, "PP", "雨量站");

    private final Integer id;

    private final String code;

    private final String text;

    private StationTypeEnum(Integer id, String code, String text){
        this.id = id;
        this.code = code;
        this.text = text;
    }

    public Integer getId(){
        return id;
    }

    public static String getText(Integer id) {
        StationTypeEnum[] stationTypeEnums = values();
        for (StationTypeEnum stationTypeEnum : stationTypeEnums) {
            if (stationTypeEnum.id.equals(id)) {
                return stationTypeEnum.text;
            }
        }
        return null;
    }

    public static String getText(String code) {
        StationTypeEnum[] stationTypeEnums = values();
        for (StationTypeEnum stationTypeEnum : stationTypeEnums) {
            if (stationTypeEnum.code.equals(code)) {
                return stationTypeEnum.text;
            }
        }
        return null;
    }

    public static String getCode(Integer id) {
        StationTypeEnum[] stationTypeEnums = values();
        for (StationTypeEnum stationTypeEnum : stationTypeEnums) {
            if (stationTypeEnum.id.equals(id)) {
                return stationTypeEnum.code;
            }
        }
        return null;
    }

    public static List getList(){
        List retval = new ArrayList();
        StationTypeEnum[] stationTypeEnums = values();
        for (StationTypeEnum stationTypeEnum : stationTypeEnums) {
            Map m = new HashMap<>();
            m.put("id", stationTypeEnum.id);
            m.put("code", stationTypeEnum.code);
            m.put("text", stationTypeEnum.text);
            retval.add(m);
        }
        return retval;
    }
}
