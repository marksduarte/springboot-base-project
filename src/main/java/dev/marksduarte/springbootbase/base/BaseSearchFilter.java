package dev.marksduarte.springbootbase.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSearchFilter<E> {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;
    private static final String DEFAULT_SORT_ORDER = "nome";

    private Pageable pageable;

    public abstract Specification<E> toSpecification();

    public Pageable getPageable() {
        if (pageable != null) {
            return pageable;
        }

        return PageRequest.of(
                DEFAULT_PAGE_NUMBER,
                DEFAULT_PAGE_SIZE,
                Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_SORT_ORDER));
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
