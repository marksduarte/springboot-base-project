package dev.marksduarte.springbootbase.base;

import dev.marksduarte.springbootbase.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;


@Log4j2
@Service
@Validated
public abstract class BaseService<E extends BaseEntity<?>, V, R extends JpaRepository<E, V>> {
    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    @Transactional
    public E findById(@NotNull V id) {
        Optional<E> entity = this.repository.findById(id);
        return this.extractEntity(entity, id);
    }

    @Transactional
    public E save(@NotNull E entity) {
        validateAndPrepareForSave(entity);
        var saved = this.persist(entity);
        this.afterSaved(saved);
        return saved;
    }

    @Transactional
    public E update(@NotNull E entity) {
        checkIdForUpdate(entity);
        validateAndPrepareForUpdate(entity);
        var updated = this.persist(entity);
        this.afterUpdated(updated);
        return updated;
    }

    @Transactional
    public void delete(@NotNull V id) {
        this.repository.deleteById(id);
    }

    public Collection<E> findAll() {
        return this.repository.findAll();
    }

    public Page<E> findAll(@NotNull E entity, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<E> example = Example.of(entity, matcher);
        return this.repository.findAll(example, pageable);
    }

    protected void validateAndPrepareForSave(@NotNull E entity) {
    }

    protected void validateAndPrepareForUpdate(@NotNull E entity) {
    }

    protected void afterSaved(@NotNull E entity) {

    }

    protected void afterUpdated(@NotNull E entity) {

    }

    protected E extractEntity(Optional<E> opt, V id) {
        return opt.orElseThrow(() -> new ObjectNotFoundException(getEntityNotFoundMessage(id)));
    }

    protected String getEntityNotFoundMessage(V id) {
        return String.format("Object not found! Id: %s", id.toString());
    }

    protected void checkIdForUpdate(@NotNull E entity) {
        if (Objects.isNull(entity.getId())) {
            String message = "Error on update: ID must not be null!";
            log.error("ERROR: {} - {}", this.getClass().getSimpleName(), message);
            throw new IllegalArgumentException(message);
        }
    }

    private E persist(@NotNull @Valid E entity) {
        return this.repository.save(entity);
    }
}
