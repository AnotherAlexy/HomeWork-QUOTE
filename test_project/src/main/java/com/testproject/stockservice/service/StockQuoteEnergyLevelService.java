package com.testproject.stockservice.service;

import com.testproject.stockservice.model.dto.QuoteDto;
import com.testproject.stockservice.model.dto.QuoteElvlDto;
import com.testproject.stockservice.model.entity.QuoteElvl;
import com.testproject.stockservice.repository.QuotesEnergyLevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockQuoteEnergyLevelService {
    @Autowired
    private QuotesEnergyLevelRepository quotesEnergyLevelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void updateQuotesElvl(final QuoteDto quote) {
        if (quote.getBid() == null && quote.getAsk() == null) {
            return;
        }

        final Optional<QuoteElvl> data = quotesEnergyLevelRepository.findById(quote.getIsin());

        if (data.isPresent()) {
            synchronized (this) {
                final QuoteElvl quoteElvl = data.get();
                // RULE 1
                if (quote.getBid() != null && quote.getBid() > quoteElvl.getElvl()) {
                    quoteElvl.setElvl(quote.getBid());
                    quotesEnergyLevelRepository.save(quoteElvl);
                    // RULE 2
                } else if (quote.getAsk() != null && quote.getAsk() < quoteElvl.getElvl()) {
                    quoteElvl.setElvl(quote.getAsk());
                    quotesEnergyLevelRepository.save(quoteElvl);
                }
            }
        } else {
            try {
                // RULE 3
                if (quote.getBid() != null) {
                    final QuoteElvl newQuoteLevel = new QuoteElvl(quote.getIsin(), quote.getBid());
                    quotesEnergyLevelRepository.save(newQuoteLevel);
                // RULE 4
                } else if (quote.getAsk() != null) {
                    final QuoteElvl newQuoteLevel = new QuoteElvl(quote.getIsin(), quote.getAsk());
                    quotesEnergyLevelRepository.save(newQuoteLevel);
                }
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                updateQuotesElvl(quote);
            }
        }
    }

    public List<QuoteElvlDto> getAllElvl() {
        return quotesEnergyLevelRepository.findAll()
                .stream()
                .map(dataBaseQuote -> modelMapper.map(dataBaseQuote, QuoteElvlDto.class))
                .collect(Collectors.toList());
    }

    public QuoteElvlDto getOneElvl(final String isin) {
        return modelMapper.map(quotesEnergyLevelRepository.findById(isin)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote energy level not found")), QuoteElvlDto.class);
    }
}
