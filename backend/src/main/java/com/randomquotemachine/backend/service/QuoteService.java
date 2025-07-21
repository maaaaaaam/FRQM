package com.randomquotemachine.backend.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.randomquotemachine.backend.model.Quote;
import com.randomquotemachine.backend.repository.QuoteRepository;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteRepository repository;

    public QuoteService(QuoteRepository repo) {
        this.repository = repo;
    }

    @Transactional
    public void importQuotes(List<Quote> quotes) {
        repository.saveAll(quotes);
    }

    @Transactional
    public void resetTable(List<Quote> quotes) {
        repository.deleteAll();
        repository.saveAll(quotes);
    }

    public List<Quote> parseQuotes(InputStream stream) throws IOException, CsvValidationException {

        List<Quote> quotes = new ArrayList<>();

        try (Reader reader = new InputStreamReader(stream);
            CSVReader csvReader = new CSVReader(reader)) {

            String[] line;

            while ((line = csvReader.readNext()) != null) {
                if (line.length == 2) {
                    Quote quote = new Quote();
                    quote.setText(line[0]);
                    quote.setAuthor(line[1]);
                    quotes.add(quote);
                }
            }
        }

        return quotes;
    }
}
