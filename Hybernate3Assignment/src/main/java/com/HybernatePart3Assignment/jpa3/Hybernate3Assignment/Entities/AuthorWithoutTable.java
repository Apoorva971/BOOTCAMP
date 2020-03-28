package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;

import javax.persistence.*;
import java.util.Set;
import java.util.concurrent.Semaphore;

@Entity
public class AuthorWithoutTable {
    @Id
    @GeneratedValue
    int id;
    String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<BookWithoutTable> bookWithoutTables;

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

    public Set<BookWithoutTable> getBookWithoutTables() {
        return bookWithoutTables;
    }

    public void setBookWithoutTables(Set<BookWithoutTable> bookWithoutTables) {
        this.bookWithoutTables = bookWithoutTables;
    }
}