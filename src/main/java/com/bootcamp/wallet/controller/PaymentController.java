package com.bootcamp.wallet.controller;

import com.bootcamp.wallet.dto.PaymentDto;
import com.bootcamp.wallet.request.MovementRequest;
import com.bootcamp.wallet.resource.PaymentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/wallet/payment")
public class PaymentController {
    @Autowired
    private PaymentResource paymentResource;

    @PostMapping
    public Mono<PaymentDto> create(@RequestBody PaymentDto paymentDto) {
        return paymentResource.create(paymentDto);
    }

    @PutMapping
    public Mono<PaymentDto> update(@RequestBody PaymentDto paymentDto) {
        return paymentResource.update(paymentDto);
    }

    @GetMapping
    public Flux<PaymentDto> findAll() {
        return paymentResource.findAll();
    }

    @GetMapping("/{id}")
    public Mono<PaymentDto> findById(@PathVariable String id) {
        return paymentResource.findById(id);
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody PaymentDto paymentDto){
        return paymentResource.delete(paymentDto);
    }

}
