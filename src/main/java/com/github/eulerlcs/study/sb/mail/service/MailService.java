package com.github.eulerlcs.study.sb.mail.service;

import com.github.eulerlcs.study.sb.mail.config.TelworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@Slf4j
public class MailService {
    private JavaMailSender sender;
    private TelworkProperties telwork;

    @Value("${spring.mail.from}")
    private String from;

    public MailService(JavaMailSender sender, TelworkProperties telwork) {
        this.sender = sender;
        this.telwork = telwork;
    }

    public void sendStartMail() {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setSubject(makeMailSubject());
        msg.setText(makeMailText());

        msg.setFrom(this.from);
        msg.setTo(telwork.getStart().getTo().toArray(String[]::new));
        if (telwork.getStart().getCc() != null) {
            msg.setCc(telwork.getStart().getCc().toArray(String[]::new));
        }
        if (telwork.getStart().getBcc() != null) {
            msg.setBcc(telwork.getStart().getBcc().toArray(String[]::new));
        }

        try {
            log.warn("★★★ before sender.send(msg); at {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            sender.send(msg);
            log.warn("★★★ after  sender.send(msg); at {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        } catch (MailException e) {
            log.error("★★★ after  sender.send(msg); at {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")), e);
        }

    }

    private String makeMailSubject() {
        Map<String, String> params = Map.of(
                "day", LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd")),
                "start_time_real", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        );

        return new StringSubstitutor(params).replace(telwork.getStart().getSubject());
    }

    private String makeMailText() {
        Map<String, String> params = Map.of(
//                "start_time_expect", "09:30",
//                "end_time_expect", "18:30",
                "start_time_real", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
//                "lunchbreak_start_expect", "12:00",
//                "lunchbreak_end_expect", "13:00"
        );

        return new StringSubstitutor(params).replace(telwork.getStart().getText());
    }
}
