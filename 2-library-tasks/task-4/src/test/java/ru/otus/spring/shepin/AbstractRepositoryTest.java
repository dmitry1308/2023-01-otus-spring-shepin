package ru.otus.spring.shepin;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.shepin"})
//@Import(RawResultPrinterImpl.class)
public abstract class AbstractRepositoryTest {
}
