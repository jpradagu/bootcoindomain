package com.nttdata.bootcamp.bootcoindomain.service;

import com.nttdata.bootcamp.bootcoindomain.model.Tariff;
import com.nttdata.bootcamp.bootcoindomain.repository.TariffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TariffService.
 */
@Service
@Slf4j
public class TariffService {

  @Autowired
  private TariffRepository tariffRepository;


  /**
   * findAll Tariff.
   */
  public Flux<Tariff> findAll() {
    log.info("TariffService findAll-> ");
    return tariffRepository.findAll();
  }

  /**
   * findById Tariff.
   */
  public Mono<Tariff> findById(String id) {
    return tariffRepository.findById(id);
  }

  /**
   * create Tariff.
   */
  public Mono<Tariff> create(Tariff tariff) {
    return tariffRepository.save(tariff);
  }


  /**
   * update Tariff.
   */
  public Mono<Tariff> update(Tariff tariff, String id) {
    log.info("TariffService update -> ");
    return tariffRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Tariff not found")))
        .flatMap(p -> tariffRepository.findByName(tariff.getName())
            .switchIfEmpty(Mono.defer(() -> tariffRepository.save(parseIdToTariff(tariff, id))))
            .flatMap(obj -> {
              if (obj != null) {
                if (obj.getId().equals(id)) {
                  return tariffRepository.save(parseIdToTariff(tariff, id));
                } else {
                  return Mono.error(new RuntimeException("Tariff exist other side!"));
                }
              } else {
                return tariffRepository.save(parseIdToTariff(tariff, id));
              }
            }));
  }

  private Tariff parseIdToTariff(Tariff tariff, String id) {
    tariff.setId(id);
    return tariff;
  }

  /**
   * delete Tariff.
   */
  public Mono<Void> delete(Tariff tariff) {
    return tariffRepository.delete(tariff);
  }


}
