import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {

    public static HttpResponse sendPost(String urlString, String payload) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(urlString);
        post.setHeader("Content-type", "application/json");
        post.setEntity(new StringEntity(payload));
        return httpClient.execute(post);
    }
    public static HttpResponse sendAuthorizedGet(String urlString, String token) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(urlString);
        get.setHeader("Content-type", "application/json");
        get.setHeader("Authorization", "Bearer " + token);
        return httpClient.execute(get);
    }
    public static HttpResponse sendAuthorizedPut(String urlString, String payload, String token) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut put = new HttpPut(urlString);
        put.setHeader("Content-type", "application/json");
        put.setHeader("Authorization", "Bearer " + token);
        put.setEntity(new StringEntity(payload));
        return httpClient.execute(put);
    }

    public static HttpResponse sendAuthorizedPost(String urlString, String payload, String token) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(urlString);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "Bearer " + token);
        post.setEntity(new StringEntity(payload));
        return httpClient.execute(post);
    }

    public static String getResponseContent(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
}
