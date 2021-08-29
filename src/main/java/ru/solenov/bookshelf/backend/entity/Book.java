package ru.solenov.bookshelf.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Book implements Serializable {

    public Book() {
    }

    public Book(String isbn, Date date, String title) {
        this.isbn = isbn;
        this.date = date;
        this.title = title;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonIgnore
    private List<Author> authors;

    public void addAuthor(Author author) {
        authors = new ArrayList<>();
        this.authors.add(author);
    }
}
