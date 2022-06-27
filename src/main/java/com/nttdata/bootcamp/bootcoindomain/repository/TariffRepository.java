package com.nttdata.bootcamp.bootcoindomain.repository;

import com.nttdata.bootcamp.bootcoindomain.model.Tariff;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * PaymentRepository.
 */
public interface TariffRepository extends ReactiveMongoRepository<Tariff, String> {

  /**
   * find by name.
   */
  Mono<Tariff> findByName(String name);
}
