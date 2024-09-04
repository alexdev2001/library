package com.BorrowRecord.BorrowRecord.Repositories;

import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findById(Long borrowID);

    Optional<BorrowRecord> findBybookID(Long bookID);
}
