package ru.development.booking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    private List<Permission> permissions;
}
