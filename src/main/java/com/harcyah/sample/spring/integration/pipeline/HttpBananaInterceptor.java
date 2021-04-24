package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
public class HttpBananaInterceptor implements ChannelInterceptor {

    private final HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(20))
        .build();

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        Fruit froot = (Fruit) message.getPayload();

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:8000?bananaId=" + froot.getId()))
            .setHeader("User-Agent", "Banana Bot")
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.error("Unable to notify banana server");
            }
        } catch (IOException | InterruptedException e) {
            log.error("Unable to notify banana server");
        }
    }

}
