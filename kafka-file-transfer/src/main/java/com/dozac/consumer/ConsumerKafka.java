package com.dozac.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

@Slf4j
public class ConsumerKafka {

	 @KafkaListener(id = "consumer", topics = {"${kafka.topic}"} )
	 public void onMessage(ConsumerRecord<String, Map> record) {
          byte[] b = (byte[]) record.value().get(record.key());
		  writeBytesToFile(b);
	 }

	private static void writeBytesToFile(byte[] bFile) {

		File file = new File("C:\\destinationFolder\\myFile.txt");
		try {

			OutputStream os = new FileOutputStream(file);
			os.write(bFile);
			log.info("Write bytes to file.");
			os.close();
		} catch (Exception e) {
			log.error("An Error occurred", e);
		}

	}
}
