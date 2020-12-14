package com.harcyah.sample.spring.integration;

import com.harcyah.sample.spring.integration.domain.Fruit;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface FruitSourceNotifier {

    @Gateway(requestChannel = "fruitSourceChannel")
    void onNewFruit(Fruit fruit);

}
