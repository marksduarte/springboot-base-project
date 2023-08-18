package dev.marksduarte.springbootbase.base;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

public interface ControllerHttpMethods<T extends BaseDTO> {

    @GetMapping
    ResponseEntity<Collection<T>> findAll();

    @GetMapping("paged")
    ResponseEntity<Page<T>> findPaged(Pageable pageable);

    @GetMapping("{id}")
    ResponseEntity<T> findById(@PathVariable("id") UUID id);

    @PostMapping
    ResponseEntity<T> save(@Valid @RequestBody T dto);

    @PutMapping
    ResponseEntity<T> update(@Valid @RequestBody T dto);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);
}
