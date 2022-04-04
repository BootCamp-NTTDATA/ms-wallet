package com.bootcamp.wallet.repository;

import com.bootcamp.wallet.entity.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IPaymentRepository extends ReactiveMongoRepository<Payment, String> {
}
