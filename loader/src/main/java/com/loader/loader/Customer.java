package com.loader.loader;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customers")
@Setter
@Getter
@NoArgsConstructor
public class Customer {
    @Id
    private  int id;
    private  String firstName;
    private  String lastName;
    private  String postCode;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;
}
