package com.HybernatePart3Assignment.jpa3.Hybernate3Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hybernate3AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hybernate3AssignmentApplication.class, args);
	}

}
/*

    Create a class Address for Author with instance variables streetNumber, location, State.
    Create instance variable of Address class inside Author class and save it as embedded object.
    Introduce a List of subjects for author.
    Persist 3 subjects for each author.
    Create an Entity book with an instance variable bookName.
    Implement One to One mapping between Author and Book.
    Implement One to Many Mapping between Author and Book(Unidirectional, BiDirectional and without additional table ) and implement cascade save.
    Implement Many to Many Mapping between Author and Book.
    Which method on the session object can be used to remove an object from the cache?
    What does @transactional annotation do?

 */