package com.KrupoderovMikhail.github.booksrestspringboot.repo;

import com.KrupoderovMikhail.github.booksrestspringboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
}
