package org.example.currencyexchange.service;

import jakarta.xml.bind.Unmarshaller;
import org.example.currencyexchange.config.JAXBConfig;
import org.example.currencyexchange.model.enums.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@SpringBootTest
@ContextConfiguration(classes = {JAXBConfig.class})
public class ExchangeRateServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Autowired
    private Unmarshaller unmarshaller;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exchangeRateService = new ExchangeRateService("https://api.nbp.pl/api/exchangerates/rates/C/USD", restTemplate, unmarshaller);
    }

    @Test
    public void testGetExchangeRatePlnToUsd() {
        String mockResponse = "<ExchangeRatesSeries><Rates><Rate><Ask>3.85</Ask></Rate></Rates></ExchangeRatesSeries>";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse);

        BigDecimal rate = exchangeRateService.getExchangeRate(Currency.PLN, Currency.USD);

        assertNotNull(rate);
        assertEquals(new BigDecimal("3.85"), rate);
    }

    @Test
    public void testGetExchangeRateUsdToPln() {
        String mockResponse = "<ExchangeRatesSeries><Table>A</Table><Currency>USD</Currency><Code>USD</Code><Rates><Rate><No>001/A/NBP/2021</No><EffectiveDate>2021-01-01</EffectiveDate><Bid>3.80</Bid><Ask>3.85</Ask></Rate></Rates></ExchangeRatesSeries>";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockResponse);

        BigDecimal rate = exchangeRateService.getExchangeRate(Currency.USD, Currency.PLN);

        assertNotNull(rate);
        assertEquals(new BigDecimal("0.2597"), rate);
    }

}
