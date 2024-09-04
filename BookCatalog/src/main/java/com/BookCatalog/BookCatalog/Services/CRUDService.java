package com.BookCatalog.BookCatalog.Services;

import com.BookCatalog.BookCatalog.Models.BookCatalog;
import com.BookCatalog.BookCatalog.RESTClients.BorrowRecordClient;
import com.BookCatalog.BookCatalog.Repositories.BookCatalogRepo;
import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CRUDService {
    @Autowired
    BookCatalogRepo bookCatalogRepository;

    @Autowired
    BorrowRecordClient borrowRecordClient;

    // create new book
    public BookCatalog addBook(BookCatalog bookCatalog) {
        return bookCatalogRepository.save(bookCatalog);
    }

    // retrieve book by id
    public Optional<BookCatalog> findBookById(Long id) {
        return bookCatalogRepository.findById(id);
    }

    // update existing book
    public BookCatalog updateBook(Long id, BookCatalog bookDetails) {
        BookCatalog book = bookCatalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("oould not find book" + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublisher(bookDetails.getPublisher());
        book.setPublishedDate(bookDetails.getPublishedDate());
        book.setCategory(bookDetails.getCategory());
        book.setTotal_copies(bookDetails.getTotal_copies());
        book.setAvailable_copies(bookDetails.getAvailable_copies());



        return bookCatalogRepository.save(book);

    }

    // delete book by id
    public void deleteBookById(Long id) {
        BookCatalog book = bookCatalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("could not find book" + id));
        bookCatalogRepository.delete(book);
    }


}