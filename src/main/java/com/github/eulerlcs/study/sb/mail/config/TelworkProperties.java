package com.github.eulerlcs.study.sb.mail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "telwork.mail")
@PropertySource(value = "file:/data/telwork.yml", encoding = "UTF-8", factory = CommPropertyResourceFactory.class)
@Data
public class TelworkProperties {
    private StartMailTemplate start;

    @Data
    public static class StartMailTemplate {
        private List<String> to;
        private List<String> cc;
        private List<String> bcc;
        private String subject;
        private String text;
    }
}