package com.mytel.smallBanking.model;


public enum TransactionType {
    DEPOSIT(0, "deposit"),
    WITHDRAW(1, "withdraw"),
    TRANSFER(2, "transfer");

    int tranNo;
    String transDescription;

    TransactionType(int tranNo, String transDescription) {
        this.tranNo = tranNo;
        this.transDescription = transDescription;
    }

    public int getTranNo() {
        return tranNo;
    }

    public void setTranNo(int tranNo) {
        this.tranNo = tranNo;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }



}
