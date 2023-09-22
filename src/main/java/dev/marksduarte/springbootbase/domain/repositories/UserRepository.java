package dev.marksduarte.springbootbase.domain.repositories;

import dev.marksduarte.springbootbase.base.BaseRepository;
import dev.marksduarte.springbootbase.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {
}
