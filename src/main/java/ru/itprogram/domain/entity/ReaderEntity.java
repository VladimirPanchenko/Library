package ru.itprogram.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "library_reader")
public class ReaderEntity {

    @Id
    @SequenceGenerator(name = "seq_reader_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reader_id")
    private Long id;

    @Column(name = "fio")
    private String fio;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_on_hand_id")
    private List<BookEntity> booksOnHand;

}
