package com.tw.apistackbase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.core.CompanyProfile;
import com.tw.apistackbase.core.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("companyA"));
    }

    @Test
    void should_return_all_companies() throws Exception {
        Company company1 = createCompany(1L, "companyA", null, Collections.emptyList());
        Company company2 = createCompany(2L, "companyB", null, Collections.emptyList());
        when(companyService.findAll(PAGE, PAGE_SIZE)).thenReturn(Arrays.asList(company1, company2));

        ResultActions result = mvc.perform(get("/companies/all"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("companyA"))
                .andExpect(jsonPath("$[1].name").value("companyB"));
    }

    @Test
    void should_add_a_company() throws Exception {
        Company company1 = createCompany(1L, "companyA", null, Collections.emptyList());

        ResultActions result = mvc.perform(post("/companies")
                .contentType(APPLICATION_JSON)
                .content(mapToJson(company1)));

        result.andExpect(status().isCreated());
    }
    

    private Company createCompany(Long id, String name, CompanyProfile companyProfile, List<Employee> employees) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setProfile(companyProfile);
        company.setEmployees(employees);
        return company;
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
