package com.testproject.stockservice;

import com.testproject.stockservice.model.dto.QuoteDto;
import com.testproject.stockservice.model.dto.QuoteElvlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractBaseTest {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    String postQuoteUrl() {
        return "http://localhost:" + port + "/api/v1/stock_quote/quote";
    }

    String getEnergyLevelUrl() {
        return "http://localhost:" + port + "/api/v1/stock_quote/elvls/{id}";
    }

    String getAllEnergyLevelsUrl() {
        return "http://localhost:" + port + "/api/v1/stock_quote/elvls";
    }

    HttpEntity getRequest(Object requestObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(requestObject.toString(), headers);
    }

    HttpEntity getRequest() {
        HttpHeaders headers = new HttpHeaders();

        return new HttpEntity(headers);
    }

    void postQuoteAndCheck(QuoteDto quoteDto) {
        ResponseEntity<QuoteDto> result = this.restTemplate.postForEntity(postQuoteUrl(),
                getRequest(quoteDto), QuoteDto.class);

        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualToComparingFieldByField(quoteDto);
    }

    void getQuoteEnergyLevelAndCheck(String id, Consumer<ResponseEntity<QuoteElvlDto>> test) {
        ResponseEntity<QuoteElvlDto> response = this.restTemplate.getForEntity(getEnergyLevelUrl(), QuoteElvlDto.class, id);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody().getIsin()).isEqualTo(id);
        test.accept(response);
    }
}
