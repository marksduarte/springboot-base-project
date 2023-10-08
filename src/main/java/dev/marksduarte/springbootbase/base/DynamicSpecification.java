package dev.marksduarte.springbootbase.base;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static dev.marksduarte.springbootbase.base.SearchFilterCondition.*;


@Validated
public class DynamicSpecification<T> implements Specification<T> {

    private final List<SearchFilterParam> filterParams = new ArrayList<>();

    public DynamicSpecification(@NotEmpty Collection<SearchFilterParam> filter) {
        this.filterParams.addAll(filter);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();
        for (var param : filterParams) {
            if (param.getValue() == null)
                continue;
            if (param.isOperation(GREATER_THAN)) {
                predicates.add(builder.greaterThan(root.get(param.getKey()), param.getValue().toString()));
            } else if (param.isOperation(LESS_THAN)) {
                predicates.add(builder.lessThan(root.get(param.getKey()), param.getValue().toString()));
            } else if (param.isOperation(GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
            } else if (param.isOperation(LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
            } else if (param.isOperation(NOT_EQUAL)) {
                predicates.add(builder.notEqual(root.get(param.getKey()), param.getValue()));
            } else if (param.isOperation(EQUAL)) {
                predicates.add(builder.equal(root.get(param.getKey()), param.getValue()));
            } else if (param.isOperation(MATCH)) {
                predicates.add(builder.like(builder.lower(root.get(param.getKey())),
                        "%" + param.getValue().toString().toLowerCase() + "%"));
            } else if (param.isOperation(MATCH_END)) {
                predicates.add(builder.like(builder.lower(root.get(param.getKey())),
                        param.getValue().toString().toLowerCase() + "%"));
            } else if (param.isOperation(MATCH_START)) {
                predicates.add(builder.like(builder.lower(root.get(param.getKey())),
                        "%" + param.getValue().toString().toLowerCase()));
            } else if (param.isOperation(IN)) {
                predicates.add(builder.in(root.get(param.getKey())).value(param.getValue()));
            } else if (param.isOperation(NOT_IN)) {
                predicates.add(builder.not(root.get(param.getKey())).in(param.getValue()));
            }
        }
        return query.where(builder.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }

    public DynamicSpecification<T> add(@NotNull SearchFilterParam param) {
        this.filterParams.add(param);
        return this;
    }

    public DynamicSpecification<T> addAll(@NotEmpty Collection<SearchFilterParam> params) {
        this.filterParams.addAll(params);
        return this;
    }
}
