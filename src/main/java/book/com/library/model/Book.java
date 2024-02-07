package book.com.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Book title is required")
    @Size(min = 2, max = 255, message = "Book title must be between {min} and {max} characters")
    private String title;

    @NotBlank(message = "Genre is required")
    @Size(min = 2, max = 50, message = "Genre must be between {min} and {max} characters")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull(message = "Author is required")

    private Author author;
    @Column(name = "is_available")

    private boolean isAvailable;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)

    private List<Review> reviewList;
    @Column(name = "author_name")

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 100, message = "Author name must be between {min} and {max} characters")
    private String authorName;

}