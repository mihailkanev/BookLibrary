package book.com.library.service;

import book.com.library.dto.AuthorDTO;
import book.com.library.model.Author;
import book.com.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<AuthorDTO> getAllAuthors() {
        try {
            List<Author> authors = authorRepository.findAll();
            return authors.stream().map(author -> new AuthorDTO(author.getName())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving authors", e);
        }
    }

    public AuthorDTO getAuthorById(Long id) {
        try {
            Optional<Author> optionalAuthor = authorRepository.findById(id);
            return optionalAuthor.map(author -> new AuthorDTO(author.getName())).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving author by ID", e);
        }
    }
}
