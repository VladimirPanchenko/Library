package ru.itprogram.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "library_book")
public class BookEntity {

    @Id
    @SequenceGenerator(name = "seq_book_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String authorEntity;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "publishing_house")
    private String publishingHouse;

}
