package com.example.reservationmanagement.Port.Clinet;

import com.example.reservationmanagement.application.Flights;
import com.example.reservationmanagement.domain.model.Flight;
import com.example.reservationmanagement.domain.model.FlightId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class FlightClient implements Flights {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public FlightClient(@Value("http://localhost:8081") String serverUrl, @Value("5000") int connectTimeOut, @Value("5000") int readTimeOut) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeOut);
        requestFactory.setReadTimeout(readTimeOut);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri(){
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public List<Flight> findAll(){
        try{
            return restTemplate.exchange(uri().path("api/flight").build().toUri(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Flight>>(){
            }).getBody();
        }catch (Exception ex){
            LOGGER.error("Error retrieving products", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Flight findById(FlightId id) {
        try {
            return restTemplate.exchange(uri().path("/api/flight/" + id.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Flight>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving product by id", ex);
            return null;
        }
    }
}
