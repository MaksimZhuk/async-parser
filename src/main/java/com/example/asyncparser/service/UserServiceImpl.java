package com.example.asyncparser.service;

import com.example.asyncparser.dto.UserResponseDto;
import com.example.asyncparser.util.JsonHelper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class UserServiceImpl implements UserService {

    private HttpClient client = HttpClient.newBuilder().build();

    private final String aqifyURITemplate = "https://api.agify.io/?name=%s";
    private final int TIMEOUT = 5_000;

    @Override
    public List<UserResponseDto> getUsers(List<String> names) {

        List<URI> uris = names.stream().map(x -> {
            try {
                return new URI(String.format(aqifyURITemplate, x));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                throw new RuntimeException(String.format("URI creation problem with %s", x));
            }
        }).collect(Collectors.toList());

        List<CompletableFuture<UserResponseDto>> futureList = uris.stream()
                .map(target -> client
                        .sendAsync(
                                HttpRequest.newBuilder(target).GET().build(),
                                HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body).thenApply(JsonHelper::readValueJackson)).collect(Collectors.toList());


        return futureList.stream().parallel().map(x -> {
            try {
                return x.get(TIMEOUT, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
