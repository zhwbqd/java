package zhwb.study.url;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Date: 14-1-14
 * Time: 上午7:58
 *
 * @author jack.zhang
 */

public class HttpInvoker {

    /**
     * The constant GET_URL.
     */
    public static final String GET_URL = "http://user.api.vip.com/users/3213123/vmark/detail";
    /**
     * The constant POST_URL.
     */
    public static final String POST_URL = "http://user.api.vip.com/users/3213123/vmark/";

    /**
     * Read content from get.
     *
     * @throws IOException the iO exception
     */
    public static void readContentFromGet() {
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
        URL getUrl = null;
        try {
            getUrl = new URL(GET_URL);

            // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
            // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();
            // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
            // 服务器
            connection.connect();
            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            System.out.println("=============================");
            System.out.println("Contents of get request");
            System.out.println("=============================");
            String lines;
            while ((lines = reader.readLine()) != null) {
                System.out.println(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            System.out.println("=============================");
            System.out.println("Contents of get request ends");
            System.out.println("=============================");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Read content from post.
     *
     * @throws IOException the iO exception
     */
    public static void readContentFromPost() {
        try {
            URL postUrl = null;
            postUrl = new URL(POST_URL);

            // Post请求的url，与get不同的是不需要带参数
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) postUrl
                    .openConnection();
            // Output to the connection. Default is
            // false, set to true because post
            // method must write something to the
            // connection
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // Set the post method. Default is GET
            connection.setRequestMethod("POST");
            // Post cannot use caches
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            // This method takes effects to
            // every instances of this class.
            // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
            // connection.setFollowRedirects(true);

            // This methods only
            // takes effacts to this
            // instance.
            // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
            connection.setInstanceFollowRedirects(true);
            // Set the content type to urlencoded,
            // because we will write
            // some URL-encoded content to the
            // connection. Settings above must be set before connect!
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            // The URL-encoded contend
            // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
            String content = "vmark_type_name=" + URLEncoder.encode("register", "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            out.writeBytes(content);

            content = "comment=" + URLEncoder.encode("测试", "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            out.writeBytes(content);

            out.flush();
            out.close(); // flush and close

            String line;
            System.out.println(connection.getContent());

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            System.out.println("=============================");
            System.out.println("Contents of post request");
            System.out.println("=============================");
            System.out.println(connection.getResponseCode() + connection.getResponseMessage());
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("=============================");
            System.out.println("Contents of post request ends");
            System.out.println("=============================");
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        readContentFromPost();
        readContentFromGet();
    }
}