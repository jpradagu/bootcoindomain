package com.nttdata.bootcamp.bootcoindomain.controller;

import com.nttdata.bootcamp.bootcoindomain.exception.ResumenError;
import com.nttdata.bootcamp.bootcoindomain.model.Tariff;
import com.nttdata.bootcamp.bootcoindomain.service.TariffService;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Payment Controller.
 */
@RestController
@RequestMapping("/api/boot-coin/tariff")
@Slf4j
public class TariffController {

  private final TariffService tariffService;

  public TariffController(TariffService tariffService) {
    this.tariffService = tariffService;
  }

  /**
   * FindAll Payment.
   */
  @GetMapping
  public Mono<ResponseEntity<Flux<Tariff>>> findAll() {
    log.info("TariffController findAll ->");
    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
        .body(tariffService.findAll()));
  }

  /**
   * Find Payment.
   */
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Tariff>> findById(@PathVariable String id) {
    log.info("TariffController findById ->");
    return tariffService.findById(id)
        .map(ce -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * create Payment.
   */
  @PostMapping
  public Mono<ResponseEntity<Map<String, Object>>> create(
      @Valid @RequestBody Mono<Tariff> paymentMono) {
    log.info("TariffController create ->");
    Map<String, Object> result = new HashMap<>();
    return paymentMono.flatMap(c -> tariffService.create(c)
            .map(p -> ResponseEntity.created(URI.create("/api/boot-coin/tariff/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON).body(result)))
        .onErrorResume(ResumenError::errorResumenException);
  }

  /**
   * update Payment.
   */
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Map<String, Object>>> update(
      @Valid @RequestBody Mono<Tariff> paymentMono, @PathVariable String id) {
    log.info("TariffController create ->");
    Map<String, Object> result = new HashMap<>();
    return paymentMono.flatMap(c -> tariffService.update(c, id)
            .flatMap(p -> {
              result.put("data", p);
              return Mono.just(ResponseEntity.ok().body(result));
            }))
        .onErrorResume(ResumenError::errorResumenException);
  }

  /**
   * Delete Payment.
   */
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    log.info("TariffController delete ->");
    return tariffService.findById(id)
        .flatMap(e -> tariffService.delete(e)
            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
