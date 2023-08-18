package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private UUID id;
    private String username;
    private String password;
}
