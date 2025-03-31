package com.constuction.repository;
import com.constuction.entity.Admin;
import com.constuction.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT a FROM Customer a where a.isDeleted = FALSE " +
            "and a.basicDetails.firstName =:firstName " +
            "and a.basicDetails.lastName =:lastName " +
            "and a.basicDetails.mobileNumber =:mobileNumber")
    List<Customer> findByFirstNameAndLastNameAndMobileNumber(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mobileNumber") String mobileNumber);
}
