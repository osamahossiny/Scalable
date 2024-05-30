//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//
//public class HttpUtil {
//
//    public static HttpResponse sendPost(String url, String jsonPayload) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost post = new HttpPost(url);
//        post.setHeader("Content-type", "application/json");
//        post.setEntity(new StringEntity(jsonPayload));
//        return httpClient.execute(post);
//    }
//
//    public static String getResponseContent(HttpResponse response) throws IOException {
//        return EntityUtils.toString(response.getEntity(), "UTF-8");
//    }
//}
