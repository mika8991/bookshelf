package ru.solenov.bookshelf.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.solenov.bookshelf.backend.model.Author;
import ru.solenov.bookshelf.backend.model.Book;
import ru.solenov.bookshelf.backend.repository.AuthorRepository;
import ru.solenov.bookshelf.backend.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors/{authorId}/books")
    private List<Book> getBooksByAuthorId(@PathVariable Long authorId) {
        if (authorRepository.existsById(authorId)) {
            return bookRepository.findByAuthorId(authorId);
        } else {
            return new ArrayList<>();
        }
    }

    @PostMapping("/authors/{authorId}/books")
    private void addBook(@PathVariable Long authorId, @RequestBody Book book) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            optionalAuthor.map(author -> {
                book.setAuthor(author);
                return bookRepository.save(book);
            });
        }
    }

    @PutMapping("/authors/{authorId}/books/{bookId}")
    private void updateBook(@PathVariable Long authorId, @PathVariable Long bookId, @RequestBody Book bookToUpdate) {
        if (authorRepository.existsById(authorId)) {
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isPresent()) {
                optionalBook.map(book -> {
                    book.setDate(bookToUpdate.getDate());
                    book.setIsbn(bookToUpdate.getIsbn());
                    book.setTitle(bookToUpdate.getTitle());
                    return bookRepository.save(book);
                });
            }
        }
    }

    @DeleteMapping("/authors/{authorId}/books/{bookId}")
    private void deleteBook(@PathVariable Long authorId, @PathVariable Long bookId) {
        if (authorRepository.existsById(authorId)) {
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            optionalBook.ifPresent(book -> bookRepository.delete(book));
        }
    }
}
