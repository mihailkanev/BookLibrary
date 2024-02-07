package book.com.library.repository;

import book.com.library.dto.BookDTO;
import book.com.library.model.Author;
import book.com.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleAndAuthor(String title, Author author);

    List<Book> findBookByTitleContainingIgnoreCase(String title);
    Book save(BookDTO bookDTO);
    Page<Book> findAll(Pageable pageable);


}
