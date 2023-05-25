package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.database.Entities.BookFund;
import com.example.database.Entities.BookLendingJournal;
import com.example.database.Entities.Catalog;
import com.example.database.Entities.LendingPlanId;
import com.example.database.Entities.LendingPlans;
import com.example.database.Entities.ReaderArchive;


@Repository
@Transactional()
public class LibraryRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SessionFactory sessionFactory;
	public List<ReaderArchive> findUserByName(String fullName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ReaderArchive WHERE fullName LIKE :partialName";
        Query<ReaderArchive> query = session.createQuery(hql, ReaderArchive.class);
        query.setParameter("partialName", "%" + fullName + "%");
        return query.list();
	}
	public List<ReaderArchive> findUserByPhoneNumber(String phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ReaderArchive WHERE phoneNumber LIKE :phone_number";
        Query<ReaderArchive> query = session.createQuery(hql, ReaderArchive.class);
        query.setParameter("phone_number", "%" + phoneNumber + "%");
        List<ReaderArchive> resArchives = query.list();
        return resArchives;
	}
	public List<Catalog> findBookByCriteria(String term, String criteria) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Catalog WHERE :criteria LIKE :term";
        Query<Catalog> query = session.createQuery(hql, Catalog.class);
        query.setParameter("criteria", criteria);
        query.setParameter("term", "%" + term + "%");
        List<Catalog> resArchives = query.list();
        return resArchives;
	}
	
	public Catalog findBookByCode(int libraryCode) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Catalog WHERE libraryCode = :code";
        Query<Catalog> query = session.createQuery(hql, Catalog.class);
        query.setParameter("code", libraryCode);
        Catalog res = query.getSingleResult();
        return res;
	}
	
	
	public ReaderArchive findUserByCode(int code) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ReaderArchive WHERE readerCardNumber = :code";
        Query<ReaderArchive> query = session.createQuery(hql, ReaderArchive.class);
        query.setParameter("code", code);
        ReaderArchive res = query.getSingleResult();
        return res;
	}
	
	
	public List<Catalog> findBookByPrice(int startValue, int endValue) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Catalog WHERE price BETWEEN :startValue AND :endValue";
        Query<Catalog> query = session.createQuery(hql, Catalog.class);
        query.setParameter("startValue", startValue);
        query.setParameter("endValue", endValue);
        List<Catalog> resArchives = query.list();
        return resArchives;
	}
	
	
	public int getBookCountByLibraryCode(int libraryCode) {
	    Session session = sessionFactory.getCurrentSession();
	    String hql = "SELECT COUNT(b) FROM BookFund b WHERE b.catalog.libraryCode = :libraryCode AND b.status = 'yes'";
	    Query<Integer> query = session.createQuery(hql, Integer.class);
	    query.setParameter("libraryCode", libraryCode);
	    int bookCount = query.getSingleResult();
	    return bookCount;
	}
	
	public void addReader(String fullName, String address, String phoneNumber, String activityType) {
	    Session session = sessionFactory.getCurrentSession();
	    
	    ReaderArchive reader = new ReaderArchive();
	    reader.setFullName(fullName);
	    reader.setAddress(address);
	    reader.setPhoneNumber(phoneNumber);
	    reader.setActivityType(activityType);
	    
	    session.save(reader);
	}
	
	
	public void addBook(String author, String title, String publisher, Integer year, String type, Integer numberOfPages, String theme, Integer price, int quantity) {
	    Session session = sessionFactory.getCurrentSession();	    
	    Catalog catalog = new Catalog(author, title, publisher, year, type, numberOfPages, theme, price);
	    for (int i = 0; i < quantity; i++) {
	        BookFund bookFund = new BookFund(catalog, "Available");
	        session.save(bookFund);
	    }    
	    session.save(catalog);
	}
	
	
	public int getBookNumberByLibraryCode(int libraryCode) {
	    Session session = sessionFactory.getCurrentSession();
	    
	    String hql = "SELECT b.inventoryNumber FROM BookFund b WHERE b.catalog.libraryCode = :libraryCode AND b.status = 'yes'";
	    Query<Integer> query = session.createQuery(hql, Integer.class);
	    query.setParameter("libraryCode", libraryCode);
	    query.setMaxResults(1); 
	    List<Integer> inventoryNumbers = query.getResultList();
	    
	    if (!inventoryNumbers.isEmpty()) {
	        return inventoryNumbers.get(0);
	    }
	    return 0;
	}
	
	
	public void lendBook(Date lendingDate, Date returnDate, int readerCardNumber, int i) {
	    Session session = sessionFactory.getCurrentSession();
	    BookFund bookFund = session.get(BookFund.class, i);
	    ReaderArchive readerArchive = session.get(ReaderArchive.class, readerCardNumber);
	    if (bookFund != null && readerArchive != null) {
	        BookLendingJournal bookLendingJournal = new BookLendingJournal(
	        		(long) i,
	        		readerArchive,
	        		lendingDate);
	        session.save(bookLendingJournal);
	        bookFund.setStatus("no");
	        session.update(bookFund);
	        LendingPlans lendingPlan = new LendingPlans();
	        LendingPlanId lendingPlanId = new LendingPlanId();
	        lendingPlanId.setInventoryNumber((long) i);
	        lendingPlan.setId(lendingPlanId);
	        lendingPlan.setBookLendingJournal(bookLendingJournal);
	        lendingPlan.setPlannedReturnDate(returnDate);
	        session.save(lendingPlan);
	    }
	}
	
	public void returnBook(Long inventoryNumber) {
	    Session session = sessionFactory.getCurrentSession();
	    BookFund bookFund = session.get(BookFund.class, inventoryNumber);
	    if (bookFund != null && bookFund.getStatus().equals("Lent")) {
	        BookLendingJournal bookLendingJournal = session.get(BookLendingJournal.class, inventoryNumber);
	        LendingPlans lendingPlan = session.get(LendingPlans.class, inventoryNumber);
	        if (bookLendingJournal != null && lendingPlan != null) {
	            session.delete(bookLendingJournal);
	            session.delete(lendingPlan);
	            bookFund.setStatus("yes");
	            session.update(bookFund);
	        }
	    }
	}
	public void addBook(Book book) {
		Session session = sessionFactory.getCurrentSession();	    
	    Catalog catalog = new Catalog(book.getAuthor(), book.getTitle(),
	    		book.getPublisher(), book.getYear(), book.getType(),
	    		book.getNumberOfPages(), book.getTheme(), book.getPrice());
	    for (int i = 0; i < book.getCount(); i++) {
	        BookFund bookFund = new BookFund(catalog, "yes");
	        session.save(bookFund);
	    }    
	    session.save(catalog);
		
	}
}


