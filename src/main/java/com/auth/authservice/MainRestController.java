package com.auth.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MainRestController {

    @Autowired
    CredentialRepository credentialRepository;
    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody Credential credential)  {

        credentialRepository.save(credential);

        return new ResponseEntity<>("New Signup Successful", HttpStatus.OK);
    }

    @PostMapping("signin")
    public ResponseEntity<String> signin(@RequestBody Credential credential)  {

        credentialRepository.save(credential);

        return new ResponseEntity<>("Sign in Successful", HttpStatus.OK);
    }
}
