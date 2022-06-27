package com.nttdata.bootcamp.bootcoindomain.model;

import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

/**
 * Purchase.
 */
@Data
@ToString
public class PurchaseCoin {
  private String id;
  private String tariffId;
  private BigDecimal amount;
  private String methodPayment;
  private String receivingNumberId;
  private String customerWalletId;
  private String numberTransaction;
  private BigDecimal requestedAmount;
  private BigDecimal purchaseAmount;
  private BigDecimal saleAmount;
}
