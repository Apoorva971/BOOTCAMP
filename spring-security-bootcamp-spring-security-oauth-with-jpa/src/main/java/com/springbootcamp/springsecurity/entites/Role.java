package com.springbootcamp.springsecurity.entites;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Authority;
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public Role(Long id, String authority) {
        Id = id;
        Authority = authority;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}