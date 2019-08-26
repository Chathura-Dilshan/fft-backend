package com.fft.farm.service;

import com.fft.farm.entity.TransactionDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionDetailsService {
    ResponseEntity createTransactionDetails(TransactionDetails transactionDetails);

    ResponseEntity updateTransactionDetails(TransactionDetails transactionDetails);

//    ResponseEntity<TransactionDetails> deleteTransactionDetails(Integer transactionDetailsSeq);

//    List<TransactionDetails> findAllTransactionDetailss(Integer statusSeq);
}
