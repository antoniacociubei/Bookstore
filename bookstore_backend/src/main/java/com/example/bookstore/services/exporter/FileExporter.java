package com.example.bookstore.services.exporter;

import com.example.bookstore.model.Book;

public interface FileExporter {
    String exportData(Book object);
}