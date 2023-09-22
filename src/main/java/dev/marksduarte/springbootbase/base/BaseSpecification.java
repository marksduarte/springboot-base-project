package dev.marksduarte.springbootbase.base;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<F extends BaseSearchFilterDTO<E>, E extends BaseEntity<?>> implements Specification<E> {

    protected final F searchFilter;

    protected BaseSpecification(F searchFilter) {
        this.searchFilter = searchFilter;
    }

}
