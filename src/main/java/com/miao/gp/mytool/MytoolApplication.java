package com.miao.gp.mytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 开启定时任务功能

public class MytoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(MytoolApplication.class, args);
    }

}
