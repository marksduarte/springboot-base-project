package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseSearchFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@AllArgsConstructor
public class UserSearchFilter extends BaseSearchFilter<User> {
    private String username;
    private String email;

    @Override
    public Specification<User> toSpecification() {
        return new UserSpec(this);
    }
}
