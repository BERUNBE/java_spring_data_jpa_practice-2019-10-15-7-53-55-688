package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/companies")
@Transactional
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @GetMapping(produces = {"application/json"})
    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    @GetMapping(path = "/{name}", produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name) {
        return repository.findOneByName(name);
    }

    @PostMapping(produces = {"application/json"})
    public Company addCompany(@RequestBody Company company) {
        return repository.save(company);
    }

    @PutMapping(path = "/{name}", produces = {"application/json"})
    public Company updateCompanyByName(@PathVariable String name, @RequestBody Company updatedCompany) {
        Company company = repository.findOneByName(name);
        company.setName(updatedCompany.getName());
        company.setProfile(updatedCompany.getProfile());
        company.setEmployees(updatedCompany.getEmployees());
        return repository.save(company);
    }

    @DeleteMapping(path = "/{name}", produces = {"application/json"})
    public List<Company> deleteCompanyByName(@PathVariable String name) {
        repository.deleteByName(name);
        return repository.findAll();
    }
}
