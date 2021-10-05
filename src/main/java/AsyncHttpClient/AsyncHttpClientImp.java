package AsyncHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
public class AsyncHttpClientImp implements AsyncHttpClient {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private final HttpClient client;
    private final ObjectMapper objectMapper;
    String responseBody = "";

    @Override
    public void get(String address, Map<String, String> headers) {

    Thread thread = new Thread(()-> {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(address))
                .headers(headers.entrySet().stream()
                        .map(header -> new String[]{header.getKey(), header.getValue()})
                        .flatMap(Arrays::stream)
                        .toArray(String[]::new))
                .build();
        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                responseBody = response.body();
                System.out.println("-------------GET REQUEST-----------------\n" +
                        ANSI_GREEN + "Status Code: " + response.statusCode() + ANSI_RESET);
                System.out.println(responseBody);
            } else {
                System.out.println(ANSI_RED + "GET request doesn't work" + ANSI_RESET);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    });
        thread.start();
    }

    @Override
    public void post(String address, Map<String, String> headers, String payload) {

        Thread thread = new Thread(()-> {
        try {
            HttpRequest request = null;
            //for (Map.Entry<String, String> header : headers.entrySet()) {
                String requestBody = objectMapper.writeValueAsString(payload);
                request = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .uri(URI.create(address))
                        .headers(headers.entrySet().stream()
                                .map(header -> new String[]{header.getKey(), header.getValue()})
                                .flatMap(Arrays::stream)
                                .toArray(String[]::new))
                        .build();
            //}

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                responseBody = response.body();
                System.out.println("-------------POST REQUEST-----------------\n" +
                        ANSI_GREEN + "ResponseCode: " + response.statusCode() + ANSI_RESET);
                System.out.println(responseBody);
            } else {
                System.out.println(ANSI_RED + "POST request doesn't work" + ANSI_RESET);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    });
        thread.start();
    }


    @Override
    public void put(String address, Map<String, String> headers, String payload) {
        Thread thread = new Thread(()-> {
            try {
                HttpRequest request = null;
                String requestBody = objectMapper.writeValueAsString(payload);
                request = HttpRequest.newBuilder()
                        .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                        .uri(URI.create(address))
                        .headers(headers.entrySet().stream()
                                .map(header -> new String[]{header.getKey(), header.getValue()})
                                .flatMap(Arrays::stream)
                                .toArray(String[]::new))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    responseBody = response.body();
                    System.out.println("-------------PUT REQUEST-----------------\n" +
                            ANSI_GREEN + "Status Code: " + response.statusCode() + ANSI_RESET);
                    System.out.println(responseBody);
                } else {
                    System.out.println(ANSI_RED + "PUT request doesn't work" + ANSI_RESET);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();
    }

    @Override
    public void delete(String address, Map<String, String> headers, String payload) {
        Thread thread = new Thread(()-> {
            try {
                HttpRequest request = null;
                request = HttpRequest.newBuilder()
                        .DELETE()
                        .uri(URI.create(address))
                        .headers(headers.entrySet().stream()
                                .map(header -> new String[]{header.getKey(), header.getValue()})
                                .flatMap(Arrays::stream)
                                .toArray(String[]::new))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    responseBody = response.body();
                    System.out.println("-------------DELETE REQUEST-----------------\n" +
                            ANSI_GREEN + "Status Code: " + response.statusCode() + ANSI_RESET);
                    System.out.println(responseBody);
                } else {
                    System.out.println(ANSI_RED + "DELETE request doesn't work" + ANSI_RESET);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();
    }
}
