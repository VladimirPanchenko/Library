package ru.itprogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
public class LibraryLocaleMessageSourceConfig {

    @Value("${spring.mvc.locale:en-EN}")
    private String locale;

    @PostConstruct
    private void init() {
        LocaleContextHolder.setDefaultLocale(Locale.forLanguageTag(locale.replace('_', '-')));
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
