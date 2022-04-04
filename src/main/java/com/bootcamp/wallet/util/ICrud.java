package com.bootcamp.wallet.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICrud <T, V>{
    Mono<T> save(T t);
    Flux<T> findAll();
    Mono<T> findById(V v);
    Mono<Void> deleteById(V v);
}
