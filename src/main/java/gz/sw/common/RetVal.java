package gz.sw.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2021年07月20日
 */
public class RetVal {

    public static JSONObject OK(){
        JSONObject retval = new JSONObject();
        retval.put("code", 200);
        return retval;
    }

    public static JSONObject Error(String msg){
        JSONObject retval = new JSONObject();
        retval.put("code", 500);
        retval.put("msg", msg);
        return retval;
    }

    public static JSONObject OK(Map data){
        JSONObject retval = new JSONObject();
        retval.put("code", 200);
        retval.put("data", data);
        return retval;
    }

    public static JSONObject OK(List data){
        JSONObject retval = new JSONObject();
        retval.put("code", 200);
        retval.put("data", data);
        return retval;
    }

    public static JSONObject OK(JSONArray data){
        JSONObject retval = new JSONObject();
        retval.put("code", 200);
        retval.put("data", data);
        return retval;
    }

    public static JSONObject OK(Integer count, List data){
        JSONObject retval = new JSONObject();
        retval.put("code", 0);
        retval.put("count", count);
        retval.put("data", data);
        return retval;
    }
}
