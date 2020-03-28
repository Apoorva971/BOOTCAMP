package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Controller;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.Author;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Repository.AuthorRepository;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @GetMapping("/savingdetails")
    public String savingAuthorDetailsAlongWithAddressBookAndSubjects() {
        Author author = authorService.createAuthorData();
        authorRepository.save(author);
        return "added records of the author successfully";
    }

}
/*
///////////////Output//////////////////
mysql> select *from author;
+----+-------+------------------+---------------+--------------+-------------+
| id | state | location         | street_number | name         | author_book |
+----+-------+------------------+---------------+--------------+-------------+
| 78 | Noida | Knowledgw park 2 | 39B           | Apoorva Garg |          79 |
+----+-------+------------------+---------------+--------------+-------------+

 */