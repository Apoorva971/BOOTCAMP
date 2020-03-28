package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;
import javax.persistence.*;
import java.util.Set;

@Entity
public class AuthorUnidirectional {
    @Id
    @GeneratedValue
    int id;
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BookUnidirectional> bookUnidirectionals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookUnidirectional> getBookUnidirectionals() {
        return bookUnidirectionals;
    }

    public void setBookUnidirectionals(Set<BookUnidirectional> bookUnidirectionals) {
        this.bookUnidirectionals = bookUnidirectionals;

    }
}