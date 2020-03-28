package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.AuthorWithoutTable;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.BookWithoutTable;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class WithoutTableService {

    public AuthorWithoutTable createData() {
        AuthorWithoutTable authorWithoutTable = new AuthorWithoutTable();
        authorWithoutTable.setName("Apoorva garg");
        BookWithoutTable bookWithoutTable = new BookWithoutTable();
        bookWithoutTable.setBookName("Aant man");
        BookWithoutTable bookWithoutTable1 = new BookWithoutTable();
        bookWithoutTable1.setBookName("happy day");
        HashSet<BookWithoutTable> bookWithoutTables = new HashSet<>();
        bookWithoutTables.add(bookWithoutTable1);
        bookWithoutTables.add(bookWithoutTable);
        bookWithoutTable.setAuthor(authorWithoutTable);
        bookWithoutTable1.setAuthor(authorWithoutTable);
        authorWithoutTable.setBookWithoutTables(bookWithoutTables);
        return authorWithoutTable;
    }
}

