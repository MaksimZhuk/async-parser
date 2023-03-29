package com.example.asyncparser.service;

import com.example.asyncparser.dto.UserGeoDataDTO;
import com.example.asyncparser.dto.UserResponseDTO;
import com.example.asyncparser.util.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserGeoServiceImpl implements UserGeoService {
    @Autowired
    private HttpClient client;

    @Value("${HTTP_USERS_GEO_TEMPLATE}")
    private String userGeoDataTemplate;
    @Value("${HTTP_REQUEST_TIMEOUT}")
    private int TIMEOUT;
    @Override
    public UserGeoDataDTO getUserGeoData(String ip) {
        URI uri;
        try {
            uri = new URI(String.format(userGeoDataTemplate, ip));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String body;
        try {
            body = client.send(HttpRequest.newBuilder(uri).GET().build(), HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CompletableFuture<UserGeoDataDTO> future = client.sendAsync(HttpRequest.newBuilder(uri).GET().build(), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(JsonHelper::getUserGeoDataDTO);
        try {
            return future.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
