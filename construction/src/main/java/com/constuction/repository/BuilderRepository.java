package com.constuction.repository;
import com.constuction.entity.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuilderRepository extends JpaRepository<Builder,Long> {

    @Query("SELECT a FROM Builder a where a.isDeleted = FALSE " +
            "and a.basicDetails.firstName =:firstName " +
            "and a.basicDetails.lastName =:lastName " +
            "and a.basicDetails.mobileNumber =:mobileNumber")
    Builder findByFirstNameAndLastNameAndMobileNumber(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mobileNumber") String mobileNumber);
}
