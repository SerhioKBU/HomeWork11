package AsyncHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MainHttp {
    public final static String ADDRESS1 = "https://httpbin.org/get";
    public final static String ADDRESS2 = "https://httpbin.org/post";
    public final static String ADDRESS3 = "https://httpbin.org/put";
    public final static String ADDRESS4 = "https://httpbin.org/delete";
    public final static String ADDRESS5 = "http://webcode.me";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newBuilder().build();

        Map<String, String> post = new HashMap<>();
        post.put("Student", "Serhii");
        post.put("Level", "Elementary");
        byte[] postPayLoad = post.toString().getBytes(StandardCharsets.UTF_8);

        AsyncHttpClientImp httpClient = new AsyncHttpClientImp(client, objectMapper);

       //-------------GET REQUEST-----------------
        httpClient.get(ADDRESS1,
                Map.of("Accept", "application/json"));

        //-------------POST REQUEST-----------------
        httpClient.post(ADDRESS2,
                Map.of("User-Agent", "Google Chrome",
                        "Content-Type", "text/plain"),
                new String(postPayLoad));

        //-------------PUT REQUEST-----------------
        httpClient.put(ADDRESS3,
                Map.of( "Accept", "audio/mp3, video/mkv, image/png"),
                "Put PayLoad");


        //-------------DELETE REQUEST--------------
        httpClient.delete(ADDRESS4,
                Map.of( "Content-Type", "application/json"),
                "");


        //http://dummy.restapiexample.com/api/v1/create
        //http://dummy.restapiexample.com/api/v1/delete/{id}
        //http://dummy.restapiexample.com/api/v1/employees
        //http://dummy.restapiexample.com/api/v1/employee/{id}
        //https://jsonplaceholder.typicode.com/posts
        //https://openjdk.java.net
    }
}
