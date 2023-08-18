package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class UserService extends BaseService<User, UUID, UserRepository> {

    protected UserService(UserRepository repository) {
        super(repository);
    }
}
