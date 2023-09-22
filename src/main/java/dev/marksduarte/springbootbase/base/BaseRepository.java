package dev.marksduarte.springbootbase.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E, T> extends JpaRepository<E, T>, JpaSpecificationExecutor<E> {
}
