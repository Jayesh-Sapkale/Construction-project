package com.constuction.repository;

import com.constuction.entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails,Long> {
    CompanyDetails findByName(String name);
}
