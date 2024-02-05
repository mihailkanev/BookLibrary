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
        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthorName()))
                .collect(Collectors.toList());
    }

    public List<Book> findByTitleAndAuthor(String title, Author author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    public BookDTO getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthorName())).orElse(null);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book newBook = new Book();
        newBook.setTitle(bookDTO.getTitle());
        newBook.setAuthorName(bookDTO.getAuthor());

        Book savedBook = bookRepository.save(newBook);
        return new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthorName());

    }


    public BookDTO updateBook(Long id, BookDTO updatedBookDTO) {
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
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
