package book.com.library.dto;

import book.com.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private long id;
    private String title;
    private String author;

    @Override
    public String toString() {
        return String.format("BookDTO{id=%d, title='%s', author='%s'}", id, title, author);
    }
}
