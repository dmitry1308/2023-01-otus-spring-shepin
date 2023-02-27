package ru.otus.shepin.spring.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "test")
@Getter
@Setter
@AllArgsConstructor
public class AppTestProps {
    private String fileName;
    private Locale locale;
}
