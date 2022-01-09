package com.mytel.smallBanking.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private Long fromAccount;
    private Long toAccount;
    private double amount;
    private String status;
    private LocalDateTime transactionAt;
    private LocalDateTime lastUpdatedAt;




}
