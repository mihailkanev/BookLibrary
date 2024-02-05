package book.com.library.service;

import book.com.library.dto.BookDTO;
import book.com.library.model.Author;
import book.com.library.model.Book;
import book.com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return books.stream().map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthorName()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving books", e);
        }
    }

    public List<Book> findByTitleAndAuthor(String title, Author author) {
        try {
            return bookRepository.findByTitleAndAuthor(title, author);
        } catch (Exception e) {
            // Custom exception handling, logging, or rethrowing
            throw new RuntimeException("Error finding books by title and author", e);
        }
    }

    public BookDTO getBookById(Long id) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            return optionalBook.map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthorName())).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving book by ID", e);
        }
    }

    public BookDTO createBook(BookDTO bookDTO) {
        try {
            Book newBook = new Book();
            newBook.setTitle(bookDTO.getTitle());
            newBook.setAuthorName(bookDTO.getAuthor());

            Book savedBook = bookRepository.save(newBook);
            return new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthorName());
        } catch (Exception e) {
            throw new RuntimeException("Error creating book", e);
        }
    }

    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);

            if (optionalBook.isPresent()) {
                Book existingBook = optionalBook.get();
                existingBook.setTitle(updatedBookDTO.getTitle());
                existingBook.setAuthorName(updatedBookDTO.getAuthor());

                Book updatedBook = bookRepository.save(existingBook);
                return new BookDTO(updatedBook.getId(), updatedBook.getTitle(), updatedBook.getAuthorName());
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating book", e);
        }
    }

    public void deleteBookById(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting book by ID", e);
        }
    }
}