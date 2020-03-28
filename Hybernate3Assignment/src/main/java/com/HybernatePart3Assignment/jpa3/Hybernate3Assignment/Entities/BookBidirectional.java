package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;

import javax.persistence.*;

@Entity
public class BookBidirectional {
    @Id
    @GeneratedValue
    int id;
    String bookName;
    @ManyToOne
    @JoinColumn(name = "author_book")
    private AuthorBidirectional authorBidirectional;

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

    public AuthorBidirectional getAuthorBidirectional() {
        return authorBidirectional;
    }

    public void setAuthorBidirectional(AuthorBidirectional authorBidirectional) {
        this.authorBidirectional = authorBidirectional;
    }
}