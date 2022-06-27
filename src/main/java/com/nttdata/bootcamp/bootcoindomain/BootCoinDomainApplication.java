package com.nttdata.bootcamp.bootcoindomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * YankidomainApplication.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BootCoinDomainApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootCoinDomainApplication.class, args);
  }

}
