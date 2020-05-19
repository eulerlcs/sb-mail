package com.github.eulerlcs.study.sb.mail.job;

import com.github.eulerlcs.study.sb.mail.service.MailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailJob {
    private MailService mailService;

    public MailJob(MailService mailService) {
        this.mailService = mailService;
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void sendStartMail() {
        mailService.sendStartMail();
    }
}
