package com.BorrowRecord.BorrowRecord.Controllers;


import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import com.BorrowRecord.BorrowRecord.Repositories.BorrowRecordRepo;
import com.BorrowRecord.BorrowRecord.Services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/borrowrecord/")
public class CRUDController {
    // inject the service as a dependency
    @Autowired
    CRUDService crudService;


    // create borrow record
    @PostMapping("/create")
    public ResponseEntity<BorrowRecord> createBorrowRecord(@RequestBody BorrowRecord borrowRecord) {
        crudService.createBorrowRecord(borrowRecord);
        return ResponseEntity.ok(borrowRecord);
    }

    // retrieve record by id
    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecord> readBorrowRecord(@PathVariable Long id) {
        Optional<BorrowRecord> borrowRecord = crudService.retriveBorrowRecord(id);
        return borrowRecord.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());

    }

    // update record by id
    @PutMapping("/update/{id}")
    public ResponseEntity<BorrowRecord> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecord recordDetails) {
        BorrowRecord updatedRecord = crudService.updateBorrowRecord(id, recordDetails);
        return ResponseEntity.ok(updatedRecord);
    }

    // delete  record by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BorrowRecord> deleteBorrowRecord(@PathVariable Long id) {
        crudService.deleteBprrowRecord(id);
        return ResponseEntity.noContent().build();
    }

    // get borrow record by book id
    @GetMapping("/byBook/{bookID}")
    public ResponseEntity<BorrowRecord> getBorrowRecordByBookId(@PathVariable Long bookID) {
        Optional<BorrowRecord> borrowRecord = crudService.getBorrowRecordByBookId(bookID);

        return borrowRecord.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
