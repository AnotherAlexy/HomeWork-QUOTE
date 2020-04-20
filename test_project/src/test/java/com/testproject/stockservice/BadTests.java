package com.testproject.stockservice;

import com.testproject.stockservice.model.dto.QuoteDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StockServiceApplication.class)
@ActiveProfiles("test")
class BadTests extends AbstractBaseTest {

    @Test
    public void when_BidGreaterThanAsk_expect_BadRequestFromValidation() throws Exception {

        QuoteDto requestObject = new QuoteDto()
                .setIsin("123456789012")
                .setBid(100)
                .setAsk(50);

        ResponseEntity<QuoteDto> response = this.restTemplate.postForEntity(postQuoteUrl(),
                getRequest(requestObject), QuoteDto.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void when_IsinHaveLengthOtherThan12_expect_BadRequestFromValidation() throws Exception {

        QuoteDto requestObject = new QuoteDto()
                .setIsin("1234")
                .setBid(50)
                .setAsk(100);

        ResponseEntity<QuoteDto> response = this.restTemplate.postForEntity(postQuoteUrl(),
                getRequest(requestObject), QuoteDto.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void when_SearchForNonExistentIsin_expect_NotFound() throws Exception {

        ResponseEntity<QuoteDto> response = this.restTemplate.getForEntity(getEnergyLevelUrl(), QuoteDto.class, "0");

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);

    }
}
