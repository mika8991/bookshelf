package ru.solenov.bookshelf.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.solenov.bookshelf.backend.dto.AuthorDto;
import ru.solenov.bookshelf.backend.dto.BookDto;
import ru.solenov.bookshelf.backend.entity.Author;
import ru.solenov.bookshelf.backend.entity.Book;
import ru.solenov.bookshelf.backend.repository.AuthorRepository;
import ru.solenov.bookshelf.backend.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/books")
    private List<BookDto> getAllBooks() {
        BookDto bookDto = new BookDto();
        return bookDto.getBookDtoList(bookRepository.findAll());
    }

    @GetMapping("/books/{bookId}")
    private List<AuthorDto> getAuthorsByBookId(@PathVariable Long bookId) {
        BookDto bookDto = new BookDto();
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        bookDto.getBookDto(bookOptional);
        return bookDto.getAuthors();
    }

    @GetMapping("/book/{bookId}")
    private BookDto getBookById(@PathVariable Long bookId) {
        BookDto bookDto = new BookDto();
        return bookDto.getBookDto(bookRepository.findById(bookId));
    }

    @GetMapping("/authors/{authorId}/books")
    private List<BookDto> getBooksByAuthorId(@PathVariable Long authorId) {
        AuthorDto authorDto = new AuthorDto();
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        authorDto.getAuthor(authorOptional);
        return authorDto.getBooks();
    }

    @PostMapping("/authors/{authorId}/books")
    private void addBook(@PathVariable Long authorId, @RequestBody Book book) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            book.addAuthor(optionalAuthor.get());
            bookRepository.save(book);
        }
    }

    @PostMapping("/books/{bookId}/author")
    private void addAuthorByBookId(@PathVariable Long bookId, @RequestBody Author author) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.getAuthors().add(author);
            bookRepository.save(book);
        }
    }

    @PutMapping("/authors/{authorId}/books/{bookId}")
    private void updateBook(@PathVariable Long authorId, @PathVariable Long bookId, @RequestBody Book bookToUpdate) {
        if (authorRepository.existsById(authorId)) {
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                book.setDate(bookToUpdate.getDate());
                book.setIsbn(bookToUpdate.getIsbn());
                book.setTitle(bookToUpdate.getTitle());
                book.setAuthors(bookToUpdate.getAuthors());
                bookRepository.save(book);
            }
        }
    }

    @DeleteMapping("/authors/{authorId}/books/{bookId}")
    private void deleteBook(@PathVariable Long authorId, @PathVariable Long bookId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            Book book = optionalBook.get();
            bookRepository.deleteById(bookId);
            author.getBooks().remove(book);
            authorRepository.save(author);
        }
    }
}
