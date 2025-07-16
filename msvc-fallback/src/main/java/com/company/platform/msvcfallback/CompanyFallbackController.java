package com.company.platform.msvcfallback;

import com.company.platform.msvcfallback.entities.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;


@RestController
@RequestMapping("company")
@Slf4j
public class CompanyFallbackController {
    //Puede venir de una base de datos respaldo puede ser
    private static final Company DEFAULT_COMPANY = Company.builder()
            .id(0L)
            .founder("Fallback")
            .name("Fallback Company")
            .foundationDate(LocalDate.now())
            .webSites(Collections.emptyList())
            .build();

    @GetMapping("{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.info("GET: company {}", name);
        return ResponseEntity.ok(DEFAULT_COMPANY);
    }

}
