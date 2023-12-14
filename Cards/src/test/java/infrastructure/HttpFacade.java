package infrastructure;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpFacade {
    public static HttpResponse get(String url, Map<String, String> headers,
                                   Map<String, String> queryParams) throws IOException {
        // create connection
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(HttpMethod.GET.getMethod());
        // set headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        // set query parameters
        if (queryParams != null) {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            url += "?" + queryString;
        }
        // send request and handle response
        int status = connection.getResponseCode();
        String body = new String(connection.getInputStream().readAllBytes());
        Map<String, String> responseHeaders =
                connection.getHeaderFields().entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                                entry.getValue().get(0)));
        return new HttpResponse(status,responseHeaders,body);

    }

    public static HttpResponse post(String url, Map<String, String> headers,
                                    Map<String, String> queryParams, String requestBody) throws IOException {
        // create connection
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(HttpMethod.POST.getMethod());

        // set headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // set query parameters
        if (queryParams != null) {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            url += "?" + queryString;

        }

        // enable input/output streams for POST
        connection.setDoOutput(true);

        // write the request body, if provided
        if (requestBody != null && !requestBody.isEmpty()) {
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        // send request and handle response
        int status = connection.getResponseCode();
        String body = new String(connection.getInputStream().readAllBytes());
        Map<String, String> responseHeaders = connection.getHeaderFields().entrySet()
                .stream()
                .filter(entry -> entry.getKey() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(0)));

        return new HttpResponse(status, responseHeaders, body);
    }
}

