package com.company.platform.msvcreportms.repositories;

import com.company.platform.msvcreportms.beans.LoadBalancerConfiguration;
import com.company.platform.msvcreportms.models.Company;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "msvc-companies")
@LoadBalancerClient(name = "msvc-companies", configuration = LoadBalancerConfiguration.class)
public interface CompaniesRepository {

    @GetMapping("/msvc-companies/{name}")
    Optional<Company> getByName(@PathVariable String name);

    @PostMapping("/msvc-companies")
    Optional<Company> postByName(@RequestBody Company company);

    @DeleteMapping("/msvc-companies/{name}")
    void deleteByName(@PathVariable String name);

}
