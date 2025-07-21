package com.randomquotemachine.backend.controller;

import com.randomquotemachine.backend.model.Quote;
import com.randomquotemachine.backend.repository.QuoteRepository;
import com.randomquotemachine.backend.service.QuoteService;

import java.util.Map;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/quotes")
public class QuoteController {

    private final QuoteRepository repository;
    private final QuoteService quoteService;
    

    @Autowired
    public QuoteController(QuoteRepository repo, QuoteService service) {
        this.repository = repo;
        this.quoteService = service;
    }

    //  GET random quote query
    @GetMapping
    @Async
    public CompletableFuture<ResponseEntity<?>> getRandomQuote() {
        try {

            long count = repository.count();
            int index = new Random().nextInt((int) count);
            
            return CompletableFuture.completedFuture(ResponseEntity.ok(repository.findQuoteByOffset(index)));

        } catch (Exception e) {

            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(Map.of("message", "Fetching the quote failed:" + e.getMessage())));

        }
    }

    //  GET all quotes query
    @GetMapping("/all")
    @Async
    public CompletableFuture<ResponseEntity<?>> getAllQuotes() {
        try {

            List<Quote> quotes = repository.findAll();
            return CompletableFuture.completedFuture(ResponseEntity.ok(quotes));

        } catch (Exception e) {

            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(Map.of("message", "Fetching all the quotes failed:" + e.getMessage())));

        }
    }

    //  POST quotes import query
    @PostMapping
    @Async
    public CompletableFuture<ResponseEntity<?>> importQuotes(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(Map.of("message", "Empty or no file.")));

        try (InputStream stream = file.getInputStream()) {

            List<Quote> quotes = quoteService.parseQuotes(stream);

            if (quotes.isEmpty()) throw new IllegalArgumentException("No valid quotes found in file.");

            quoteService.importQuotes(quotes);

            return CompletableFuture.completedFuture(ResponseEntity.ok(Map.of("message", quotes.size() + " quote(s) imported.")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(Map.of("message", "File reading error: " + e.getMessage())));
        }
    }

    //  POST table reinit query
    @PostMapping("/reinit")
    @Async
    public CompletableFuture<ResponseEntity<Void>> reinitTable() {
        try {

            ClassLoader classLoader = getClass().getClassLoader();

            try (InputStream stream = classLoader.getResourceAsStream("10InitialQuotesForReinit.csv")) {
                quoteService.resetTable(quoteService.parseQuotes(stream));
            }

            return CompletableFuture.completedFuture(ResponseEntity.ok().build());

        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }
    }

}