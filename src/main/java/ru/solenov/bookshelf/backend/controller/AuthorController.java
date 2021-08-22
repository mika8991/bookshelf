package ru.solenov.bookshelf.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solenov.bookshelf.backend.model.Author;
import ru.solenov.bookshelf.backend.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @GetMapping("/authors")
    private List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @GetMapping("/authors/{id}")
    private ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = repository.findById(id);
        return author.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/authors")
    private void createAuthor(@RequestBody Author author) {
        repository.save(author);
    }

    @PutMapping("/authors/{id}")
    private void updateAuthor(@PathVariable Long id, @RequestBody Author authorToUpdate) {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setFio(authorToUpdate.getFio());
            author.setCountry(authorToUpdate.getCountry());
            author.setDateOfBirth(authorToUpdate.getDateOfBirth());
            repository.save(author);
        } else {
            repository.save(authorToUpdate);
        }
    }

    @DeleteMapping("/authors/{id}")
    private void deleteAuthor(@PathVariable Long id) {
        Optional<Author> author = repository.findById(id);
        author.ifPresent(repository::delete);
    }
}
