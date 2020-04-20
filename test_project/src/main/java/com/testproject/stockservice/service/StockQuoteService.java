package com.testproject.stockservice.service;

import com.testproject.stockservice.model.dto.QuoteDto;
import com.testproject.stockservice.model.entity.Quote;
import com.testproject.stockservice.repository.QuotesHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockQuoteService {
    @Autowired
    private QuotesHistoryRepository quotesHistoryRepository;

    @Autowired
    private StockQuoteEnergyLevelService stockQuoteEnergyLevelService;

    @Autowired
    private ModelMapper modelMapper;

    public QuoteDto postQuotes(final QuoteDto inputQuote) {
        final Quote databaseQuote = modelMapper.map(inputQuote, Quote.class);

        Quote result = quotesHistoryRepository.save(databaseQuote);

        stockQuoteEnergyLevelService.updateQuotesElvl(inputQuote);

        QuoteDto resultOutput = modelMapper.map(result, QuoteDto.class);

        return resultOutput;
    }
}
