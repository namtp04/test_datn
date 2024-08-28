package com.example.test_datn;

import com.longnh.annotions.EnableCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EnableCommon
public class TestDatnApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestDatnApplication.class, args);
    }

}
