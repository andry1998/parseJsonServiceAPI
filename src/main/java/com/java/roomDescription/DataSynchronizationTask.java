package com.java.roomDescription;

import com.java.roomDescription.service.SynchronizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DataSynchronizationTask {

    final SynchronizationService synchronizationService;

    @Scheduled(fixedRateString = "${task.time}")
    public void loadingAndUnloadingData() {
        synchronizationService.dataSynchronization();
        log.info("loading and unloading success");
    }
}
