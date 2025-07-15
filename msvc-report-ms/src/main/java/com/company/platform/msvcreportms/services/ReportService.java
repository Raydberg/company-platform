package com.company.platform.msvcreportms.services;

public interface ReportService {
    String makeReport(String name);

    String saveReport(String report);

    void deleteReport(String name);
}
