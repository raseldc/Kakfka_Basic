/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kafka.sampleKafka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rasel
 */
@RestController
public class Controller {

    private final MessageProducer messageProducer;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final DefaultKafkaConsumerFactory<String, String> defaultKafkaConsumerFactory;

    public Controller(MessageProducer messageProducer, KafkaTemplate<String, String> kafkaTemplate, DefaultKafkaConsumerFactory<String, String> defaultKafkaConsumerFactory) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageProducer = messageProducer;
        this.defaultKafkaConsumerFactory = defaultKafkaConsumerFactory;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam(value = "msg") String msg) {
        messageProducer.sendMessage("testtopic", msg);
        kafkaTemplate.setConsumerFactory(defaultKafkaConsumerFactory);

        ConsumerRecord<String, String> record = kafkaTemplate.receive("testtopic", 0, 0);
        return "Message sent: ";
    }

    @GetMapping("/add")
    public String addTwoValue(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b) {
        String msg = a + "+" + b;
        messageProducer.sendMessage("testtopic", msg);

        return "Message sent Succesfully";
    }

    @GetMapping("/sub")
    public String subTwoValue(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b) {
        String msg = a + "-" + b;
        messageProducer.sendMessage("testtopic", msg);

        return "Message sent Succesfully";
    }

    @GetMapping("/get-send-value")
    @ResponseBody
    public List<Message> sum() throws InterruptedException, ExecutionException {
        kafkaTemplate.setConsumerFactory(defaultKafkaConsumerFactory);
        ConsumerRecord<String, String> record;
        List<Message> getMesasge = new ArrayList<>();
        int offset = 0;
//        StringBuilder response = new StringBuilder();
        while ((record = kafkaTemplate.receive("testtopic", 0, offset)) != null) {
            getMesasge.add(new Message(record.value()));
//            System.out.println(record.value());
//            response.append("{");
//            response.append(record.value());
//            response.append("},");
            offset++;
        }
//        response.setLength(response.length() - 1);
        return getMesasge;
    }
}
