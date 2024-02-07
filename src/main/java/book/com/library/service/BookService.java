package book.com.library.service;

import book.com.library.dto.BookDTO;
import book.com.library.model.Author;
import book.com.library.model.Book;
import book.com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<Book> findByTitle(String title, Author author) {
        try {
            return bookRepository.findByTitleAndAuthor(title, author);
        } catch (Exception e) {
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


    public List<BookDTO> findBookByTitle(String title) {
        try {
            System.out.println("Searching for books with title: " + title);
            List<Book> books = bookRepository.findBookByTitleContainingIgnoreCase(title);
            if (books.isEmpty()) {
                throw new RuntimeException("No books found with the specified letter");
            } else {
                return books.stream()
                        .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthorName()))
                        .collect(Collectors.toList());            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding books by title", e);
        }
    }
    public Page<BookDTO> getAllBooksPaginated(Pageable pageable) {
        try {
            Page<Book> booksPage = bookRepository.findAll(pageable);
            return booksPage.map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthorName()));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving paginated books", e);
        }
    }
}