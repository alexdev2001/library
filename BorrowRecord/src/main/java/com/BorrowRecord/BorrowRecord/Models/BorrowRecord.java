package com.BorrowRecord.BorrowRecord.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "borrow_record"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowID;
    private Long userID;
    private Long bookID;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

}

