package com.payment.repository;

import com.payment.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends
        JpaRepository<TransactionDetails, Long> {
}
