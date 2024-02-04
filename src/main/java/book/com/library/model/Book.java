package book.com.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(name = "is_available")
    private boolean isAvailable;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviewList;
    @Column(name = "author_name")
    private String authorName;

}