package zhwb.study.url;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2014/7/10
 * Time: 22:32
 *
 * 说明：对某用户设置某状态

 application/json 请求方式
 输入参数
 {"user_id":1,"status":1,"operate_name":"你好","reason":"test_add"}
 *
 * @author jack.zhang
 */
public class HttpPostJson {

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost post = new HttpPost("");
        post.setHeader("Content-Type","application/json;charset=UTF-8");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user_id",1);
        map.put("status",1);
        map.put("operate_name","fuck");
        map.put("reason","test");

        StringEntity stringEntity = new StringEntity(JSON.toJSONString(map));

        post.setEntity(stringEntity);
        HttpResponse resp = httpClient.execute(post);

        System.out.println(EntityUtils.toString(resp.getEntity()));



    }
}
