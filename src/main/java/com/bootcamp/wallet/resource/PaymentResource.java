package com.bootcamp.wallet.resource;

import com.bootcamp.wallet.dto.PaymentDto;
import com.bootcamp.wallet.dto.WalletTransactionDto;
import com.bootcamp.wallet.entity.Payment;
import com.bootcamp.wallet.repository.MongoDBDao;
import com.bootcamp.wallet.service.IPaymentService;

import com.bootcamp.wallet.util.MapperUtil;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PaymentResource extends MapperUtil {
    Logger logger = LoggerFactory.getLogger(PaymentResource.class);

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private MongoDBDao mongoDBDao;

    public Mono<PaymentDto> create(PaymentDto paymentDto) {
        Payment payment = map(paymentDto, Payment.class);
        Mono<Payment> entity = paymentService.save(payment);
        return entity.map(x -> map(x, PaymentDto.class));
    }

    public Mono<PaymentDto> update(PaymentDto paymentDto) {
        return paymentService.findById(paymentDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(p -> {
                    Payment payment = map(paymentDto, Payment.class);
                    payment.setUpdateAt(LocalDateTime.now());
                    return  paymentService.save(payment)
                            .map(x -> map(x, PaymentDto.class));
                });
    }

    public Mono<PaymentDto> findById(String id) {
        return paymentService.findById(id)
                .switchIfEmpty(Mono.error(new Exception()))
                .map(x -> map(x, PaymentDto.class));
    }

    public Flux<PaymentDto> findAll(){
        return paymentService.findAll().map(x -> map(x, PaymentDto.class));
    }

    public Mono<Void> delete(PaymentDto paymentDto) {
        return paymentService.findById(paymentDto.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> paymentService.deleteById(paymentDto.getId()));
    }

    @KafkaListener(topics = "topic_ms_wallet", groupId = "group_id")
    public void walletTransaction(WalletTransactionDto walletTransactionDto) {
        logger.info("consuming Message {}", walletTransactionDto);
        createPaymentTransmitter(walletTransactionDto);
        createPaymentReceiver(walletTransactionDto);
    }

    @KafkaListener(topics = "ms_wallet_recharge", groupId = "group_id")
    public void rechargeWalletTransaction(WalletTransactionDto walletTransactionDto) {
        logger.info("consuming Message {}", walletTransactionDto);
        createRechargeByAccount(walletTransactionDto);

    }

    public void createRechargeByAccount(WalletTransactionDto walletTransactionDto){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setIdWallet(walletTransactionDto.getIdTransmitter());
        paymentDto.setAmount(walletTransactionDto.getAmount());
        paymentDto.setType("recharged-by-account");
        paymentDto.setDate(LocalDateTime.now());
        paymentDto.setClient(walletTransactionDto.getClientTransmitter());
        Payment payment = map(paymentDto,Payment.class);
        mongoDBDao.save(Mono.just(payment));
    }

    public void createPaymentTransmitter(WalletTransactionDto walletTransactionDto){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setIdWallet(walletTransactionDto.getIdTransmitter());
        paymentDto.setAmount(walletTransactionDto.getAmount());
        paymentDto.setType("payment");
        paymentDto.setDate(LocalDateTime.now());
        paymentDto.setClient(walletTransactionDto.getClientReceiver());
        Payment payment = map(paymentDto,Payment.class);
        mongoDBDao.save(Mono.just(payment));
    }
    public void createPaymentReceiver(WalletTransactionDto walletTransactionDto){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setIdWallet(walletTransactionDto.getIdReceiver());
        paymentDto.setAmount(walletTransactionDto.getAmount());
        paymentDto.setType("receiver");
        paymentDto.setDate(LocalDateTime.now());
        paymentDto.setClient(walletTransactionDto.getClientTransmitter());
        Payment payment = map(paymentDto,Payment.class);
        mongoDBDao.save(Mono.just(payment));
    }
}
