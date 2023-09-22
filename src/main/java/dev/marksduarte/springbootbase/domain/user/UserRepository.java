package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {
}
