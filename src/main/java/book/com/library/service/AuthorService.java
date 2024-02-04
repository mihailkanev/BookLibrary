package book.com.library.service;

import book.com.library.model.Author;
import book.com.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getOrCreateAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);

        if (author == null) {
            // If the author does not exist, create a new one
            author = new Author();
            author.setName(authorName);
            authorRepository.save(author);
        }

        return author;
    }
}
