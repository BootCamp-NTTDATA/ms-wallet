package com.bootcamp.wallet.dto;

import com.bootcamp.wallet.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionDto {
    private String idReceiver;
    private BigDecimal amount;
    private String movementType; //tipo de movimiento (envio o recepcion)
    private String idTransmitter;
    private Client clientTransmitter;
    private Client clientReceiver;


    @Override
    public String toString() {
        return "WalletTransactionDto{" +
                "idReceiver='" + idReceiver + '\'' +
                ", amount=" + amount +
                ", movementType='" + movementType + '\'' +
                ", idTransmitter='" + idTransmitter + '\'' +
                ", clientReceiver=" + clientReceiver +
                ", clientTransmitter=" + clientTransmitter +
                '}';
    }
}
