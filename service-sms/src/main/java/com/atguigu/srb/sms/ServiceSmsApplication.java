package com.atguigu.srb.sms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan({"com.atguigu.srb", "com.atguigu.common"})
public class ServiceSmsApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ServiceSmsApplication.class,args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
