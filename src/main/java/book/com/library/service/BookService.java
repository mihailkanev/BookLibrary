package book.com.library.service;

import book.com.library.dto.BookDTO;
import book.com.library.model.Author;
import book.com.library.model.Book;
import book.com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        return books.stream().map(book -> new BookDTO(book.getId(), book.getTitle(),book.getAuthorName()))
                .collect(Collectors.toList());
    }

    public List<Book> findByTitleAndAuthor(String title, Author author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

}
