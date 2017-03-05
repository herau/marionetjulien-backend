package org.marionetjulien.controllers;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;
import org.marionetjulien.domains.Form;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.io.IOException;

/**
 * Created by n27 on 2/27/17.
 */
@CrossOrigin
@RestController
@Slf4j
public class FormController {

    @Value("${email.to}")
    private String emailTo;

    @PostMapping("/api/form")
    public ResponseEntity postForm(@Valid @RequestBody Form form) {
        Email from = new Email("marionetjulien@example.fr");
        String subject = "Confirmation de présence au mariage \uD83D\uDC92 \uD83D\uDC4C";
        Email to = new Email(emailTo);

        String value =
                "Hello, <br/><strong>" + form.getFirstname() + " " +  form.getLastname() + "</strong> a répondu sur le site"
                + "<br/>"
                + "Email : " + form.getEmail()
                + "<br/>"
                + "présence : " + form.getPresent()
                + "<br/>"
                + "Nombre de personnes : " + form.getPeople()
                + "<br/>"
                + "Nombre d'enfants : " + form.getChildren();

        Content content = new Content("text/html", value);

        Mail mail = new Mail(from, subject, to, content);

        String apiKey = System.getenv("SENDGRID_API_KEY");
        log.info("SENDGRID API KEY is OK: {}", apiKey != null);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            log.info("mail sent {}", response.statusCode);
        } catch (IOException ex) {
            log.error("Unable to send the mail", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }

}
