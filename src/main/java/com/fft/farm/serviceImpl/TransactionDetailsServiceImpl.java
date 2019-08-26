package com.fft.farm.serviceImpl;

import com.fft.farm.entity.TransactionDetails;
import com.fft.farm.repository.TransactionDetailsRepository;
import com.fft.farm.service.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Optional;
import java.util.Set;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    public TransactionDetailsServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }


    @Override
    public ResponseEntity createTransactionDetails(TransactionDetails transactionDetails) {
        ResponseEntity responseEntity;
        Optional<TransactionDetails> existTransactionDetailsId = this.transactionDetailsRepository.findByTransactionDetailsSeq(transactionDetails.getTransactionDetailsSeq());
        Set<ConstraintViolation<TransactionDetails>> errors = Validation.buildDefaultValidatorFactory().
                getValidator().validate(transactionDetails);
        if (errors.size() > 0) {
//            String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
            responseEntity = new ResponseEntity<>("TransactionDetails already exist", HttpStatus.BAD_REQUEST);
        } else {

            if (existTransactionDetailsId.isPresent()) {
                responseEntity = new ResponseEntity<>("TransactionDetails already exist", HttpStatus.BAD_REQUEST);

            } else {
                transactionDetails.setTransactionDetailsSeq(null);
                this.transactionDetailsRepository.save(transactionDetails);
                responseEntity = new ResponseEntity<>(transactionDetails, HttpStatus.CREATED);
            }


        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateTransactionDetails(TransactionDetails transactionDetails) {
        Optional<TransactionDetails> dbTransactionDetails = this.transactionDetailsRepository.findById(transactionDetails.getTransactionDetailsSeq());
        ResponseEntity responseEntity;
        if (dbTransactionDetails.isPresent()) {
            if (dbTransactionDetails.get().equals(transactionDetails)) {
                responseEntity = new ResponseEntity<>(transactionDetails, HttpStatus.NOT_MODIFIED);
            } else {
                Set<ConstraintViolation<TransactionDetails>> errors = Validation.buildDefaultValidatorFactory().
                        getValidator().validate(transactionDetails);
                if (errors.size() > 0) {
//                    String errorMessage = ErrorMessageCreator.errorsInRow((HashSet<?>) errors);
                    responseEntity = new ResponseEntity<>("TransactionDetails already exist", HttpStatus.BAD_REQUEST);
                } else {
                    transactionDetails = this.transactionDetailsRepository.save(transactionDetails);
                    responseEntity = new ResponseEntity<>(transactionDetails, HttpStatus.CREATED);
                }
            }
        } else {
            responseEntity = new ResponseEntity<>(transactionDetails, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

//    @Override
//    public ResponseEntity<TransactionDetails> deleteTransactionDetails(Integer transactionDetailsSeq) {
//        Optional<TransactionDetails> dbTransactionDetails = this.transactionDetailsRepository.findById(transactionDetailsSeq);
//        ResponseEntity<TransactionDetails> responseEntity;
//        if (dbTransactionDetails.isPresent()) {
//            TransactionDetails transactionDetails = dbTransactionDetails.get();
//            transactionDetails.setStatus(MasterDataStatus.DELETED.getStatusSeq());
//            this.transactionDetailsRepository.save(transactionDetails);
//            responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//    }

//    @Override
//    public List<TransactionDetails> findAllTransactionDetailss(Integer statusSeq) {
//        return this.transactionDetailsRepository.findByStatus(statusSeq);
//    }

}
