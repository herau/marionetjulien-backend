package org.marionetjulien.controllers;

import org.marionetjulien.domains.Form;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by n27 on 2/27/17.
 */
@RestController
public class FormController {

    @PostMapping("/api/form")
    public ResponseEntity postForm(@Valid Form form) {
        System.out.println(form);
        return ResponseEntity.ok().build();
    }

}
