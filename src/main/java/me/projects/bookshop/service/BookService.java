package me.projects.bookshop.service;

import java.util.List;

import me.projects.bookshop.model.Book;

public interface BookService {
	
	List <Book> getAllBooks();
	
	void saveBook(Book book);
	
	Book getBookById(long id);
	
	void deleteBookById(long id);
}