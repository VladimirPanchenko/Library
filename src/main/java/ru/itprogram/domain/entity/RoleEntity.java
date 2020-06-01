package ru.itprogram.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "security_role")
public class RoleEntity implements GrantedAuthority {

    @Id
    @SequenceGenerator(name = "seq_role_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_id")
    private Long id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    @Override
    public String getAuthority() {
        return getName();
    }
}
