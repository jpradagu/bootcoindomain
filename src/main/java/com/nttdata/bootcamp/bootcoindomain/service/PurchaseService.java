package com.nttdata.bootcamp.bootcoindomain.service;

import com.nttdata.bootcamp.bootcoindomain.event.Event;
import com.nttdata.bootcamp.bootcoindomain.event.PurchaseCoinEvent;
import com.nttdata.bootcamp.bootcoindomain.model.PurchaseCoin;
import com.nttdata.bootcamp.bootcoindomain.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * PurchaseService.
 */
@Service
@Slf4j
public class PurchaseService {

  @Autowired
  private TariffRepository tariffRepository;

  @Autowired
  private KafkaTemplate<String, Event<?>> kafkaTemplate;

  /**
   * PurchaseCoin BrokerMessage.
   */
  @KafkaListener(topics = "purchase_coin",      containerFactory = "facPurchase")
  public void paymentWallet(Event<PurchaseCoin> purchase) {
    PurchaseCoinEvent purchaseEvent = (PurchaseCoinEvent) purchase;
    PurchaseCoin purchaseCoin = purchaseEvent.getData();
    // genera la proforma de compra
    log.info("receiver: ->" + purchaseCoin.toString());

    tariffRepository.findById(purchaseCoin.getTariffId()).map(tariff -> {
      purchaseCoin.setRequestedAmount(purchaseCoin.getAmount());
      purchaseCoin.setPurchaseAmount(purchaseCoin.getAmount().multiply(tariff.getPurchaseRate()));
      purchaseCoin.setSaleAmount(purchaseCoin.getAmount().multiply(tariff.getSellingRate()));
     // sendPurchaseCoin(purchaseCoin);
      return Mono.just(tariff);
    }).subscribe();
  }

  /**
   * sendPayment.
   */
//  private void sendPurchaseCoin(PurchaseCoin t) {
//    PurchaseCoinEvent event = new PurchaseCoinEvent();
//    event.setData(t);
//    log.info("Send updatePurchaseEvent -> {}", event);
//    this.kafkaTemplate.send("update_purchase_coin", event);
//  }
}
