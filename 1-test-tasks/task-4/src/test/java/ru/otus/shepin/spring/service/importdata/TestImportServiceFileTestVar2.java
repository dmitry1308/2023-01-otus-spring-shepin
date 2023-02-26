package ru.otus.shepin.spring.service.importdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shepin.spring.config.AppTestProps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestImportServiceFileTestVar2 {
    @Autowired
    private DataImportService importService;


    @Configuration
    @Import({TestImportServiceFile.class})
    public static class TestConfig {
        @Bean
        public AppTestProps appTestProps(){
            return new AppTestProps("Questions.csv", Locale.ENGLISH);
        }
    }
    @Test
    void when_call_import_data_then_return_right_count_questions() throws IOException {
        assertThat(importService.importData().size()).isEqualTo(5);
    }

    @Test
    void when_call_import_data_then_return_right_question() throws IOException {
        assertThat(importService.importData().get(0).getQuestion()).isEqualTo("What is the biggest planet in the solar system?");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void when_call_import_data_then_return_ArrayList() throws IOException {
        assertThat(importService.importData()).isExactlyInstanceOf(ArrayList.class);
    }
}