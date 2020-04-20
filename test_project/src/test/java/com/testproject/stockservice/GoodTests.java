package com.testproject.stockservice;

import com.testproject.stockservice.model.dto.QuoteDto;
import com.testproject.stockservice.model.dto.QuoteElvlDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoodTests extends AbstractBaseTest {
    @Test
    @Order(0)
    public void when_BidLessThanAskSent_expect_SameObject() throws Exception {

        QuoteDto requestObject = new QuoteDto()
                .setIsin("100000000000")
                .setBid(50)
                .setAsk(100);

        postQuoteAndCheck(requestObject);
    }

    @Test
    @Order(0)
    public void when_Rule_1_BidHigherThanLevel_expect_HigherEnergyLevel() throws Exception {

        final String knownId = "100000000001";

        final QuoteDto requestObject = new QuoteDto()
                .setIsin(knownId)
                .setBid(50)
                .setAsk(100);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(50f);
        });


        requestObject.setBid(70);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(70f);
        });

    }

    @Test
    @Order(0)
    public void when_Rule_1_BidLowerThanLevel_expect_NoChangeInEnergyLevel() throws Exception {

        final String knownId = "100000000002";

        final QuoteDto requestObject = new QuoteDto()
                .setIsin(knownId)
                .setBid(50)
                .setAsk(100);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(50f);
        });

        requestObject.setBid(30);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(50f);
        });

    }

    @Test
    @Order(0)
    public void when_Rule_2_AskLowerThanLevel_expect_LowerEnergyLevel() throws Exception {

        final String knownId = "100000000003";

        final QuoteDto requestObject = new QuoteDto()
                .setIsin(knownId)
                .setBid(50)
                .setAsk(100);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(50f);
        });


        requestObject.setBid(20);
        requestObject.setAsk(30);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(30f);
        });

    }

    @Test
    @Order(0)
    public void when_Rule_2_AskHigherThanLevel_expect_NoChangeInEnergyLevel() throws Exception {

        final String knownId = "100000000004";

        final QuoteDto requestObject = new QuoteDto()
                .setIsin(knownId)
                .setBid(50)
                .setAsk(100);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(50f);
        });

        requestObject.setBid(20);
        requestObject.setAsk(120);

        postQuoteAndCheck(requestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(50f);
        });

    }

    @Test
    @Order(0)
    public void when_ExampleLogicExecuted_expect_FlowAsInExample() throws Exception {
        final String knownId = "RU000A0JX0J2";
        ResponseEntity<QuoteDto> response_0 = this.restTemplate.getForEntity(getEnergyLevelUrl(), QuoteDto.class, knownId);

        assertThat(response_0.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);

        QuoteDto firstRequestObject = new QuoteDto()
                .setIsin(knownId)
                .setBid(100.2f)
                .setAsk(101.9f);

        postQuoteAndCheck(firstRequestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(100.2f);
        });

        QuoteDto secondRequestObject = new QuoteDto()
                .setIsin(knownId)
                .setBid(100.5f)
                .setAsk(101.9f);

        postQuoteAndCheck(secondRequestObject);

        getQuoteEnergyLevelAndCheck(knownId, (response) -> {
            assertThat(response.getBody().getElvl()).isEqualTo(100.5f);
        });

    }

    @Test
    @Order(1)
    public void when_allTestsFinished_then_thereShouldBeSomeEnergyLevels() {
        ResponseEntity<QuoteElvlDto[]> response = this.restTemplate.getForEntity(getAllEnergyLevelsUrl(), QuoteElvlDto[].class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody().length).isGreaterThan(0);
    }

}
