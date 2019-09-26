package com.fft.farm.controller;

import com.fft.farm.entity.TransactionDetails;
import com.fft.farm.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactionDetails")
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;

    @Autowired
    public TransactionDetailsController(TransactionDetailsService transactionDetailsService) {
        this.transactionDetailsService = transactionDetailsService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('createTransactionDetails')")
    public ResponseEntity createTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
        return this.transactionDetailsService.createTransactionDetails(transactionDetails);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('login','createTransactionDetails')")
    public ResponseEntity updateTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
        return this.transactionDetailsService.updateTransactionDetails(transactionDetails);
    }
//
//    @DeleteMapping("{transactionDetailsSeq}")
//    @PreAuthorize("hasAnyRole('login','transactionDetails')")
//    public ResponseEntity<TransactionDetails> deleteTransactionDetails(@PathVariable("transactionDetailsSeq") Integer transactionDetailsSeq) {
//        return this.transactionDetailsService.deleteTransactionDetails(transactionDetailsSeq);
//    }

//    @GetMapping
//    @PreAuthorize("hasAnyRole('login','transactionDetails')")
//    public List<TransactionDetails> findAllTransactionDetailss() {
//        return this.transactionDetailsService.findAllTransactionDetailss(MasterDataStatus.APPROVED.getStatusSeq());
//    }

    @GetMapping("{transactionDetailsSeq}")
    @PreAuthorize("hasAnyRole('login','createTransactionDetails')")
    public TransactionDetails findTransactionDetailsByTransactionDetailsSeq(@PathVariable("transactionDetailsSeq") Integer transactionDetailsSeq) {
        return this.transactionDetailsService.findByTtransactionDetailsSeq(transactionDetailsSeq);

    }
}
