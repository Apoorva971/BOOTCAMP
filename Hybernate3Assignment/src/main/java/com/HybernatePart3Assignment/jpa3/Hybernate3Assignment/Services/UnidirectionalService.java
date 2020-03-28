package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorUnidirectional;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.BookUnidirectional;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UnidirectionalService {

        public AuthorUnidirectional createData()
        {
            AuthorUnidirectional authorUnidirectional = new AuthorUnidirectional();
            authorUnidirectional.setName("Apoorva Garg");
            BookUnidirectional bookUnidirectional = new BookUnidirectional();
            bookUnidirectional.setBookName("happy new year");
            BookUnidirectional bookUnidirectional1 = new BookUnidirectional();
            bookUnidirectional1.setBookName("happy hour");
            HashSet<BookUnidirectional> bookUnidirectionals = new HashSet<>();
            bookUnidirectionals.add(bookUnidirectional);
            bookUnidirectionals.add(bookUnidirectional1);
            authorUnidirectional.setBookUnidirectionals(bookUnidirectionals);
            return authorUnidirectional;
        }
    }

