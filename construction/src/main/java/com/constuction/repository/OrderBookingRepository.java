package com.constuction.repository;

import com.constuction.entity.OrderBooking;
import com.constuction.entity.Project;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookingRepository extends JpaRepository<OrderBooking,Long> {
}
