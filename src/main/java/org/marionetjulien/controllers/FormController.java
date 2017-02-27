package org.marionetjulien.controllers;

import org.marionetjulien.domains.Form;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by n27 on 2/27/17.
 */
@RestController
public class FormController {

    private final MailSender mailSender;

    public FormController(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping("/api/form")
    public ResponseEntity postForm(@Valid @RequestBody Form form) {
        System.out.println(form);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("leboulanger.aurelien@gmail.com");
        mailMessage.setSubject("Confirmation présence mariage");

        mailSender.send(mailMessage);

        return ResponseEntity.ok().build();
    }

}
