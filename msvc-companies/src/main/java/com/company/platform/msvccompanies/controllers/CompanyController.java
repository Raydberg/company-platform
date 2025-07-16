package com.company.platform.msvccompanies.controllers;

import com.company.platform.msvccompanies.entities.Company;
import com.company.platform.msvccompanies.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
//        try {
//            Thread.sleep(5000);
//        } catch (
//                InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        log.info("GET: company {}", name);
        return ResponseEntity.ok(companyService.readByName(name));
    }


    @PostMapping
    public ResponseEntity<Company> post(@RequestBody Company company) {
        log.info("POST: company {}", company.getName());
        return ResponseEntity.ok(companyService.create(company));
    }

    @PutMapping("{name}")
    public ResponseEntity<Company> update(@PathVariable String name, @RequestBody Company company) {
        log.info("PUT: company name {} with company {}", name, company.getName());
        return ResponseEntity.ok(companyService.update(company, name));
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        log.info("DELETE company : {}", name);
        companyService.delete(name);
        return ResponseEntity.noContent().build();
    }


}
