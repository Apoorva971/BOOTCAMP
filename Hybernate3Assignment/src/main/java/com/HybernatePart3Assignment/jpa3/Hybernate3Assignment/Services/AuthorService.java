package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Services;

import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.Address;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.Author;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.Book;
import com.HybernatePart3Assignment.jpa3.Hybernate3Assignment.Entities.Subject;
import org.springframework.stereotype.Component;



import java.util.HashSet;

@Component
public class AuthorService
    
{
    /**creating a author and setting his book details and 3 subjects*/
    public Author createAuthorData() {
        Address address = new Address();
        address.setLocation("Knowledgw park 2");
        address.setState("Noida");
        address.setStreetNumber("39B");
        Author author = new Author();
        author.setName("Apoorva Garg");
        author.setAddress(address);
        Subject subject = new Subject();
        HashSet<Subject> subjects = new HashSet<>();
        subject.setSubjectName("spring");
        subjects.add(subject);
        Subject subject1 = new Subject();
        subject1.setSubjectName("dbms");
        subjects.add(subject1);
        Subject subject2 = new Subject();
        subject2.setSubjectName("java");
        subjects.add(subject2);
        subject.setAuthor(author);
        subject1.setAuthor(author);
        subject2.setAuthor(author);
        author.setSubjects(subjects);
        Book book = new Book();
        book.setBookName("learning C");
        author.setBook(book);
        book.setAuthor(author);
        return author;
    }

}