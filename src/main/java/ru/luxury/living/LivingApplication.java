package ru.luxury.living;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.luxury.living.props.EmailProperties;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties({EmailProperties.class})
public class LivingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LivingApplication.class, args);
    }
}
