package com.tw.apistackbase.repository;

import com.tw.apistackbase.core.Company;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("Select c from Company c where c.name = :name")
    Company findOneByName(@Param("name") String name);

    @Query("Select c from Company c where c.name like CONCAT('%',:name,'%')")
    List<Company> findAllByNameLike(@Param("name") String name, Pageable pageable);

    void deleteByName(String name);
}
