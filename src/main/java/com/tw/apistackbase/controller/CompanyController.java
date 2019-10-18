package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/companies")
@Transactional
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(produces = {"application/json"})
    public Iterable<Company> getCompanyByName(@RequestParam String name, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return companyService.findAllByNameLike(name, page, pageSize);
    }

    @GetMapping(path = "/all", produces = {"application/json"})
    public Iterable<Company> getAllCompanies(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return companyService.findAll(page, pageSize);
    }

    @PostMapping(produces = {"application/json"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping(path = "/{name}", produces = {"application/json"})
    public ResponseEntity<Company> updateCompanyByName(@PathVariable String name, @RequestBody Company updatedCompany) {
        return companyService.updateCompanyByName(name, updatedCompany);

    }

    @DeleteMapping(path = "/{name}", produces = {"application/json"})
    public ResponseEntity<Company> deleteCompanyByName(@PathVariable String name) {
        return companyService.deleteCompanyByName(name);
    }
}
