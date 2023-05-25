package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.database.Entities.Catalog;
import com.example.database.Entities.ReaderArchive;

@Service
public class LibraryService {
	@Autowired
	private LibraryRepository repository;

	public List<User> searchUser(String criteria, String term) {
		if(criteria.equals("name")) {
			return readerToUser(repository.findUserByName(term));
		}
		if(criteria.equals("phoneNumber")) {
			return readerToUser(repository.findUserByPhoneNumber(term));
		}
		return null;
	}
	
	
	public List<Book> searchBook(String criteria, String term, int min, int max) {
		if(criteria.equals("price")) {
			return catalogToBook(repository.findBookByPrice(min, max));
		} else {
			return catalogToBook(repository.findBookByCriteria(term, criteria));
		}		
	}
	
	private List<User> readerToUser(List<ReaderArchive> readers){
		List<User> users = new ArrayList<User>();
		for (ReaderArchive reader : readers) {
			users.add(new User(reader.getReaderCardNumber().intValue(),
					reader.getFullName().split(" ")[0],
					reader.getFullName().split(" ")[1],
					reader.getAddress(),
					reader.getPhoneNumber(),
					reader.getActivityType()));
		}
		return users;
	}
	
	
	private List<Book> catalogToBook(List<Catalog> books){
		List<Book> resBooks = new ArrayList<Book>();
		for (Catalog book : books) {
			resBooks.add(new Book(book.getLibraryCode(),
					book.getTitle(),
					book.getAuthor(),
					book.getPrice(),
					book.getYear(),
					book.getNumberOfPages()));		
		}
		return resBooks;
	}

	public ConfirmationDTO getConfirmationData(int code, int userId) {
		Catalog catalog = repository.findBookByCode(code);
		ReaderArchive archive = repository.findUserByCode(userId);
		ConfirmationDTO dto = new ConfirmationDTO(catalog.getTitle(),
				catalog.getAuthor(),
				archive.getFullName(),
				new Date());
		return dto;
	}

	public void registerLending(int userId, int code, Date dateOfReturn, Date date) {
		repository.lendBook(date, dateOfReturn, userId, repository.getBookNumberByLibraryCode(code));	
	}


	public void addUser(String name, String city, String street, String house, String phoneNumber, String job) {
		repository.addReader(name,new String("м. "+ city + " вул. " + street + " " + house) , phoneNumber, job);	
	}


	
		


	public void addBook(Book book) {
		repository.addBook(book);
		
	}	
}
