package com.BookCatalog.BookCatalog.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "book_catalog"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String category;
    private Integer total_copies;
    private Integer available_copies;
    private String created_At;

}
