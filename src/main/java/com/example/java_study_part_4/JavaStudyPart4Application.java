package com.example.java_study_part_4;

import com.example.java_study_part_4.Service.LoginService;
import com.example.java_study_part_4.Service.LoginServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@Configuration
@SpringBootApplication
@EnableJpaRepositories()
@EntityScan()

public class JavaStudyPart4Application {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(JavaStudyPart4Application.class, args);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу");
        String s = scanner.next();
        scanner.close();

        LoginService service = context.getBean(LoginServiceImpl.class);
        service.save(s);
        context.close();
    }

}
