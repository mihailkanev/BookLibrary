package book.com.library.repository;

import book.com.library.model.Author;
import book.com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long > {
    Author findByName(String authorName);
}
