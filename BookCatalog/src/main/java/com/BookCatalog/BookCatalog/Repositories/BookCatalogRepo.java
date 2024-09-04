package com.BookCatalog.BookCatalog.Repositories;

import com.BookCatalog.BookCatalog.Models.BookCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCatalogRepo extends JpaRepository<BookCatalog, Long> {

}
