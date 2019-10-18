package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> findAllByNameLike(String name, Integer page, Integer pageSize) {
        return companyRepository.findAllByNameLike(name, PageRequest.of(page, pageSize));
    }

    public Iterable<Company> findAll(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public ResponseEntity<Company> updateCompanyByName(String name, Company updatedCompany) {
        Company company = companyRepository.findOneByName(name);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setName(updatedCompany.getName());
        company.setProfile(updatedCompany.getProfile());
        company.setEmployees(updatedCompany.getEmployees());
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    public Company findOneByName(String name) {
        return companyRepository.findOneByName(name);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public ResponseEntity<Company> deleteCompanyByName(String name) {
        Company company = companyRepository.findOneByName(name);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
