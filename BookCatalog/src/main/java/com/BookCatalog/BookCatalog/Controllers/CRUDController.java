package com.BookCatalog.BookCatalog.Controllers;

import com.BookCatalog.BookCatalog.Models.BookCatalog;
import com.BookCatalog.BookCatalog.RESTClients.BorrowRecordClient;
import com.BookCatalog.BookCatalog.Services.CRUDService;
import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller
@RequestMapping("/api/books/")
public class CRUDController {
    // inject service class and REST Client dependencies
    @Autowired
    CRUDService crudService;

    @Autowired
    BorrowRecordClient borrowRecordClient;

    private static final Logger logger = LoggerFactory.getLogger(CRUDController.class);

    // set the RESTClient tp the class constructor
    public CRUDController(BorrowRecordClient borrowRecordClient) {
        this.borrowRecordClient = borrowRecordClient;
    }

    //create book
    @PostMapping("/create")
    public ResponseEntity<BookCatalog> createBook(@RequestBody BookCatalog bookCatalog) {
        BookCatalog book = crudService.addBook(bookCatalog);
        return ResponseEntity.ok(book);
    }

    // get book by id
    @GetMapping("/{id}")
    public ResponseEntity<BookCatalog> getBookById(@PathVariable Long id) {
        Optional<BookCatalog> book = crudService.findBookById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    // update existing book
    @PutMapping("/{id}")
    public ResponseEntity<BookCatalog> updateBook(@PathVariable Long id, @RequestBody BookCatalog bookInfo) {
        BookCatalog updatedBook = crudService.updateBook(id, bookInfo);
        return ResponseEntity.ok(updatedBook);
    }

    // delete book by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        crudService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    // retrieve borrow record for bookId
    @GetMapping("/{bookID}/borrowRecord")
    public ResponseEntity<BorrowRecord> getBorrowRecord(@PathVariable Long bookID) {
        logger.info("Received request to get BorrowRecord for bookID: {}", bookID);
        try {
            logger.debug("Calling BorrowRecord service for bookID: {}", bookID);
            ResponseEntity<BorrowRecord> response = borrowRecordClient.getBorrowRecordByBookId(bookID);
            logger.debug("Received response from BorrowRecord service: StatusCode={}, Body={}",
                    response.getStatusCode(), response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Successfully retrieved BorrowRecord for bookID: {}", response.getBody());
                return ResponseEntity.ok(response.getBody());
            } else {
                logger.warn("Failed to retrieve BorrowRecord for bookID: {}. StatusCode: {}", bookID, response.getStatusCode());
                return ResponseEntity.status(response.getStatusCode()).build();
            }
        } catch (Exception e) {
            logger.error("Exception occurred while retrieving BorrowRecord for bookID: {}", bookID, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    // find all books
//    @GetMapping
//    public  ResponseEntity<List<BookCatalog>> getAllBooks() {
//        List<BookCatalog> books = findBooksService.findAllBooks();
//        return ResponseEntity.ok(books);
//    }
//
//    // find book by author
//    @GetMapping("/{author}")
//    public ResponseEntity<List<BookCatalog>> findBookById(@PathVariable String author) {
//        List<BookCatalog> books = findBooksService.findBookByAuthor(author);
//        return ResponseEntity.ok(books);
//    }
//
//    // find books by category
//    @GetMapping("/{category}")
//    public ResponseEntity<List<BookCatalog>> findBookByCategory(@PathVariable String category) {
//        List<BookCatalog> books = findBooksService.findBookByCategory(category);
//        return ResponseEntity.ok(books);
//    }

}
