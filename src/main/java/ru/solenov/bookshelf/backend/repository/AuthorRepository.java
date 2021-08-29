package ru.solenov.bookshelf.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solenov.bookshelf.backend.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
