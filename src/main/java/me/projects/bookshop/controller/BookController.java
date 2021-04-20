package me.projects.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import me.projects.bookshop.model.Book;
import me.projects.bookshop.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	//display list of books
	@GetMapping("/bookList")
	public String viewBooks(Model model) {
		model.addAttribute("bookList", bookService.getAllBooks());
		return "bookList";
	}
	
	@GetMapping("/displayAddBookForm")
	public String displayAddBookForm(Model model) {
		//create a model attribute to bind data from the form
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBookForm";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") Book book) {
		//save book to database
		bookService.saveBook(book);
		return "redirect:/bookList";
	}
	
	@GetMapping("/displayEditBookForm/{id}")
	public String displayEditBookForm(@PathVariable(value = "id") long id, Model model) {
		
		//get book from the service layer
		Book book = bookService.getBookById(id);
		
		//set book as a model attribute to prepopulate the form
		model.addAttribute("book", book);
		return "editBookForm";
	}
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable(value = "id") long id) {
		//call delete book method
		this.bookService.deleteBookById(id);
		return "redirect:/bookList";
	}
	
}