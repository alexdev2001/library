package com.BookCatalog.BookCatalog.RESTClients;

import com.BorrowRecord.BorrowRecord.Models.BorrowRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BORROWRECORD", url = "http://localhost:8787/api/borrowrecord")
public interface BorrowRecordClient {

    @GetMapping("/byBook/{bookID}")
    public ResponseEntity<BorrowRecord> getBorrowRecordByBookId(@PathVariable Long bookID);

}
