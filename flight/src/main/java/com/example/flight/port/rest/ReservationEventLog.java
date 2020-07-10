package com.example.flight.port.rest;

import com.example.sharekernel.domain.base.RemoteEventLog;
import com.example.sharekernel.infra.eventlog.RemoteEventLogService;
import com.example.sharekernel.infra.eventlog.StoredDomainEvent;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Service
public class ReservationEventLog  implements RemoteEventLogService {



    private final String source;
    private final String serverUrl;
    private final RestTemplate restTemplate;

    public ReservationEventLog(@Value("http://localhost:8082") String serverUrl,
                               @Value("5000") int connectTimeOut,@Value("5000") int readTimeOut) {
        this.source = Objects.requireNonNull(serverUrl, "serverUrl must not be null");
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeOut);
        requestFactory.setReadTimeout(readTimeOut);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    public String source() {
        return this.source;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        URI currentLogUri = UriComponentsBuilder.fromUriString(serverUrl)
                .path(String.format("/api/event-log/%d", lastProcessedId)).build().toUri();
        return retrieveLog(currentLogUri);
    }
    @NonNull
    private RemoteEventLog retrieveLog(@NonNull URI uri) {
        ResponseEntity<List<StoredDomainEvent>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<StoredDomainEvent>>() {
        });
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Could not retrieve log from URI " + uri);
        }
        return new RemoteEventLogImpl(response);

    }
    private class RemoteEventLogImpl implements RemoteEventLog {

        private final List<StoredDomainEvent> events;

        private RemoteEventLogImpl(@NonNull ResponseEntity<List<StoredDomainEvent>> events) {
            this.events = List.copyOf(Objects.requireNonNull(events.getBody()));
        }



        @Override
        public List<StoredDomainEvent> events() {
            return events;
        }
    }
}
