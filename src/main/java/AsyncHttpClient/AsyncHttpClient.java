package AsyncHttpClient;

import java.util.Map;

public interface AsyncHttpClient {
    void get(String address, Map<String,String> headers);
    void post(String address, Map<String,String> headers, String payload);
    void put(String address, Map<String,String> headers, String payload);
    void delete(String address, Map<String,String> headers, String payload);
}
