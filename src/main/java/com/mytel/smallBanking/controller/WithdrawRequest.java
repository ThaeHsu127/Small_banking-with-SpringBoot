package com.mytel.smallBanking.controller;

public class WithdrawRequest {
    Long fromAccountNo;
    Double amount;
    String content;

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
