package com.peam.spring5webapp.bootstrap;

import com.peam.spring5webapp.model.Author;
import com.peam.spring5webapp.model.Book;
import com.peam.spring5webapp.model.Publisher;
import com.peam.spring5webapp.repositories.AuthorRepository;
import com.peam.spring5webapp.repositories.BookRepository;
import com.peam.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){

        Publisher guru = new Publisher("Refactoring.Guru","");

        Author alexander = new Author("Alexander", "Shvets");
        Book ddp = new Book("Dive Into Design Patterns", "1238",guru);
        alexander.getBooks().add(ddp);
        ddp.getAuthors().add(alexander);

        publisherRepository.save(guru);
        authorRepository.save(alexander);
        bookRepository.save(ddp);


        Publisher reilly = new Publisher("O'Reilly","");

        Author napoleon = new Author("Napoleon","Hill");
        Book tgR = new Book("Think and Grow Rich", "14597",reilly);
        napoleon.getBooks().add(tgR);

        publisherRepository.save(reilly);
        authorRepository.save(napoleon);
        bookRepository.save(tgR);

    }
}
