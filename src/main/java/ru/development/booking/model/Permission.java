package ru.development.booking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private List<Role> roles;
}
