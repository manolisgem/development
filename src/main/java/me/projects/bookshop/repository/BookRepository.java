package me.projects.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.projects.bookshop.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}