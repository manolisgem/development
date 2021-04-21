package me.projects.bookshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.projects.bookshop.model.Book;
import me.projects.bookshop.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	//display list of books
	@GetMapping("/bookList")
	public String viewBooks(Model model) {
		
		return findPaginated(1, "title", "asc", model);
		
	}
	
	//display form for adding a new title
	@GetMapping("/displayAddBookForm")
	public String displayAddBookForm(Model model) {
		
		//create a model attribute to bind data from the form
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBookForm";
	}
	
	//executes when button is clicked to save a book in addBookForm and in Edit button of editBookForm
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
	
	@GetMapping("/page/{pageNumber}")
	public String findPaginated(@PathVariable (value = "pageNumber") int pageNumber,
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			Model model) {
		
		int pageSize = 5;
		
		Page<Book> page = bookService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		List<Book> listOfBooks = page.getContent();
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listOfBooks", listOfBooks);
		
		return "bookList";
	}
}