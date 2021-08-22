package ru.solenov.bookshelf.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solenov.bookshelf.backend.model.Author;

@org.springframework.stereotype.Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
