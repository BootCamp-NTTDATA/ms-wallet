package com.bootcamp.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Payment extends BaseEntity{
    private String idWallet;
    private BigDecimal amount;
    private String type;
    private Client client;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Payment{" +
                "idWallet='" + idWallet + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", client=" + client +
                ", date=" + date +
                '}';
    }
}
