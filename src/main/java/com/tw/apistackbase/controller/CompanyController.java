package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        return repository.findAll();
    }

    @GetMapping(path = "/{name}", produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name) {
        return repository.findOneByName(name);
    }

    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return repository.save(company);
    }

    @PutMapping(path = "/{name}", produces = {"application/json"})
    public Company updateCompany(@PathVariable String name, @RequestBody Company updatedCompany) {
        Company company = repository.findOneByName(name);
        company.setName(updatedCompany.getName());
        company.setProfile(updatedCompany.getProfile());
        company.setEmployees(updatedCompany.getEmployees());
        return repository.save(company);
    }
}
