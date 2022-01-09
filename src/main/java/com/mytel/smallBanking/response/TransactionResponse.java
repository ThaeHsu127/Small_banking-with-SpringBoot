package com.mytel.smallBanking.response;

import lombok.Data;

@Data
public class TransactionResponse {
    private String transactionType;
    private Double currentAmount;
    private String status;
}
