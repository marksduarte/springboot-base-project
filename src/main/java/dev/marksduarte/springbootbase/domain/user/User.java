package dev.marksduarte.springbootbase.domain.user;

import dev.marksduarte.springbootbase.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "tb_user", schema = "auth")
public class User extends BaseEntity<UUID> implements Serializable {

    @Id
    @Column(name = "id_user", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "st_username", length = 100, nullable = false)
    private String username;

    @Column(name = "st_email", length = 100, nullable = false)
    private String email;

    @Column(name = "st_password", length = 500, nullable = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
