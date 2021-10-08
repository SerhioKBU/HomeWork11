package AsyncHttpClient;

import lombok.Setter;

@Setter
public class Response {
    private String response;

    @Override
    public String toString() {
        return response;
    }
}
