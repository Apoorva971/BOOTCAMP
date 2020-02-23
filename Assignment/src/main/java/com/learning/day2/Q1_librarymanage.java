package main.java.com.learning.day2;

public class Q1_librarymanage {
        public static void main(String[] args) {
        }
    }
    enum BookStatus {

        AVAILABLE,

        NOTAVAILABLE

    }

    enum AccountStatus {

        ACTIVE,

        CLOSED

    }

    //Person Class

    class Person {

        private String name;

        private String phone;

    }

    //Account Class

   abstract class Account {

        private String id;

        private String password;

        private AccountStatus status;

        private Person person;

    }

  //  Librarian Class

       class Librarian extends Account {

         public void addBookItem(){}

        ///Public

        void removeBookItem(){}

    }

//    MemberClass

    class Member extends Account {

        private int totalBooksIssued;

        public void getTotalBooksIssued() {

        }

        public void IssueBooks(){}

        public void returnBooks(){}

        private void checkForFine(){}

    }

    class BookIssue {

        private String memberId;

        public static void getIssueDetails() {

        }
    }

    //Fine Class

     class Fine {
        private String creationDate;
        private String bookname;
        private String memberId;
        public static void collectFine(){}
    }
    //Book Class
     abstract class Book {
        private String title;

        private String subject;

        private String publisher;
    }
    class Books extends Book {

        private float price;

        private BookStatus status;

        public void checkoutBook() {

        }
    }
