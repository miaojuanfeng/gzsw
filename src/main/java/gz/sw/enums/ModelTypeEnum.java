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

    XAJ_HL(2, 1, "滞后演算法"),

    EXP_CL(3, 2, "经验降径关系线"),

    EXP_HL(4, 2, "经验单位线"),

    API_CL(5, 3, "公式降径关系线"),

    API_HL(6, 3, "瞬时单位线");

    private final Integer id;

    private final Integer parent;

    private final String text;

    private ModelTypeEnum(Integer id, Integer parent, String text){
        this.id = id;
        this.parent = parent;
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

    public static List getList(Integer mtype){
        List retval = new ArrayList();
        ModelTypeEnum[] modelTypeEnums = values();
        for (ModelTypeEnum modelTypeEnum : modelTypeEnums) {
            if (modelTypeEnum.parent.equals(mtype)) {
                Map m = new HashMap<>();
                m.put("id", modelTypeEnum.id);
                m.put("text", modelTypeEnum.text);
                retval.add(m);
            }
        }
        return retval;
    }
}
