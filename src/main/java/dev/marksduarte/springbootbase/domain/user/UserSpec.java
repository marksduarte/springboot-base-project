package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class UserSpec extends BaseSpecification<UserSearchFilter, User> {

    public UserSpec(UserSearchFilter searchFilter) {
        super(searchFilter);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();

        if (StringUtils.hasText(searchFilter.getEmail())) {
            predicates.add(cb.like(root.get("email"), searchFilter.getEmail()));
        }

        if (StringUtils.hasText(searchFilter.getUsername())) {
            predicates.add(cb.like(root.get("username"), searchFilter.getUsername()));
        }

        return query.where(cb.and(predicates.toArray(new Predicate[0]))).getRestriction();
    }
}
