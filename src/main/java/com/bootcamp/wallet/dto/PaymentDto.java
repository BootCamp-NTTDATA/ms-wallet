package com.bootcamp.wallet.dto;

import com.bootcamp.wallet.entity.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private String id;
    private String idWallet;
    private BigDecimal amount;
    private String type;
    private Client client;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "PaymentDto{" +
                "id='" + id + '\'' +
                ", idWallet='" + idWallet + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", client=" + client +
                ", date=" + date +
                '}';
    }
}

