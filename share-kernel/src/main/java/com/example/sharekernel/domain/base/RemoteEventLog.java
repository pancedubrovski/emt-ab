package com.example.sharekernel.domain.base;


import com.example.sharekernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {
    List<StoredDomainEvent> events();
}
