import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minidev.json.reader.JsonWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxuanxuan
 * On 2020-09-30 14:58
 */
public class Test {
    @org.junit.Test
    public void testJson(){
        List<String> authorities = new ArrayList<>();
        authorities.add("ROLE_ADMIN");
        authorities.add("ROLE_USER");
        authorities.add("ROLE_API");
        Map<String,Object> jsonToken = new HashMap<>();
        jsonToken.put("principle", "tian");
        jsonToken.put("authorities", authorities);
        String token = JSON.toJSONString(jsonToken);
        System.out.println(token);
        //{"principle":"tian","authorities":["ROLE_ADMIN","ROLE_USER","ROLE_API"]}

        JSONObject jsonObject = JSON.parseObject(token);
        System.out.println(jsonObject);
        JSONArray authArray = jsonObject.getJSONArray("authorities");
        List<String> authority = authArray.toJavaList(String.class);
        System.out.println(authority);
    }
}
