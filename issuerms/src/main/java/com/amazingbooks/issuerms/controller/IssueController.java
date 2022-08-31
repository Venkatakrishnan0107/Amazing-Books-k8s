package com.amazingbooks.issuerms.controller;

import com.amazingbooks.issuerms.repository.IssueRepository;
import com.amazingbooks.issuerms.entity.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/issue")
public class IssueController {

        @Value("${book.service.name}")
        private String bookService;

        @Autowired
        RestTemplate restTemplate;
        @Autowired
        IssueRepository issueRepository;

        private static final Logger LOGGER = LoggerFactory.getLogger(IssueController.class);


        @GetMapping("/fetchAvailableCopies/{isbn}")
        public ResponseEntity getBookAvailability(@PathVariable String isbn) throws IOException {
            String source = ".getBookAvailability()";
            LOGGER.info(source+" "+isbn+" Starting");
            ResponseEntity<String> entity = null;
           try {
                entity = restTemplate.getForEntity(bookService+"/books/checkBookAvailability/" + isbn, String.class);
            } catch (HttpStatusCodeException ex) {
               LOGGER.info(source+" Ending");
                return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
            }
            LOGGER.info(source+" Ending");
            return entity;

        }

        @PostMapping("/issueBooks")
        public ResponseEntity issueBooks(@RequestBody Issue issue){
            String source = ".getBookAvailability()";
            LOGGER.info(source+" Starting");
            ResponseEntity<String> entity = null;
            try {
                entity = restTemplate.postForEntity(bookService+"/books/issueBook/" + issue.getIsbn() + "/" + issue.getNoOfCopies(), issue, String.class);
                issueRepository.save(issue);
            }catch (HttpStatusCodeException ex) {
                LOGGER.info(source+" Ending");
                return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
            }
            LOGGER.info(source+" Ending");
            return entity;
        }
}
