package com.fft.farm.util;

import java.util.Arrays;

public enum  TransactionStatus {
    FARM(1, "Farm"),
    TRANSPORTER(2, "Transporter"),
    PACKING(3, "Packing"),
    WAREHOUSE(4, "Warehouse"),
    SUPERMARKET(5, "Supermarket");

    private final Integer transactionStatusSeq;
    private final String statusName;

    TransactionStatus(Integer transactionStatusSeq, String statusName) {
        this.transactionStatusSeq = transactionStatusSeq;
        this.statusName = statusName;
    }

    public static TransactionStatus findOne(Integer transactionStatusSeq) {
        return Arrays.stream(TransactionStatus.values())
                .filter(x -> x.transactionStatusSeq.equals(transactionStatusSeq))
                .findFirst()
                .orElse(null);
    }
    
    public Integer getTransactionStatusSeq() {
        return transactionStatusSeq;
    }

    public String getStatusName() {
        return statusName;
    }
}
