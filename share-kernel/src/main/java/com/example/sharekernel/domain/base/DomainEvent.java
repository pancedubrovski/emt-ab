package com.example.sharekernel.domain.base;

import lombok.NonNull;

import java.time.Instant;

public interface DomainEvent extends DomainObject {

    @NonNull
    Instant occurredOn();
}
