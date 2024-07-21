package org.example.currencyexchange.service;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.example.currencyexchange.model.dto.ExchangeRatesSeries;
import org.example.currencyexchange.model.enums.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeRateService implements ExchangeRateServiceIF {

    @Value("${nbp.api.url}")
    private final String nbpApiUrl;

    private final RestTemplate restTemplate;
    private final Unmarshaller unmarshaller;

    @Override
    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == Currency.PLN && toCurrency == Currency.USD) {
            return fetchExchangeRate();
        } else if (fromCurrency == Currency.USD && toCurrency == Currency.PLN) {
            return BigDecimal.ONE.divide(fetchExchangeRate(), 4, BigDecimal.ROUND_HALF_UP);
        } else {
            throw new IllegalArgumentException("Unsupported currency conversion");
        }
    }

    private BigDecimal fetchExchangeRate() {
        String response = restTemplate.getForObject(nbpApiUrl, String.class);
        return parseExchangeRate(response)
                .orElseThrow(() -> new RuntimeException("No exchange rate found in the response"));
    }

    private Optional<BigDecimal> parseExchangeRate(String response) {
        try {
            StringReader reader = new StringReader(response);
            ExchangeRatesSeries ratesSeries = (ExchangeRatesSeries) unmarshaller.unmarshal(reader);
            return ratesSeries.getRates().stream().findFirst().map(ExchangeRatesSeries.Rate::getAsk);
        } catch (JAXBException e) {
            throw new RuntimeException("Error parsing XML response from NBP API", e);
        }
    }

}
