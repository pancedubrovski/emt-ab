package com.example.sharekernel.infra.eventlog;


import java.util.Optional;

public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<Object> translate(StoredDomainEvent remoteEvent);
}
