package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue
    int id;
    String name;
    @Embedded
    Address address;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_book")
    private Book book;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
