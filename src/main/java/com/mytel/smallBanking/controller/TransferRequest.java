package com.mytel.smallBanking.controller;

public class TransferRequest {
    Long fromAccountNo;
    Long toAccountNo;
    Double amount;
    String content;
    Double fee;

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Long getToAccountNo() {
        return toAccountNo;
    }

    public void setToAccountNo(Long toAccountNo) {
        this.toAccountNo = toAccountNo;
    }

    public Long getFromAccountNo() {
        return fromAccountNo;
    }

    public void setFromAccountNo(Long fromAccountNo) {
        this.fromAccountNo = fromAccountNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
