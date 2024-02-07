package book.com.library.controller;

import book.com.library.dto.AuthorDTO;
import book.com.library.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        try {
            List<AuthorDTO> authors = authorService.getAllAuthors();
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        try {
            AuthorDTO authorDTO = authorService.getAuthorById(id);
            if (authorDTO != null) {
                return new ResponseEntity<>(authorDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(authorDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search-by-letter")
    public ResponseEntity<List<AuthorDTO>> searchAuthorByFirstLetter(@RequestParam
        @Pattern(regexp = "A-Za-z", message = "Invalid letter format") char letter) {
        try {
            List<AuthorDTO> authors = authorService.searchAuthorByFirstLetter(letter);
            if (authors.isEmpty()) {
                return new ResponseEntity<>(authors, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(authors, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}