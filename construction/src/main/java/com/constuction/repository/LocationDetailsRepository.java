package com.constuction.repository;

import com.constuction.entity.LocationDetails;
import com.constuction.entity.OrderBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDetailsRepository extends JpaRepository<LocationDetails,Long> {
}
