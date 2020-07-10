package com.example.sharekernel.domain.base;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
public class DomainObjectId implements ValueObject {
    private String id;

    public DomainObjectId(String id){
         this.id = id;
    }
    @NonNull
    public static <ID extends DomainObjectId> ID randomId(Class<ID> idClass){
        Objects.requireNonNull(idClass,"Id class must not be null");
        try{
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex){
            throw new RuntimeException("Could not create new instance of "+idClass,ex);
        }
    }
}
