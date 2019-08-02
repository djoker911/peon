package com.madking.peon.util;


public class SnowflakeIdGenerator {

    private final static long beginTs = 1483200000000L;

    private long lastTs = 0L;

    private long processId;
    private int processIdBits = 10;

    private long sequence = 0L;
    private int sequenceBits = 12;

    public SnowflakeIdGenerator(long processId) {
        System.out.println(String.valueOf(processId));
        System.out.println(String.valueOf(((1 << 10) - 1)));
        if (processId > ((1 << processIdBits) - 1)) {
            processId = processId%((1 << processIdBits)-1);
        }
        System.out.println(processId);
        this.processId = processId;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long ts = timeGen();
        if (ts < lastTs) {
            throw new RuntimeException("wrong time stamp order");
        }
        if (ts == lastTs) {
            sequence = (sequence + 1) & ((1 << sequenceBits) - 1);
            if (sequence == 0) {
                ts = nextTs(lastTs);
            }
        } else {
            sequence = 0L;
        }
        lastTs = ts;
        return ((ts - beginTs) << (processIdBits + sequenceBits)) | (processId << sequenceBits)
                | sequence;
    }

    protected long nextTs(long lastTs) {
        long ts = timeGen();
        while (ts <= lastTs) {
            ts = timeGen();
        }
        return ts;
    }

}
