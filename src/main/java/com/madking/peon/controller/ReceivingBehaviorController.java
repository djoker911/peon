package com.madking.peon.controller;

import com.madking.peon.config.ProducerConfig;
import com.madking.peon.dto.APIResponseDTO;
import com.madking.peon.dto.ReceivedUserDTO;
import com.madking.peon.pojo.UserBehaviorPOJO;
import com.madking.peon.util.TopicSender;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import org.springframework.amqp.core.AmqpTemplate;



@Controller
@Api(value = "ReceivingBehavior", description = "API ReceivingBehavior", tags = { "Receiving Behavior" })
public class ReceivingBehaviorController {

    @Autowired
    private TopicSender topicSender;

    @GetMapping("/wtfla")
    public @ResponseBody APIResponseDTO topicTest() {
        APIResponseDTO result = new APIResponseDTO();
        topicSender.send3();
        result.setSuccess(Boolean.TRUE);
        result.setMsg("Success");
        result.setStatus(HttpStatus.OK.value());
        return result;
    }


////    private static final ProducerConfig producer = new ProducerConfig("testla","ha.q1", "q1");
////    private static final ProducerConfig producer2nd = new ProducerConfig("testla","q2", "q2.#.event");
//
//    @Autowired
//    ProducerConfig producer;
//
//    @GetMapping("/access/{name}")
//    public @ResponseBody
//    APIResponseDTO getRawLog(@PathVariable("name") String name) {
//        APIResponseDTO result = new APIResponseDTO();
//        producer.setExchangeName("testla");
//        producer.setQueueName("ha.q1");
//        producer.setRoutingKey("q1");
//        producer.send("testla", "q1", name);
//        result.setStatus(HttpStatus.OK.value());
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("ok");
//        return result;
//    }
//
//
//    @GetMapping("/new-route/{name}")
//    public @ResponseBody
//    APIResponseDTO getWhatever(@PathVariable("name") String name) {
//        APIResponseDTO result = new APIResponseDTO();
//        String routingKey = "q2."+name+".event";
//        producer.setExchangeName("testla");
//        producer.setQueueName("q2");
//        producer.setRoutingKey("q2.#.event");
//        producer.send("testla", routingKey, name);
//        result.setStatus(HttpStatus.OK.value());
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("ok");
//        return result;
//    }

//    @PostMapping("/click")
//    public @ResponseBody
//    APIResponseDTO sendClickEvent(@RequestBody ReceivedUserDTO receivedUserDTO) {
//        APIResponseDTO result = new APIResponseDTO();
//        UserBehaviorPOJO userBehaviorPOJO = new UserBehaviorPOJO();
//        userBehaviorPOJO.setId(receivedUserDTO.getId());
//        userBehaviorPOJO.setName(receivedUserDTO.getName());
//        userBehaviorPOJO.setAction("click");
//        userBehaviorPOJO.setCtime(new Date());
//        String routingKey = "q2.click."+String.valueOf(receivedUserDTO.getId())+".event";
//        producer2nd.send("testla", routingKey, userBehaviorPOJO);
//        result.setStatus(HttpStatus.OK.value());
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("ok");
//        return result;
//    }
//
//
//    @PostMapping("/display")
//    public @ResponseBody
//    APIResponseDTO sendDisplayEvent(@RequestBody ReceivedUserDTO receivedUserDTO) {
//        APIResponseDTO result = new APIResponseDTO();
//        UserBehaviorPOJO userBehaviorPOJO = new UserBehaviorPOJO();
//        userBehaviorPOJO.setId(receivedUserDTO.getId());
//        userBehaviorPOJO.setName(receivedUserDTO.getName());
//        userBehaviorPOJO.setAction("display");
//        userBehaviorPOJO.setCtime(new Date());
//        String routingKey = "q2.display."+String.valueOf(receivedUserDTO.getId())+".event";
//        producer2nd.send("testla", routingKey, userBehaviorPOJO);
//        result.setStatus(HttpStatus.OK.value());
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("ok");
//        return result;
//    }



}
