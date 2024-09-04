package com.BorrowRecord.BorrowRecord.Services;


import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import com.BorrowRecord.BorrowRecord.Repositories.BorrowRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CRUDService {
    @Autowired
    BorrowRecordRepo borrowRepository;

    // create borrow record
    public BorrowRecord createBorrowRecord(BorrowRecord recordDetails) {

        recordDetails.setBookID(recordDetails.getBookID());
        recordDetails.setUserID(recordDetails.getUserID());
        recordDetails.setBorrowDate(recordDetails.getBorrowDate());
        recordDetails.setDueDate(recordDetails.getDueDate());
        recordDetails.setReturnDate(recordDetails.getReturnDate());

        return borrowRepository.save(recordDetails);
    }

    // retrive borrow record
    public Optional<BorrowRecord> retriveBorrowRecord(Long borrowID) {

        return borrowRepository.findById(borrowID);
    }

    // update borrow record
    public BorrowRecord updateBorrowRecord(Long borrowID, BorrowRecord updatedBorrow) {
        BorrowRecord borrowRecord = borrowRepository.findById(borrowID)
                .orElseThrow(() -> new RuntimeException("user not found"));

        borrowRecord.setBookID(updatedBorrow.getBookID());
        borrowRecord.setUserID(updatedBorrow.getUserID());
        borrowRecord.setBorrowDate(updatedBorrow.getBorrowDate());
        borrowRecord.setDueDate(updatedBorrow.getDueDate());
        borrowRecord.setReturnDate(updatedBorrow.getReturnDate());

        return borrowRepository.save(borrowRecord);
    }

    // delete borrow record
    public void deleteBprrowRecord(Long id) {
        BorrowRecord borrowRecord = borrowRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("could not find user"));

        borrowRepository.delete(borrowRecord);

    }

    // get borrow record by bookId
    public Optional<BorrowRecord> getBorrowRecordByBookId(Long bookID) {
        return borrowRepository.findBybookID(bookID);
    }
}
