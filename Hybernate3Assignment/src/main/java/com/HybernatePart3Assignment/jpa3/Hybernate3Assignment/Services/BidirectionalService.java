package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorBidirectional;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.BookBidirectional;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BidirectionalService

{
    public AuthorBidirectional createData()
    {
        AuthorBidirectional authorBidirectional = new AuthorBidirectional();
        authorBidirectional.setName("Apoorva Garg");
        BookBidirectional bookBidirectional = new BookBidirectional();
        bookBidirectional.setBookName("learning C");
        BookBidirectional bookBidirectional1 = new BookBidirectional();
        bookBidirectional1.setBookName("hi how are you");
        HashSet<BookBidirectional> bookBidirectionals = new HashSet<>();
        bookBidirectionals.add(bookBidirectional);
        bookBidirectionals.add(bookBidirectional1);
        bookBidirectional.setAuthorBidirectional(authorBidirectional);
        bookBidirectional1.setAuthorBidirectional(authorBidirectional);
        authorBidirectional.setBookBidirectionals(bookBidirectionals);
        return authorBidirectional;
    }

}