package com.example.sharekernel.domain.financial;

import com.example.sharekernel.domain.base.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.util.Objects;

public class Price implements ValueObject {

    @JsonProperty("currency")
    private final Currency currency;

    @JsonProperty("amount")
    private final Integer amount;

    @JsonCreator
    public Price(@NotNull @JsonProperty("currency") Currency currency,@JsonProperty("amount") Integer amount) {
        this.currency = Objects.requireNonNull(currency,"currency must not be null");
        this.amount = amount;
    }
}
