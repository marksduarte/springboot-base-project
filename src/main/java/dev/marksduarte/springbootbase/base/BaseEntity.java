package dev.marksduarte.springbootbase.base;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T extends Serializable> implements Serializable {

    public abstract T getId();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BaseEntity<?> other = (BaseEntity<?>) obj;
        return getId().equals(other.getId());
    }
}
