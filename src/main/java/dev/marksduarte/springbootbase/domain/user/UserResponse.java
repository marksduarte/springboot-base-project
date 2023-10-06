package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse extends BaseResponse {
    private UUID id;

    @Length(max = 100, message = "Username must have at least {max} characters.")
    @NotBlank(message = "Username is required.")
    private String username;

    @Length(max = 100, message = "E-mail must have at least {max} characters.")
    @NotBlank(message = "E-mail is required.")
    private String email;

    @Length(max = 100, message = "Password must have at least {max} characters.")
    @NotBlank(message = "Password is required.")
    private String password;
}
