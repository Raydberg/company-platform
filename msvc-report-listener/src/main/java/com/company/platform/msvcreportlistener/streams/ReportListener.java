package com.company.platform.msvcreportlistener.streams;

import com.company.platform.msvcreportlistener.documents.ReportDocument;
import com.company.platform.msvcreportlistener.repository.ReportDocumentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
@AllArgsConstructor
public class ReportListener {
    private final ReportDocumentRepository documentRepository;

    //Escucha cuando viene un nuevo mensaje
    @Bean
    public Consumer<String> consumerReport() {
        return report -> {
            documentRepository
                    .save(ReportDocument.builder()
                            .content(report)
                            .build());
            log.info("Saving report {}", report);
        };
    }
}
