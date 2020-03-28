package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;
import javax.persistence.*;

@Entity
public class BookWithoutTable {
    @Id
    @GeneratedValue
    int id;
    String bookName;
    @ManyToOne
    @JoinColumn(name = "author_book")
    private AuthorWithoutTable author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public AuthorWithoutTable getAuthor() {
        return author;
    }

    public void setAuthor(AuthorWithoutTable author) {
        this.author = author;
    }
}