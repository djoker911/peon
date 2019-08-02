package com.madking.peon.service.impl;

import com.madking.peon.service.HelloWorldService;
import com.madking.peon.util.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HelloWorldServiceImpl implements HelloWorldService {

//    private final SnowflakeIdGenerator idGenerator;
//
//    @Autowired
//    public HelloWorldServiceImpl(SnowflakeIdGenerator idGenerator){
//        this.idGenerator = idGenerator;
//    }
//    @Autowired
//    SnowflakeIdGenerator idGenerator;


    private final UniqueIdGenerator uniqueIdGenerator;
//
    @Autowired
    public HelloWorldServiceImpl(UniqueIdGenerator uniqueIdGenerator){
        this.uniqueIdGenerator = uniqueIdGenerator;
    }

    @Override
    public String sayHello(String name){
        StringBuilder sb = new StringBuilder();
        sb.append("Hi, ");
        sb.append(name);
        System.out.println(String.valueOf(uniqueIdGenerator.genSnowflakeId()));
        return sb.toString();
    }
}
