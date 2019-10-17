package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> findAllByNameLike(String name, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return companyRepository.findAllByNameLike(name, pageable);
    }

    public Iterable<Company> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return companyRepository.findAll(pageable);
    }

    public Company findOneByName(String name) {
        return companyRepository.findOneByName(name);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void deleteByName(String name) {
        companyRepository.deleteByName(name);
    }
}
