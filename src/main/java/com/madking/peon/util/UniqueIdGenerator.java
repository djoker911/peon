package com.madking.peon.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;


@Component
public class UniqueIdGenerator {


    public Long genSnowflakeId(){
        Long result = 0L;
        try {
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(getSnowflakeSeed());
            result = idGenerator.nextId();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    private Long getSnowflakeSeed(){
        long min = 0L;
        long max = 1000000L;
        Random random = new Random();
        return random.nextLong() % (max - min) + max;
    }
}
