package book.com.library.repository;

import book.com.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long > {
    Author findByName(String authorName);

    List<Author> findByNameStartingWithIgnoreCase(char letter);
}
