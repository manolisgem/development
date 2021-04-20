package me.projects.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.projects.bookshop.model.Book;
import me.projects.bookshop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List <Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@Override
	public void saveBook(Book book) {
		this.bookRepository.save(book);
	}
	
	@Override
	public Book getBookById(long id) {
		Optional <Book> opt = bookRepository.findById(id);
		Book book  = null;
		if (opt.isPresent()) {
			book = opt.get();
		} else {
			throw new RuntimeException(" Book could not be retrieved, having id = " + id);
		}
		return book;
	}
	@Override
	public void deleteBookById(long id) {
		this.bookRepository.deleteById(id);
	}
	
	
}