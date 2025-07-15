package com.company.platform.msvcreportms.controllers;

import com.company.platform.msvcreportms.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("{name}")
    public ResponseEntity<Map<String, String>> getReport(@PathVariable String name) {
        var resp = Map.of("report", reportService.makeReport(name));
        return ResponseEntity.ok(resp);
    }

    @PostMapping()
    public ResponseEntity<String> postReport(@RequestBody String report) {
        return ResponseEntity.ok(reportService.saveReport(report));
    }

    @DeleteMapping("{name}")
    ResponseEntity<Void> deleteReport(@PathVariable String name) {
        reportService.deleteReport(name);
        return ResponseEntity.noContent().build();
    }

}
