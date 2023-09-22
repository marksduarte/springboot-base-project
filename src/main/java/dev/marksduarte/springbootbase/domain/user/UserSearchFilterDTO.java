package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseSearchFilterDTO;
import dev.marksduarte.springbootbase.domain.repositories.specifications.UserSpec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@AllArgsConstructor
public class UserSearchFilterDTO extends BaseSearchFilterDTO<User> {
    private String username;
    private String email;

    @Override
    public Specification<User> toSpecification() {
        return new UserSpec(this);
    }
}
