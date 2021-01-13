package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void zifu() {
        String var = "北京市海淀区";
        int index = 0;
        System.out.println(var.indexOf("adad"));
    }

}
