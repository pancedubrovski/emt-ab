package com.example.sharekernel.domain.financial;


import com.example.sharekernel.domain.base.ValueObject;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final float amount;

    public Money(@NotNull Currency currency,@NotNull float amount) {
        this.currency = currency;
        this.amount = amount;
    }



    public static Money valueOf(Currency currency, float amount){
        return new Money(currency,amount);
    }
    private Money(){
        this.currency = null;
        this.amount = 0;
    }


    public Money add(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("can add money with different currency");
        }
        return new Money(currency,amount+money.amount);
    }
    public Money subtract(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("can't subtract with different currency ");
        }
        return new Money(currency,amount-money.amount);
    }
    public Money multiply(int m){
        return new Money(currency,amount*m);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Float.compare(money.amount, amount) == 0 &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
