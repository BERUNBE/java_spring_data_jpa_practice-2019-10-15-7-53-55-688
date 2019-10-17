package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.core.CompanyProfile;
import com.tw.apistackbase.core.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mvc;

    @Test
    void should_return_company_by_name() throws Exception {
        Company company = createCompany(1L, "companyA", null, Collections.emptyList());
        when(companyService.findAllByNameLike("companyA", PAGE, PAGE_SIZE)).thenReturn(Arrays.asList(company));

        ResultActions result = mvc.perform(get("/companies/?name=companyA"));

        result.andExpect(status().isOk());
    }

    private Company createCompany(Long id, String name, CompanyProfile companyProfile, List<Employee> employees) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setProfile(companyProfile);
        company.setEmployees(employees);
        return company;
    }
}
