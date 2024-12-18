package cl.desafio.java.pstaubr.entity;


import cl.desafio.java.pstaubr.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Pablo Staub R
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity<UserDto, UserEntity> {

    private static final long serialVersionUID = -1133417054956907238L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "type_token", nullable = false)
    private String typeToken;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "disabled", nullable = false)
    private Boolean disabled;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_phones", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "phone_id")})
    private List<PhonesEntity> phones = new ArrayList<>();

    @Override
    public UserDto normalizeObj(UserEntity entity) {

        UserDto obj = new UserDto();

        obj.setId(entity.getId());
        obj.setEmail(entity.getEmail());
        if (entity.getDisabled()) {
            obj.setIsactive(Boolean.FALSE);
        } else {
            obj.setIsactive(Boolean.TRUE);
        }
        obj.setLast_login(entity.getLastLogin());
        obj.setModified(entity.getModifiedAt());
        obj.setCreated(entity.getCreatedAt());
        obj.setToken(entity.getTypeToken() + " " + entity.getToken());
        obj.setPhones(new PhonesEntity().normalizeObjList(entity.getPhones()));
        return obj;
    }

    @Override
    public List<UserDto> normalizeObjList(List<UserEntity> entityList) {

        List<UserDto> result = new ArrayList<UserDto>();

        for (UserEntity userEntity : entityList) {

            result.add(normalizeObj(userEntity));

        }

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(disabled, email, id, lastLogin, name, password, token);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserEntity other = (UserEntity) obj;
        return Objects.equals(disabled, other.disabled) && Objects.equals(email, other.email)
                && Objects.equals(id, other.id) && Objects.equals(lastLogin, other.lastLogin)
                && Objects.equals(name, other.name) && Objects.equals(password, other.password)
                && Objects.equals(token, other.token);
    }
}