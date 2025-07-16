package com.company.platform.msvcreportms.services.impl;

import com.company.platform.msvcreportms.helpers.ReportHelper;
import com.company.platform.msvcreportms.models.Company;
import com.company.platform.msvcreportms.models.WebSite;
import com.company.platform.msvcreportms.repositories.CompaniesFallbackRepository;
import com.company.platform.msvcreportms.repositories.CompaniesRepository;
import com.company.platform.msvcreportms.services.ReportService;
import com.company.platform.msvcreportms.streams.ReportPublisher;
import com.netflix.discovery.EurekaClient;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final CompaniesRepository companiesRepository;
    private final ReportHelper reportHelper;
    private final CompaniesFallbackRepository companiesFallbackRepository;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final ReportPublisher reportPublisher;

    @Override
    public String makeReport(String name) {
        //Instanciarlo
        var circuitBreaker = circuitBreakerFactory.create("companies-circuirbreaker");
        return circuitBreaker.run(
                () -> makeReportMain(name), throwable -> makeReportFallback(name, throwable)
        );
    }

    @Override
    public String saveReport(String report) {
        var format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var placeholder = reportHelper.getPlaceholdersFromTemplates(report);
        var webSites = Streams.of(placeholder.get(3)).map(website ->
                WebSite.builder()
                        .name(website)
                        .build()).toList();
        var company = Company.builder()
                .name(placeholder.get(0))
                .foundationDate(LocalDate.parse(placeholder.get(1), format))
                .founder(placeholder.get(2))
                .webSites(webSites)
                .build();

        companiesRepository.postByName(company);
        reportPublisher.publishReport(report);
        return "Saved";
    }

    @Override
    public void deleteReport(String name) {
        if (name != null || !name.isBlank()) {
            companiesRepository.deleteByName(name);
        }
    }

    private String makeReportMain(String name) {
        return reportHelper.readTemplate(companiesRepository.getByName(name)
                .orElseThrow(() -> new NotFoundException("Compañia no encontrada")));
    }

    private String makeReportFallback(String name, Throwable error) {
        log.warn(error.getMessage());
        return reportHelper.readTemplate(companiesFallbackRepository.getByName(name));
    }

}
