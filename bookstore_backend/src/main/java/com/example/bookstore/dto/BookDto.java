package com.example.bookstore.dto;

import com.example.bookstore.constraints.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    private double price;
    private int quantity;
    private Category category;
}
