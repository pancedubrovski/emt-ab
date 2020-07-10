package com.example.sharekernel.infra.eventlog;


import com.example.sharekernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
