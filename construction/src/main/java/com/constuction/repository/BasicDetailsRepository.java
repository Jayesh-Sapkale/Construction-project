package com.constuction.repository;

import com.constuction.entity.BasicDetails;
import com.constuction.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicDetailsRepository extends JpaRepository<BasicDetails,Long> {
}
