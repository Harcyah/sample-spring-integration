package com.harcyah.sample.spring.integration;

import com.harcyah.sample.spring.integration.domain.Fruit;
import com.harcyah.sample.spring.integration.domain.Race;
import com.harcyah.sample.spring.integration.pipeline.BananaColorizer;
import com.harcyah.sample.spring.integration.pipeline.BananaConverter;
import com.harcyah.sample.spring.integration.pipeline.BananaTwister;
import com.harcyah.sample.spring.integration.pipeline.HttpBananaInterceptor;
import com.harcyah.sample.spring.integration.pipeline.NoFuckingKiwis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

@Configuration
@EnableIntegration
public class SampleSpringIntegrationContext {

    @Bean
    public MessageChannel isBananaChannel() {
        return MessageChannels.direct()
            .interceptor(httpNotifierInterceptor())
            .get();
    }

    private ChannelInterceptor httpNotifierInterceptor() {
        return new HttpBananaInterceptor();
    }

    @Bean
    public MessageChannel isNoBananaChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow isBananaFlow() {
        return IntegrationFlows.from("isBananaChannel")
            .transform(bananaColorizer())
            .transform(bananaTwister())
            .channel(successChannel())
            .get();
    }

    @Bean
    public IntegrationFlow isNoBananaFlow() {
        return IntegrationFlows.from("isNoBananaChannel")
            .transform(bananaConverter())
            .channel(successChannel())
            .get();
    }

    @Bean
    public IntegrationFlow bananaRedressorIntegrationFlow() {
        return IntegrationFlows.from("fruitSourceChannel")
            .filter(noFuckingKiwis())
            .<Fruit, Boolean> route(f -> f.getRace() == Race.BANANA, m -> m
                .channelMapping(false, "isBananaChannel")
                .channelMapping(true, "isNoBananaChannel")
            )
            .get();
    }

    private BananaConverter bananaConverter() {
        return new BananaConverter();
    }

    private GenericSelector<Fruit> noFuckingKiwis() {
        return new NoFuckingKiwis();
    }

    private BananaColorizer bananaColorizer() {
        return new BananaColorizer();
    }

    private BananaTwister bananaTwister() {
        return new BananaTwister();
    }

    @Bean
    public QueueChannel successChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    @ConditionalOnProperty("bananas.runner")
    public SampleSpringIntegrationRunner fruitCommandLineRunner(FruitSourceNotifier notifier) {
        return new SampleSpringIntegrationRunner(notifier);
    }

}



