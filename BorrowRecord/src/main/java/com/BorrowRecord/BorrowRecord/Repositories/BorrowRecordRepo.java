package com.BorrowRecord.BorrowRecord.Repositories;

import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// repo that defines methods to find borrow record by id and bookId attributes
public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findById(Long borrowID);

    Optional<BorrowRecord> findBybookID(Long bookID);
}
