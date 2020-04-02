package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setAddressLine1("address line 1");
        publisher.setAddressLine2("address line 2");
        publisher.setCity("Durham");
        publisher.setState("NC");
        publisher.setZip("123456");
        publisherRepository.save(publisher);

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");
        ddd.setPublisher(publisher);

        Set<Book> books = new HashSet<Book>();
        books.add(ddd);
        eric.setBooks(books);

        Set<Author> authors = new HashSet<Author>();
        authors.add(eric);
        ddd.setAuthors(authors);

        publisher.setBooks(eric.getBooks());

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        System.out.println("started in bootstrap");
        System.out.println("number of books:"+bookRepository.count());
        System.out.println("number of publishers:"+publisherRepository.count());
        System.out.println("publisher number of books:"+publisher.getBooks().size());

    }
}
