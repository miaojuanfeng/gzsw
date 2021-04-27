package gz.sw.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2021年04月25日
 */
public enum ModelTypeEnum {

    XAJ_CL(1, 1, "蓄满产流法"),

    XAJ_HL(2, 2, "滞后演算法"),

    EXP_CL(3, 1, "经验降径关系线"),

    EXP_HL(4, 2, "经验单位线"),

    API_CL(5, 1, "公式降径关系线"),

    API_HL(6, 2, "瞬时单位线");

    private final Integer id;

    private final Integer type;

    private final String text;

    public static final Integer MODEL_TYPE_CL = 1;

    public static final Integer MODEL_TYPE_HL = 2;

    private ModelTypeEnum(Integer id, Integer type, String text){
        this.id = id;
        this.type = type;
        this.text = text;
    }

    public Integer getId(){
        return id;
    }

    public static String getText(Integer id) {
        ModelTypeEnum[] modelTypeEnums = values();
        for (ModelTypeEnum modelTypeEnum : modelTypeEnums) {
            if (modelTypeEnum.id.equals(id)) {
                return modelTypeEnum.text;
            }
        }
        return null;
    }

    public static List getList(Integer type){
        List retval = new ArrayList();
        ModelTypeEnum[] modelTypeEnums = values();
        for (ModelTypeEnum modelTypeEnum : modelTypeEnums) {
            if (modelTypeEnum.type.equals(type)) {
                Map m = new HashMap<>();
                m.put("id", modelTypeEnum.id);
                m.put("text", modelTypeEnum.text);
                retval.add(m);
            }
        }
        return retval;
    }
}
