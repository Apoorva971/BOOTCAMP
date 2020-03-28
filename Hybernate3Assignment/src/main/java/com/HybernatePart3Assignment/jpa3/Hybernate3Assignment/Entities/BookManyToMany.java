package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class BookManyToMany {
    @Id
    @GeneratedValue
    int id;
    String bookName;
    @ManyToMany(mappedBy = "bookManyToManySet")
    private Set<AuthorManyToMany> authorManyToManySet;

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

    public Set<AuthorManyToMany> getAuthorManyToManySet() {
        return authorManyToManySet;
    }

    public void setAuthorManyToManySet(Set<AuthorManyToMany> authorManyToManySet) {
        this.authorManyToManySet = authorManyToManySet;
    }
}