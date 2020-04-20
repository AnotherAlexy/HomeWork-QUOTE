package com.testproject.stockservice.controller;

import com.testproject.stockservice.model.dto.QuoteDto;
import com.testproject.stockservice.model.dto.QuoteElvlDto;
import com.testproject.stockservice.service.StockQuoteEnergyLevelService;
import com.testproject.stockservice.service.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock_quote")
public class StockQuoteController {
    @Autowired
    private StockQuoteService stockQuoteService;

    @Autowired
    private StockQuoteEnergyLevelService stockQuoteEnergyLevelService;

    @PostMapping("quote")
    public QuoteDto postQuotes(@RequestBody @Valid final QuoteDto quote) {
        return stockQuoteService.postQuotes(quote);
    }

    @GetMapping("elvls")
    public List<QuoteElvlDto> getElvl() {
        return stockQuoteEnergyLevelService.getAllElvl();
    }

    @GetMapping("elvls/{isin}")
    public QuoteElvlDto getElvl(@PathVariable String isin) {
        return stockQuoteEnergyLevelService.getOneElvl(isin);
    }
}
