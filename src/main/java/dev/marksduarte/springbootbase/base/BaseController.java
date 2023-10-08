package dev.marksduarte.springbootbase.base;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

public abstract class BaseController<E extends BaseEntity<?>, T extends BaseResponse> {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<Collection<T>> findAll();

    @GetMapping(value = "paged", produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<Page<T>> findPaged(SearchFilter<E> filter, Pageable pageable);

    @GetMapping(value = "paged/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<Page<T>> findPagedWithFilter(SearchFilter<E> filter, Pageable pageable);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> findById(@PathVariable("id") UUID id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> save(@Valid @RequestBody T dto);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<T> update(@Valid @RequestBody T dto);

    @DeleteMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
