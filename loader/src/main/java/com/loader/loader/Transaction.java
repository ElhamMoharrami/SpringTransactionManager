package com.loader.loader;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Setter
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    private  int id;
    private  int sourceAccountId;
    private  int destinationAccountId;
    private  String amount;
    private  String date;
    private  String status;

}
