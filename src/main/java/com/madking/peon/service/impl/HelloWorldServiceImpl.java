package com.madking.peon.service.impl;

import com.madking.peon.service.HelloWorldService;
import org.springframework.stereotype.Service;


@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String sayHello(String name){
        StringBuilder sb = new StringBuilder();
        sb.append("Hi, ");
        sb.append(name);
        return sb.toString();
    }
}
