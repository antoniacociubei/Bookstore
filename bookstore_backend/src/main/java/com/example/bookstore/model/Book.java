package com.example.bookstore.model;

import com.example.bookstore.constraints.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @Id
    @GeneratedValue

    private Long id;


    private String title;


    private String author;


    private String publisher;


    private double price;


    private int quantity;


    private Category category;


}