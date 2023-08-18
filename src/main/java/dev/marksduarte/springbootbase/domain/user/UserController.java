package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.ControllerHttpMethods;
import dev.marksduarte.springbootbase.domain.user.UserDTO;
import dev.marksduarte.springbootbase.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "auth/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements ControllerHttpMethods<UserDTO> {

    @Autowired
    private UserService service;

    @Override
    public ResponseEntity<Collection<UserDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<Page<UserDTO>> findPaged(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @Override
    public ResponseEntity<UserDTO> findById(UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<UserDTO> save(UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @Override
    public ResponseEntity<UserDTO> update(UserDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
