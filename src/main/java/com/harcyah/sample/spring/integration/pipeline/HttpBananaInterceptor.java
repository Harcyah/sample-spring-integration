package com.harcyah.sample.spring.integration.pipeline;

import com.harcyah.sample.spring.integration.domain.Fruit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import java.io.IOException;

@Slf4j
public class HttpBananaInterceptor implements ChannelInterceptor {

    private final OkHttpClient httpClient = new OkHttpClient();

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        Fruit froot = (Fruit) message.getPayload();

        Request request = new Request.Builder()
            .url("http://localhost:8000?bananaId=" + froot.getId())
            .addHeader("User-Agent", "Banana Bot")
            .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

        } catch (Exception e) {
            log.error("Unable to notify banana server");
        }
    }

}
