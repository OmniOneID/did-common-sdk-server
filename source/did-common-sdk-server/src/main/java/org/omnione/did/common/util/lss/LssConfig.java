package org.omnione.did.common.util.lss;

/**
 * Configuration class for LSS (Ledger Service Server) connection.
 */
public class LssConfig {
    private String baseUrl;
    private String apiKey;
    private int timeout;

    public LssConfig() {
        this.timeout = 30000;  // Default timeout: 30 seconds
    }

    public LssConfig(String baseUrl) {
        this.baseUrl = baseUrl;
        this.timeout = 30000;
    }

    public LssConfig(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.timeout = 30000;
    }

    public LssConfig(String baseUrl, String apiKey, int timeout) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.timeout = timeout;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
