package org.omnione.did.common.util.lss;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * LSS (Ledger Service Server) HTTP client.
 *
 * <p>Provides methods to interact with LSS server via HTTP REST API.</p>
 * <p>Uses Java 11+ HttpClient for HTTP communication.</p>
 */
public class LssClient {

    private final String baseUrl;
    private final String apiKey;
    private final HttpClient httpClient;
    private final Gson gson;

    /**
     * Create LssClient with configuration.
     *
     * @param config LSS configuration
     */
    public LssClient(LssConfig config) {
        this.baseUrl = config.getBaseUrl();
        this.apiKey = config.getApiKey();
        this.gson = new Gson();
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofMillis(config.getTimeout()))
                .build();
    }

    /**
     * Register DID document.
     *
     * @param requestBody Request body (JSON string or Object)
     * @throws LssException if registration fails
     */
    public void registerDid(Object requestBody) {
        String json = toJson(requestBody);
        HttpRequest request = buildPostRequest("/did-doc", json);
        executeRequest(request, "Failed to register DID");
    }

    /**
     * Update DID document status.
     *
     * @param requestBody Request body containing DID and status
     * @throws LssException if update fails
     */
    public void updateDid(Object requestBody) {
        String json = toJson(requestBody);
        HttpRequest request = buildPutRequest("/did-doc", json);
        executeRequest(request, "Failed to update DID");
    }

    /**
     * Get DID document.
     *
     * @param did DID to retrieve
     * @return DID document as JSON string
     * @throws LssException if retrieval fails
     */
    public String getDid(String did) {
        HttpRequest request = buildGetRequest("/did-doc?did=" + did);
        return executeRequest(request, "Failed to get DID");
    }

    /**
     * Register VC metadata.
     *
     * @param vcMeta VC metadata object
     * @throws LssException if registration fails
     */
    public void registerVcMeta(Object vcMeta) {
        String json = toJson(vcMeta);
        HttpRequest request = buildPostRequest("/vc-meta", json);
        executeRequest(request, "Failed to register VC metadata");
    }

    /**
     * Update VC metadata status.
     *
     * @param requestBody Request body containing VC ID and status
     * @throws LssException if update fails
     */
    public void updateVcMetaStatus(Object requestBody) {
        String json = toJson(requestBody);
        HttpRequest request = buildPatchRequest("/vc-meta", json);
        executeRequest(request, "Failed to update VC metadata status");
    }

    /**
     * Get VC metadata.
     *
     * @param vcId VC ID
     * @return VC metadata as JSON string
     * @throws LssException if retrieval fails
     */
    public String getVcMetaData(String vcId) {
        HttpRequest request = buildGetRequest("/vc-meta?vcId=" + vcId);
        return executeRequest(request, "Failed to get VC metadata");
    }

    /**
     * Register VC schema.
     *
     * @param requestBody Request body containing DID and VC schema
     * @throws LssException if registration fails
     */
    public void registerVcSchema(Object requestBody) {
        String json = toJson(requestBody);
        HttpRequest request = buildPostRequest("/vc-schema", json);
        executeRequest(request, "Failed to register VC schema");
    }

    /**
     * Get VC schema.
     *
     * @param vcSchemaId VC schema ID
     * @return VC schema as JSON string
     * @throws LssException if retrieval fails
     */
    public String getVcSchema(String vcSchemaId) {
        HttpRequest request = buildGetRequest("/vc-schema?schemaId=" + vcSchemaId);
        return executeRequest(request, "Failed to get VC schema");
    }

    /**
     * Build POST request.
     */
    private HttpRequest buildPostRequest(String path, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body));

        if (apiKey != null && !apiKey.isEmpty()) {
            builder.header("X-API-Key", apiKey);
        }

        return builder.build();
    }

    /**
     * Build PUT request.
     */
    private HttpRequest buildPutRequest(String path, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body));

        if (apiKey != null && !apiKey.isEmpty()) {
            builder.header("X-API-Key", apiKey);
        }

        return builder.build();
    }

    /**
     * Build PATCH request.
     */
    private HttpRequest buildPatchRequest(String path, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body));

        if (apiKey != null && !apiKey.isEmpty()) {
            builder.header("X-API-Key", apiKey);
        }

        return builder.build();
    }

    /**
     * Build GET request.
     */
    private HttpRequest buildGetRequest(String path) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Accept", "application/json")
                .GET();

        if (apiKey != null && !apiKey.isEmpty()) {
            builder.header("X-API-Key", apiKey);
        }

        return builder.build();
    }

    /**
     * Execute HTTP request.
     */
    private String executeRequest(HttpRequest request, String errorMessage) {
        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            int statusCode = response.statusCode();

            if (statusCode >= 200 && statusCode < 300) {
                return response.body();
            } else {
                String responseBody = response.body();
                throw new LssException(
                        statusCode,
                        errorMessage + ": HTTP " + statusCode + ", Response: " + responseBody
                );
            }
        } catch (IOException e) {
            throw new LssException("LSS communication failed: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LssException("LSS request interrupted: " + e.getMessage(), e);
        }
    }

    /**
     * Convert object to JSON string.
     */
    private String toJson(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return gson.toJson(obj);
        } catch (JsonSyntaxException e) {
            throw new LssException("Failed to serialize object to JSON: " + e.getMessage(), e);
        }
    }
}
