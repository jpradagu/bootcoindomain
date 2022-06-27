package com.nttdata.bootcamp.bootcoindomain.event;

import lombok.Data;
import lombok.ToString;

/** EVent.*/
@Data
@ToString
public abstract class Event<T> {
  private T data;
}
