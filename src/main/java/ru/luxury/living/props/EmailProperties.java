package ru.luxury.living.props;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@Getter
@ConfigurationProperties(prefix = "email.api")
public class EmailProperties {

    private final String key;

    @ConstructorBinding
    public EmailProperties(String key) {
        this.key = key;
    }
}
