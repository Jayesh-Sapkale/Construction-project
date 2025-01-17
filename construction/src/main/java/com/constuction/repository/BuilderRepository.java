package com.constuction.repository;
import com.constuction.entity.Admin;
import com.constuction.entity.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuilderRepository extends JpaRepository<Builder,Long> {
}
