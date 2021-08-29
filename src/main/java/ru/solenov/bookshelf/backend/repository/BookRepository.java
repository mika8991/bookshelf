package ru.solenov.bookshelf.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solenov.bookshelf.backend.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
