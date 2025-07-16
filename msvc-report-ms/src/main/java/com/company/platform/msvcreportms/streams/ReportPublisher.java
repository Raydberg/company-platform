package com.company.platform.msvcreportms.streams;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReportPublisher {
    private final StreamBridge streamBridge;

    /**
     * @param report
     * @Topic name -> consumerReport
     */
    public void publishReport(String report) {
        //Cuando se trabaja con cloud streams se crea 3 topicos
        streamBridge.send("consumerReport", report);
        //Nomenclatura de Spring
        streamBridge.send("consumerReport-in-0", report);
        streamBridge.send("consumerReport-out-0", report);
    }
}
