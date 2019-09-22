package com.dozac.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Custom serializer
 */
@Slf4j
public class FileMapSerializer implements Serializer<Map<?,?>> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Map data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(data);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            log.error("Error:" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                log.info("ignore close exception");
            }
            try {
                bos.close();
            } catch (IOException ex) {
                log.info("ignore close exception");
            }
        }
        return bytes;
    }
}
