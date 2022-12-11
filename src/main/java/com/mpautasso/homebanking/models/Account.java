package com.mpautasso.homebanking.models;

import com.mpautasso.homebanking.utils.AccountType;
import com.mpautasso.homebanking.utils.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "currency", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "account_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false, unique = true)
    private Integer number;
    @Column(nullable = false, unique = true)
    private Integer cbu;
    private Double balance;

}
