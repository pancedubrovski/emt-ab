package com.example.sharekernel.domain.identity;

import com.example.sharekernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class FullName implements ValueObject {

    private final String firstName;
    private final String lastName;
    private final String passport;

    public FullName(String firstName,  String lastName,String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
    }

    @SuppressWarnings("unuse")
    protected FullName(){
        firstName = null;
        lastName = null;
        passport = null;
    }

    public static FullName valueOf(String firstName,String lastName,String passport){
        return new FullName(firstName,lastName,passport);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(lastName, fullName.lastName) &&
                Objects.equals(passport, fullName.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, passport);
    }
}
