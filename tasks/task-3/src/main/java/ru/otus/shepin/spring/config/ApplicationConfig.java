package ru.otus.shepin.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AppTestProps.class})
public class ApplicationConfig {

}
