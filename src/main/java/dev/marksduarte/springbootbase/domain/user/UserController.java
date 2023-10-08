package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseController;
import dev.marksduarte.springbootbase.base.SearchFilter;
import dev.marksduarte.springbootbase.mapper.CustomMapper;
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
public class UserController extends BaseController<User, UserResponse> {

    private final UserService service;
    private final CustomMapper mapper;

    public UserController(UserService userService, CustomMapper mapper) {
        this.service = userService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Collection<UserResponse>> findAll() {
        return ResponseEntity.ok(mapper.mapCollection(service.findAll(), UserResponse.class));
    }

    @Override
    public ResponseEntity<Page<UserResponse>> findPaged(SearchFilter<User> filter, Pageable pageable) {
        var entity = mapper.map(filter, User.class);
        return ResponseEntity.ok(mapper.mapPage(service.findAll(entity, pageable), UserResponse.class));
    }

    @Override
    public ResponseEntity<Page<UserResponse>> findPagedWithFilter(SearchFilter<User> filter, Pageable pageable) {
        filter.setPageable(pageable);
        return ResponseEntity.ok(mapper.mapPage(service.findAll(filter), UserResponse.class));
    }

    @Override
    public ResponseEntity<UserResponse> findById(UUID id) {
        return ResponseEntity.ok(mapper.map(service.findById(id), UserResponse.class));
    }

    @Override
    public ResponseEntity<UserResponse> save(UserResponse dto) {
        var entity = mapper.map(dto, User.class);
        dto = mapper.map(service.save(entity), UserResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<UserResponse> update(UserResponse dto) {
        var entity = mapper.map(dto, User.class);
        dto = mapper.map(service.update(entity), UserResponse.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
