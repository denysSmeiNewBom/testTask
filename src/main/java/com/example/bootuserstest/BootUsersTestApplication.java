package com.example.bootuserstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BootUsersTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootUsersTestApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {

            User maria = new User(
                    "Maria",
                    "Jones",
                    "380663662505",
                    "maria.jones@gmail.com",
                    "Telegram",
                    LocalDateTime.now()
            );

            User tom = new User(
                    "Tom",
                    "Tomson",
                    "380663662506",
                    "tom.tomson@gmail.com",
                    "Telegram",
                    LocalDateTime.now()
            );

            User ihor = new User(
                    "Ihor",
                    "Ivanuk",
                    "380663662507",
                    "ihor.ivanuk@gmail.com",
                    "Telegram",
                    LocalDateTime.now()
            );
            userRepository.saveAll(List.of(maria, tom, ihor));

            userRepository
                    .findStudentByEmail("ihor.ivanuk@gmail.com")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with email ihor.ivanuk@gmail.com not found"));

            userRepository
                    .findStudentByPhoneNumber("380663662506")
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Student with phone number 380663662506 not found"));


        };
    }*/
}
