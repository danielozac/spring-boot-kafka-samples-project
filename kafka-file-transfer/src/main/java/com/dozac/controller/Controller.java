package com.dozac.controller;


import com.dozac.producer.ProducerKafka;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/kafka")
public class Controller {

    @Autowired
    Environment environment;

    @Autowired
    ProducerKafka producer;

    @RequestMapping(value="/trigger")
    public String getResult() throws IOException {
        //Obtain a File instance, this could be a csv, xls, zip etc....
        File file = new File("C:\\localFolder\\test.txt");
        //Reads the contents of your file into a byte array
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        //Send the file
        producer.sendMessage(file.getName(), fileContent);

        return environment.getProperty("response.msg");
    }


}
