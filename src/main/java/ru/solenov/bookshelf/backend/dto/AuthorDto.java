package ru.solenov.bookshelf.backend.dto;

import lombok.Data;
import ru.solenov.bookshelf.backend.entity.Author;
import ru.solenov.bookshelf.backend.entity.Book;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class AuthorDto {

    private Long id;

    private String fio;

    private Date dateOfBirth;

    private String country;

    private List<BookDto> books;

    public List<AuthorDto> getAllAuthors(List<Author> authors) {
        List<AuthorDto> authorDtoList = new ArrayList<>();

        for (Author author : authors) {
            books = new ArrayList<>();

            AuthorDto authorDto = new AuthorDto();
            authorDto.setId(author.getId());
            authorDto.setFio(author.getFio());
            authorDto.setDateOfBirth(author.getDateOfBirth());
            authorDto.setCountry(author.getCountry());
            setBookDto(author);
            authorDto.setBooks(books);
            authorDtoList.add(authorDto);
        }
        return authorDtoList;
    }

    public AuthorDto getAuthor(Optional<Author> authorOptional) {

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            this.setId(author.getId());
            this.setFio(author.getFio());
            this.setDateOfBirth(author.getDateOfBirth());
            this.setCountry(author.getCountry());

            books = new ArrayList<>();
            setBookDto(author);
            this.setBooks(books);
        }
        return this;
    }

    private void setBookDto(Author author) {
        for (Book book : author.getBooks()) {
            BookDto bookDto = new BookDto();

            bookDto.setId(book.getId());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setDate(book.getDate());
            bookDto.setTitle(book.getTitle());
            books.add(bookDto);
        }
    }
}
