package framework.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Date: 2014/7/22
 * Time: 11:19
 *
 * @author jack.zhang
 */
public class BuilderHttpClient {
    private CloseableHttpClient client = HttpClientBuilder
            .create().setDefaultRequestConfig(
                    RequestConfig
                            .custom()
                            .setConnectTimeout(100)
                            .setSocketTimeout(100)
                            .build())
            .build();

    public static void main(String[] args) throws IOException {
        CloseableHttpResponse response = new BuilderHttpClient().client.execute(new HttpGet("http://www.baidu.com"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
