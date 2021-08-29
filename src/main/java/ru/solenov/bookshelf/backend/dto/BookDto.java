package ru.solenov.bookshelf.backend.dto;


import lombok.Data;
import ru.solenov.bookshelf.backend.entity.Author;
import ru.solenov.bookshelf.backend.entity.Book;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class BookDto {

    private Long id;

    private String isbn;

    private Date date;

    private String title;

    private List<AuthorDto> authors;

    public List<BookDto> getBookDtoList(List<Book> books) {

        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book : books) {
            authors = new ArrayList<>();
            BookDto bookDto = new BookDto();

            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setDate(book.getDate());
            bookDto.setIsbn(book.getIsbn());

            setAuthorDto(book);
            bookDto.setAuthors(authors);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    private void setAuthorDto(Book book) {
        for (Author author : book.getAuthors()) {
            AuthorDto authorDto = new AuthorDto();

            authorDto.setId(author.getId());
            authorDto.setFio(author.getFio());
            authorDto.setDateOfBirth(author.getDateOfBirth());
            authorDto.setCountry(author.getCountry());
            authors.add(authorDto);
        }
    }

    public List<AuthorDto> getAllAuthors(Optional<Book> bookOptional) {
        setBookDto(bookOptional);
        return this.getAuthors();
    }

    private void setBookDto(Optional<Book> bookOptional) {
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            this.setId(book.getId());
            this.setTitle(book.getTitle());
            this.setDate(book.getDate());
            this.setIsbn(book.getIsbn());

            authors = new ArrayList<>();
            setAuthorDto(book);
            this.setAuthors(authors);
        }
    }

    public BookDto getBookDto(Optional<Book> bookOptional) {
        setBookDto(bookOptional);
        return this;
    }
}
