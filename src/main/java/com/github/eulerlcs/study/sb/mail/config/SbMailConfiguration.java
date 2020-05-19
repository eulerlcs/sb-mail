package com.github.eulerlcs.study.sb.mail.config;

import com.github.eulerlcs.study.sb.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@Slf4j
public class SbMailConfiguration {
    @Bean
    public ApplicationRunner startMailRunner(MailService mailService) {
        return args -> {
            mailService.sendStartMail();

            log.warn("good morning! The start mail have sent at {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        };
    }
}
