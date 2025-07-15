package com.company.platform.msvccompanies.services.impl;

import com.company.platform.msvccompanies.entities.Category;
import com.company.platform.msvccompanies.entities.Company;
import com.company.platform.msvccompanies.repository.CompanyRepository;
import com.company.platform.msvccompanies.services.CompanyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public Company readByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(webSite -> {
            if (
                    Objects.isNull(webSite.getCategory())) {
                webSite.setCategory(Category.NONE);
            }
        });
        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company, String name) {
        var companyToUpdate = companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        companyToUpdate.setFounder(company.getFounder());
        return companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        Company companytoDelete = companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyRepository.delete(companytoDelete);
    }
}
