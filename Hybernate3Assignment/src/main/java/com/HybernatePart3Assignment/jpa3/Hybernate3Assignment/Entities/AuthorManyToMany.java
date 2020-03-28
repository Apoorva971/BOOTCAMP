package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;
import javax.persistence.*;
import java.util.Set;

@Entity
public class AuthorManyToMany {
    @Id
    @GeneratedValue
    int id;
    String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<BookManyToMany> bookManyToManySet;

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

    public Set<BookManyToMany> getBookManyToManySet() {
        return bookManyToManySet;
    }

    public void setBookManyToManySet(Set<BookManyToMany> bookManyToManySet) {
        this.bookManyToManySet = bookManyToManySet;
    }
}