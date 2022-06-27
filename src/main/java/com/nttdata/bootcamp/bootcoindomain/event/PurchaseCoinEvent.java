package com.nttdata.bootcamp.bootcoindomain.event;

import com.nttdata.bootcamp.bootcoindomain.model.PurchaseCoin;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PurchaseEvent.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseCoinEvent extends Event<PurchaseCoin> {
}
