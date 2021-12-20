package ru.development.booking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String refreshToken;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;
}
