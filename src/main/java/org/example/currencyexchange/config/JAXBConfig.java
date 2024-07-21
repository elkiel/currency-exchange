package org.example.currencyexchange.config;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.currencyexchange.model.dto.ExchangeRatesSeries;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JAXBConfig {

    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(ExchangeRatesSeries.class);
    }

    @Bean
    public Unmarshaller unmarshaller(JAXBContext jaxbContext) throws JAXBException {
        return jaxbContext.createUnmarshaller();
    }

    @Bean
    public Marshaller marshaller(JAXBContext jaxbContext) throws JAXBException {
        return jaxbContext.createMarshaller();
    }
}
