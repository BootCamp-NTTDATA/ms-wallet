package com.bootcamp.wallet.service;

import com.bootcamp.wallet.entity.Payment;
import com.bootcamp.wallet.util.ICrud;
import reactor.core.publisher.Mono;

public interface IPaymentService extends ICrud<Payment, String> {

}
