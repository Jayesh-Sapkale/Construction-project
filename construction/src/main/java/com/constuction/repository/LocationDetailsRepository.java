package com.constuction.repository;

import com.constuction.entity.LocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDetailsRepository extends JpaRepository<LocationDetails,Long> {

    LocationDetails findByStateIgnoreCaseAndCityIgnoreCaseAndPincodeAndAddressIgnoreCase(String state, String city, String pincode, String address);
}
