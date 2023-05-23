package com.yvan.beekeeper.application;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RecordMessageConverter converter;

    @ApiOperation(value = "Envoie un message au topic Kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message envoyé avec succès"),
            @ApiResponse(code = 500, message = "Erreur interne du serveur")
    })
    public void sendMessage(String message, String topic) {
        kafkaTemplate.send(topic, message);
    }

    //Methode that consumer the topic "house" and publish the id of the object House in the topic "houseID"//
    @ApiOperation(value = "Envoie un message au topic Kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message envoyé avec succès"),
            @ApiResponse(code = 500, message = "Erreur interne du serveur")
    })
    public void receiveAndsendMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        if (isHouse(topic)) {
            kafkaTemplate.send("houseID", message);
        }
    }
    private Boolean isHouse(String topic) {
        return topic.equals("house");
    }

    //Methode that consumer topic SCH and send a HTTP post to the url http://localhost:8080/api/house//
    @ApiOperation(value = "Envoie un message au topic Kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message envoyé avec succès"),
            @ApiResponse(code = 500, message = "Erreur interne du serveur")
    })
    public void receiveAndsendHTTP(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        if (isSCH(topic)) {
            //TODO
        }
    }
    private Boolean isSCH(String topic) {
        return topic.equals("SCH");
    }

}
