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
@Table(name = "library_issued_book")
public class IssuedBookEntity {

    @Id
    @SequenceGenerator(name = "seq_issued_book_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_issued_book_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @OneToOne
    @JoinColumn(name = "reader_id")
    private ReaderEntity reader;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

}
