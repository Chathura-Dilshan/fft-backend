package com.fft.farm.repository;

import com.fft.farm.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {

    Optional<TransactionDetails> findByTransactionDetailsSeq(Integer transactionDetailsSeq);
}
