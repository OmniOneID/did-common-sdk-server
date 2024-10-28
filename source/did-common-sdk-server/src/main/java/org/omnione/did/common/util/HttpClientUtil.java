package org.omnione.did.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.HttpClientException;
import org.omnione.did.common.exception.OpenDidException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Utility class for sending HTTP GET and POST requests using HttpClient.
 */
public class HttpClientUtil {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Send an HTTP GET request to the given URL and returns the response body as a String.
     * @param url URL to send the request to
     * @return Response body as a String
     * @throws OpenDidException if the HTTP request fails
     */
    public static String getData(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode < 200 || statusCode >= 300) {
                throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
        }

    }

    /**
     * Send an HTTP POST request to the given URL with the specified request body and returns the response body parsed as the specified class type.
     * @param url URL to send the request to
     * @param responseType Class type to parse the response body to
     * @return Response body parsed as the specified class type
     * @throws OpenDidException if the HTTP request fails
     */
    public static <T> T getData(String url, Class<T> responseType){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return objectMapper.readValue(response.body(), responseType);
            } else {
                throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
            }
        } catch (IOException | InterruptedException e) {
            throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
        }

    }

    /**
     * Send an HTTP POST request to the given URL with the specified request body and returns the response body as a String.
     * @param url URL to send the request to
     * @param requestBody Request body to send
     * @return Response body as a String
     * @throws OpenDidException if the HTTP request fails
     */
    public static String postData(String url, String requestBody) throws IOException, InterruptedException, HttpClientException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode < 200 || statusCode >= 300) {
                throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
        }
    }

    /**
     * Send an HTTP POST request to the given URL with the specified request body and returns the response body parsed as the specified class type.
     *
     * @param url URL to send the request to
     * @param requestBody Request body to send
     * @param responseType Class type to parse the response body to
     * @return Response body parsed as the specified class type
     * @throws OpenDidException if the HTTP request fails
     */
    public static <T> T postData(String url, String requestBody, Class<T> responseType) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return objectMapper.readValue(response.body(), responseType);
            } else {
                throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
            }
        } catch (IOException | InterruptedException e) {
            throw new OpenDidException(ErrorCode.HTTP_CLIENT_ERROR);
        }

    }
}
