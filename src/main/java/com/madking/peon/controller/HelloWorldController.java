package com.madking.peon.controller;


import com.madking.peon.dto.APIResponseDTO;
import com.madking.peon.service.HelloWorldService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "Hello", description = "API example", tags = { "Hello" })
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService){
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/hi/{name}")
    public @ResponseBody APIResponseDTO sayhi(@PathVariable("name") String name) {
        APIResponseDTO result = new APIResponseDTO();
        String response = helloWorldService.sayHello(name);
        result.setStatus(HttpStatus.OK.value());
        result.setData(response);
        result.setSuccess(Boolean.TRUE);
        result.setMsg("ok");
        return result;
    }
}
