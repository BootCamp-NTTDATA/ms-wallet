package com.bootcamp.wallet.service;

import com.bootcamp.wallet.entity.Payment;
import com.bootcamp.wallet.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    IPaymentRepository paymentRepository;

    @Override
    public Mono<Payment> save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Flux<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Mono<Payment> findById(String s) {
        return paymentRepository.findById(s);
    }

    @Override
    public Mono<Void> deleteById(String s) {
        return paymentRepository.deleteById(s);
    }

}
