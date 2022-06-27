package com.nttdata.bootcamp.bootcoindomain.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Tariff.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tariffs")
public class Tariff {
  @Id
  private String id;
  @NotNull
  private String name;
  @NotNull
  private BigDecimal purchaseRate;
  @NotNull
  private BigDecimal sellingRate;

}
