package com.dh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.dh.server.SocketServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.dh.dao")
@EnableScheduling
public class Application {
    public static void main(String[] args) {

//        SpringApplication.run(Application.class,args);

        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class,args);
        SocketServer.setApplicationContext(applicationContext);

    }
}
