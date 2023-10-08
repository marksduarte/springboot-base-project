package dev.marksduarte.springbootbase.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Classe para receber os critérios utilizados na pesquisa dinâmica.<br>
 * Exemplo de URL Params: <br>
 * <a href="http://localhost:8080/auth/user/paged/filter?params%5B0%5D.key=username&params%5B0%5D.value=marks&params%5B0%5D.condition=MATCH">http://localhost:8080/auth/user/paged/filter?params[0].key=username&params[0].value=marks&params[0].condition=MATCH</a>
 * @param <E>
 */
public class SearchFilter<E> {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;
    private static final String DEFAULT_SORT_ORDER = "id";

    private Pageable pageable;
    private List<SearchFilterParam> params;

    public Specification<E> toSpecification() {
        return new DynamicSpecification<>(this.params);
    }

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

    public List<SearchFilterParam> getParams() {
        return params;
    }

    public void setParams(List<SearchFilterParam> params) {
        this.params = params;
    }
}
