package com.Marvelchallenge.marvelAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterController {

    @GetMapping("/characters")
    public ResponseEntity<List> getCharacters() {
        return (ResponseEntity<List>) ResponseEntity
                .status(HttpStatus.OK);
                //.body(this.repo.findAll());
    }
}
