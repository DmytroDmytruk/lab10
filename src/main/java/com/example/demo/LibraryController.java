package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@Controller
public class LibraryController {
	
	@Autowired
	private LibraryService service;
	
	@PostMapping("/saveUser")
	public String setUser(@RequestParam("name") String name,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("house") String house,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("profession") String job
            ) {
		service.addUser(name, city, street, house, phoneNumber, job);		
		return "redirect:/newuser";	
	}
	
	
	@PostMapping("/saveBook")
	public String saveBook(@RequestParam("author") String author,
	                       @RequestParam("title") String title,
	                       @RequestParam("publisher") String publisher,
	                       @RequestParam("year") int year,
	                       @RequestParam("type") String type,
	                       @RequestParam("pageCount") int pageCount,
	                       @RequestParam("theme") String theme,
	                       @RequestParam("price") int price,
	                       @RequestParam("quantity") int quantity) {
		
		service.addBook(new Book(  
						   author,
	                       title,
	                       publisher,
	                       year,
	                       type,
	                       pageCount,
	                       theme,
	                       price,
	                       quantity));
	    return "redirect:/newbook";
	}
	
	
	
	@GetMapping("/newuser")
	public String newUser() {
		return "newuser";	
	}
	
	@GetMapping("/newbook")
	public String newBook() {
		return "newbook";	
	}
	
	@PostMapping("/lend/confirm")
	public String confirmLending(@RequestParam("libraryCode")int code
			,@RequestParam("userId")int userId, Model model
			)
			{
		ConfirmationDTO confirm = service.getConfirmationData(code, userId);
		model.addAttribute("title", confirm.getTitle());
		model.addAttribute("author", confirm.getAuthor());
		model.addAttribute("name", confirm.getName());
		model.addAttribute("surname", "surname");
		model.addAttribute("cdate", confirm.getCurrDate());
		model.addAttribute("userId", userId);
        model.addAttribute("libraryCode", code);
		return "confirmation";
		
	}
	
	@PostMapping("/lend")
	public String lend(@RequestParam("userId")Integer userId, Model model) {	
        model.addAttribute("user_id", userId);
		return "bookSearchResults";
	}
	@GetMapping("/searchuser")
    public String searchUsers(@RequestParam("searchCriteria") String criteria,
                              @RequestParam("searchTerm") String term,
                              Model model) {
		List<User> users = service.searchUser(criteria, term);
        model.addAttribute("users", users);
        return "userSearchResults";
    }
	@GetMapping("/addbook")
	public String addbook() {
		return "addbook";
		
	}
	
	@PostMapping("/lend/success")
	public String success(@RequestParam("date")String dateOfReturn, @RequestParam("libraryCode")int code
			,@RequestParam("userId")int userId, Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = dateFormat.parse(dateOfReturn);
			int libraryCode = service.registerLending(userId, code, date, new Date());
			model.addAttribute("bookCode", libraryCode);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return "success";
		
	}
	
	@GetMapping("/hi2")
	public String hi2() {
		return "main";
	}
	
	
	
	@GetMapping("/defineuser")
	public String hi() {
		return "userSearchResults";
	}
	
	@GetMapping("/searchbooktoadd")
    public String searchBookstoadd(@RequestParam(value = "searchCriteria", required = false) String criteria,
    		 					@RequestParam(value = "searchTerm", required = false) String term,
                              @RequestParam(value = "priceFrom", required = false) Integer fromPrice,
                              @RequestParam(value = "priceTo", required = false) Integer toPrice,
                              Model model) {
		fromPrice = fromPrice != null ? fromPrice : 0;
		toPrice = toPrice != null ? toPrice : 0;
		List<Book> books = service.searchBook(criteria, term, fromPrice, toPrice);
        model.addAttribute("books", books);
        return "addbook";
    }
	
	@PostMapping("/addbook/add")
	public String addCopy(@RequestParam("quantity") Integer count,
            @RequestParam("libraryCode") Integer code) {
		service.addCopies(count, code);
				return "redirect:/addbook";
		
	}
	
	@GetMapping("/searchbook")
    public String searchBooks(@RequestParam(value = "searchCriteria", required = false) String criteria,
                              @RequestParam(value = "searchTerm", required = false) String term,
                              @RequestParam(value = "priceFrom", required = false) Integer fromPrice,
                              @RequestParam(value = "priceTo", required = false) Integer toPrice,
                              @RequestParam(value = "userId", required = false) Integer userId,
                              Model model) {
		fromPrice = fromPrice != null ? fromPrice : 0;
		toPrice = toPrice != null ? toPrice : 0;
		List<Book> books = service.searchBook(criteria, term, fromPrice, toPrice);
        model.addAttribute("user_id", userId);
        model.addAttribute("books", books);
        return "bookSearchResults";
    }
}
