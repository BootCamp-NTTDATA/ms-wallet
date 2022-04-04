package com.bootcamp.wallet.request;

import com.bootcamp.wallet.entity.Client;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MovementRequest {
    private String idWallet;
    private BigDecimal amount;
    private String type;
    private Client client;
    private LocalDateTime date;
}
