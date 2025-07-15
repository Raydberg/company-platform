package com.company.platform.msvccompanies.services;

import com.company.platform.msvccompanies.entities.Company;

public interface CompanyService {
    Company readByName(String name);

    Company create(Company company);

    Company update(Company company, String name);

    void delete(String name);
}
