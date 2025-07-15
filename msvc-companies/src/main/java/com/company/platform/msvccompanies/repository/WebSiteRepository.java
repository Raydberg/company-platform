package com.company.platform.msvccompanies.repository;

import com.company.platform.msvccompanies.entities.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebSiteRepository extends JpaRepository<WebSite, Long> {
}
