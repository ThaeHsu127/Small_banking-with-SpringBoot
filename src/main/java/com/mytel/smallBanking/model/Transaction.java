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
    @Column
    private Long id;
    @Column
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column
    private TransactionType transactionType;

    @Column
    private String fromAccount;

    @Column
    private String toAccount;

    @Column
    private double amount;

    @Column
    private String status;

    @Column
    private LocalDateTime transactionAt;

    @Column
    private LocalDateTime lastUpdatedAt;




}
