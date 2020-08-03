package ru.itprogram.controller.integration;


import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.itprogram.Application;

import java.nio.charset.StandardCharsets;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

    private static final String TEST_DATABASE_NAME = "library";
    private static final String TEST_USER = "book";
    private static final String TEST_PASSWORD = "book";

    @LocalServerPort
    private int port;

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:11.1-alpine")
            .withDatabaseName(TEST_DATABASE_NAME)
            .withUsername(TEST_USER)
            .withPassword(TEST_PASSWORD);

    static {
        postgres.start();

        String postgresHost = postgres.getContainerIpAddress();
        int postgresPort = postgres.getMappedPort(5432);
        String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s", postgresHost, postgresPort, TEST_DATABASE_NAME);
        System.setProperty("spring.datasource.url", jdbcUrl);
        System.setProperty("spring.datasource.username", TEST_USER);
        System.setProperty("spring.datasource.password", TEST_PASSWORD);
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final String TEST_DATASOURCE = postgres.getJdbcUrl();

        @Override
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + TEST_DATASOURCE,
                    "spring.datasource.username=" + TEST_USER,
                    "spring.datasource.password=" + TEST_PASSWORD
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @TestConfiguration
    public class TestConfig {

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("localhost")
                    .port(port)
                    .build();

            return new RestTemplateBuilder()
                    .messageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                    .rootUri(uriComponents.toUriString());
        }
    }

}
