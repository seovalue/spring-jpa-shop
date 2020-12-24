package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        LombokTest lombokTest = new LombokTest();
        lombokTest.setData("Hello");
        String data = lombokTest.getData();
        System.out.println(data);

        SpringApplication.run(JpashopApplication.class, args);
    }

}
