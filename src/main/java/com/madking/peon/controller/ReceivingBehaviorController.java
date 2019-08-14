package com.madking.peon.controller;

import com.madking.peon.dto.APIResponseDTO;
import com.madking.peon.dto.EventLogDTO;
import com.madking.peon.dto.RawLogDTO;
import com.madking.peon.helper.rabbit.publisher.EventLogPublisher;
import com.madking.peon.helper.rabbit.publisher.RawLogPublisher;
import com.madking.peon.helper.rabbit.publisher.Sender;
import com.madking.peon.service.ReceivingBehaviorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Api(value = "ReceivingBehavior", description = "API ReceivingBehavior", tags = { "Receiving Behavior" })
public class ReceivingBehaviorController {


    private ReceivingBehaviorService receivingBehaviorService;

    @Autowired
    public ReceivingBehaviorController(ReceivingBehaviorService receivingBehaviorService){
        this.receivingBehaviorService = receivingBehaviorService;
    }


    @GetMapping("/heartbeat")
    public @ResponseBody APIResponseDTO processHeartbeat(@RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam String path) {
        APIResponseDTO result = new APIResponseDTO();
        Boolean publishResult = receivingBehaviorService.publishRawLog(etuid, domain, groupId, path);
        if(publishResult.equals(Boolean.TRUE)){
            result.setSuccess(Boolean.TRUE);
            result.setMsg("Success");
            result.setStatus(HttpStatus.OK.value());
        }
        return result;
    }

    @GetMapping("/display")
    public @ResponseBody APIResponseDTO processDisplay(@RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam Integer widgetId) {
        APIResponseDTO result = new APIResponseDTO();
        Boolean publishResult = receivingBehaviorService.publishDisplayEvent(etuid, domain, groupId, widgetId);
        if(publishResult.equals(Boolean.TRUE)){
            result.setSuccess(Boolean.TRUE);
            result.setMsg("Success");
            result.setStatus(HttpStatus.OK.value());
        }
        return result;
    }

    @GetMapping("/click")
    public @ResponseBody APIResponseDTO processClick(@RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam Integer widgetId) {
        APIResponseDTO result = new APIResponseDTO();
        Boolean publishResult = receivingBehaviorService.publishClickEvent(etuid, domain, groupId, widgetId);
        if(publishResult.equals(Boolean.TRUE)){
            result.setSuccess(Boolean.TRUE);
            result.setMsg("Success");
            result.setStatus(HttpStatus.OK.value());
        }
        return result;
    }


//    @Autowired
//    private Sender topicSender;
//
//    @Autowired
//    private RawLogPublisher rawLogPublisher;
//
//    @Autowired
//    private EventLogPublisher eventLogPublisher;
//
//    @GetMapping("/wtfla")
//    public @ResponseBody APIResponseDTO topicTest() {
//        APIResponseDTO result = new APIResponseDTO();
//        topicSender.send2();
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("Success");
//        result.setStatus(HttpStatus.OK.value());
//        return result;
//    }
//
//    @GetMapping("/wtftwo")
//    public @ResponseBody APIResponseDTO doWtf(@RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam String path) {
//        APIResponseDTO result = new APIResponseDTO();
//        RawLogDTO rawLogDTO = new RawLogDTO(etuid, domain, groupId, path);
//        rawLogPublisher.publish(rawLogDTO);
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("Success");
//        result.setStatus(HttpStatus.OK.value());
//        return result;
//    }
//
//    @GetMapping("/wtf/display")
//    public @ResponseBody APIResponseDTO doWtfDisplay(@RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam Integer widgetId) {
//        APIResponseDTO result = new APIResponseDTO();
//        EventLogDTO eventLogDTO = new EventLogDTO(etuid, domain, groupId, widgetId, "display");
//        eventLogPublisher.publish(eventLogDTO);
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("Success");
//        result.setStatus(HttpStatus.OK.value());
//        return result;
//    }
//
//    @GetMapping("/wtf/click")
//    public @ResponseBody APIResponseDTO doWtfClick(@RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam Integer widgetId) {
//        APIResponseDTO result = new APIResponseDTO();
//        EventLogDTO eventLogDTO = new EventLogDTO(etuid, domain, groupId, widgetId, "click");
//        eventLogPublisher.publish(eventLogDTO);
//        result.setSuccess(Boolean.TRUE);
//        result.setMsg("Success");
//        result.setStatus(HttpStatus.OK.value());
//        return result;
//    }

//    @RequestParam String etuid, @RequestParam String domain, @RequestParam String groupId, @RequestParam String path

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
