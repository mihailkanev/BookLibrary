package book.com.library.data;

import book.com.library.model.Author;
import book.com.library.model.Book;
import book.com.library.model.Review;
import book.com.library.repository.BookRepository;
import book.com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class CustomCsvReader {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Transactional
    public void readCsv(String filePath, Set<Long> existingBookIds) {
        Map<Long, Book> bookMap = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processCsv(line, bookMap, existingBookIds);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void processCsv(String line, Map<Long, Book> bookMap, Set<Long> existingBookIds) {
        try {
            if (line.startsWith("Book Title,Author,Genre,Availability,Review")) {
                return;
            }
            String[] data = line.split(",");

            if (data.length < 5) {
                System.out.println("Error: Incorrect number of fields in CSV line - " + line);
                System.out.println("Actual fields: " + Arrays.toString(data));
                return;
            }

            String title = data[0].trim();
            String authorName = data[1].trim();
            String genre = data[2].trim();
            String availability = data[3].trim();
            String review = data[4].trim();
            Author author = authorService.getOrCreateAuthor(authorName);

            //check for duplicates
            List<Book> existingBooks = bookRepository.findByTitleAndAuthor(title, author);
            if (!existingBooks.isEmpty()) {
                System.out.println("Skipped duplicate book with title: " + title + " and author: " + authorName);
                return;
            }
            Book book = new Book();
            book.setTitle(title);
            book.setGenre(genre);
            book.setAuthor(author);
            book.setAuthorName(authorName);
            book.setAvailable("Available".equalsIgnoreCase(availability));

            // review part
            String[] reviewParts = review.split("/");
            if (reviewParts.length == 2) {
                double rating = Double.parseDouble(reviewParts[0]) / Double.parseDouble(reviewParts[1]);
                Review reviewEntity = new Review();
                reviewEntity.setRating(rating);
                book.setReviewList(Collections.singletonList(reviewEntity));
            } else {
                System.err.println("Error parsing review data in CSV line: " + line);
            }

            // Save the book to the database
            bookRepository.save(book);

            // Now, retrieve the auto-generated ID from the saved book
            long generatedId = book.getId();
            if (existingBookIds.contains(generatedId)) {
                System.out.print("Skip id" + generatedId);
                return;
            }
            // Add the book to the bookMap using the auto-generated ID
            bookMap.put(generatedId, book);
        } catch (Exception e) {
            System.err.println("Error processing CSV line: " + line);
            e.printStackTrace();
        }
    }
}
