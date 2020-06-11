package com.udemy.first.spring5webapp.bootstrap;

import com.udemy.first.spring5webapp.domain.Author;
import com.udemy.first.spring5webapp.domain.Book;
import com.udemy.first.spring5webapp.domain.Publisher;
import com.udemy.first.spring5webapp.repositories.AuthorRepository;
import com.udemy.first.spring5webapp.repositories.BookRepository;
import com.udemy.first.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("Cool publisher");
        publisher.setAddressLine1("Cool street 21");
        publisher.setCity("Lviv");
        publisher.setState("Lviv Region");
        publisher.setZip("123");

        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Publisher count: " + publisherRepository.count());

        Author taras = new Author("Taras", "Rumezhak");
        Book someBook = new Book("My book", "123456789");
        taras.getBooks().add(someBook);
        someBook.getAuthors().add(taras);

        someBook.setPublisher(publisher);
        publisher.getBooks().add(someBook);

        authorRepository.save(taras);
        bookRepository.save(someBook);
        publisherRepository.save(publisher);

        Author max = new Author("Max", "Johnson");
        Book anotherBook = new Book("Cool book", "123456790");
        max.getBooks().add(anotherBook);
        anotherBook.getAuthors().add(max);

        anotherBook.setPublisher(publisher);
        publisher.getBooks().add(anotherBook);

        authorRepository.save(max);
        bookRepository.save(anotherBook);
        publisherRepository.save(publisher);

        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }
}
