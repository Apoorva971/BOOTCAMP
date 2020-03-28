package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorManyToMany;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.BookManyToMany;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository.AuthorManyToManyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ManyToManyService
{
    @Autowired
    AuthorManyToManyRepository authorManyToManyRepository;

    public void createData()
    {
        AuthorManyToMany authorManyToMany = new AuthorManyToMany();
        authorManyToMany.setName("Apoorva Garg");
        BookManyToMany bookManyToMany = new BookManyToMany();
        bookManyToMany.setBookName("hi how are you");
        BookManyToMany bookManyToMany1 = new BookManyToMany();
        bookManyToMany1.setBookName("i am good");
        HashSet<BookManyToMany> bookManyToManyHashSet = new HashSet<>();
        bookManyToManyHashSet.add(bookManyToMany);
        bookManyToManyHashSet.add(bookManyToMany1);
        authorManyToMany.setBookManyToManySet(bookManyToManyHashSet);
        AuthorManyToMany authorManyToMany1 = new AuthorManyToMany();
        authorManyToMany1.setName("Shubhanshi Tyagi");
        HashSet<AuthorManyToMany> authorManyToManyHashSet = new HashSet<>();
        authorManyToManyHashSet.add(authorManyToMany);
        authorManyToManyHashSet.add(authorManyToMany1);
        bookManyToMany.setAuthorManyToManySet(authorManyToManyHashSet);
        authorManyToManyRepository.save(authorManyToMany);
        authorManyToManyRepository.save(authorManyToMany1);

    }
}