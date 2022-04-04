package com.bootcamp.wallet.repository;

import com.bootcamp.wallet.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MongoDBDao {
    @Autowired
    private ReactiveMongoTemplate template;

    public void save(Mono<Payment> payment){
        template.save(payment).subscribe();
    }
}
