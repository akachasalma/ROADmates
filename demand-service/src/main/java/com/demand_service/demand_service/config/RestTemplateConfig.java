package com.demand_service.demand_service.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Configure connection pool for better performance
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50); // Max total connections
        connectionManager.setDefaultMaxPerRoute(20); // Max connections per route

        // Set up timeouts and connection pooling
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000) // 5 seconds to establish connection
                .setSocketTimeout(10000) // 10 seconds for socket reading
                .build();

        // Create an HttpClient with connection pooling and request configuration
        HttpClientBuilder clientBuilder = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig);

        // Create the HttpClient
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        // Create RestTemplate and set a custom error handler
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());

        return restTemplate;
    }

    // Custom error handler to handle specific HTTP status codes or other errors
    private static class CustomResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            // Implement custom error check logic
            return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            // Handle the error as needed (for example, throw an exception or log the error)
            throw new RuntimeException("Error during HTTP request: " + response.getStatusCode());
        }
    }
}
