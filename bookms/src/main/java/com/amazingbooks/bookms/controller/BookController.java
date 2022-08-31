package com.amazingbooks.bookms.controller;

import com.amazingbooks.bookms.model.BookDetails;
import com.amazingbooks.bookms.repository.BookRepository;
import com.amazingbooks.bookms.entity.Book;
import com.amazingbooks.bookms.response.BookResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/fetchAllBooks")
    public List<Book> getAllBooks(){
        String source = ".getAllBooks()";
        LOGGER.info(source);
        return bookRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public Optional<Book> getBookByIsbn(@PathVariable String isbn)
    {
        String source = ".getBookByIsbn()";
        LOGGER.info(source+isbn);
        return bookRepository.findByIsbn(isbn);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Object> addBook(@RequestBody Book book ){
        String source = ".addBook()";
        LOGGER.info(source);
        bookRepository.save(book);
        BookResponse response = new BookResponse();
        response.setBookId(book.getBookId());
        response.setIsbn(book.getIsbn());
        response.setMsg("Book Added");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/editBook/{isbn}")
    public ResponseEntity<Object> editBook(@RequestBody Book book,@PathVariable String isbn){
        String source = ".editBook()";
        LOGGER.info(source+" "+isbn+" Starting");
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if(existingBook.isPresent()){
            book.setBookId(existingBook.get().getBookId());
            bookRepository.save(book);
            BookResponse response = new BookResponse();
            response.setBookId(book.getBookId());
            response.setIsbn(book.getIsbn());
            response.setMsg("Book Edited");
            LOGGER.info(source+" "+isbn+" Ending");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        LOGGER.info(source+" Ending");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Available");
    }

    @DeleteMapping("/deleteBook/{isbn}")
    public ResponseEntity<Object> deleteBook(@PathVariable String isbn) {
        String source = ".editBook()";
        LOGGER.info(source+" "+isbn+" Starting");
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isPresent()) {
            long bookId = existingBook.get().getBookId();
            bookRepository.deleteById(bookId);
            BookResponse response = new BookResponse();
            response.setBookId(bookId);
            response.setIsbn(isbn);
            response.setMsg("Book Deleted");
            LOGGER.info(source+" "+isbn+" Ending");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        LOGGER.info(source+" "+isbn+" Ending");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Available");
    }


    @GetMapping("/checkBookAvailability/{isbn}")
    public ResponseEntity getBooksById(@PathVariable String isbn) throws JsonProcessingException {
        String source = ".getBooksById()";
        LOGGER.info(source+" "+isbn+" Starting");
        BookDetails det = new BookDetails();
        Optional<Book> list = bookRepository.findByIsbn(isbn);
        if(!list.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISBN Not Available");
        if(list.get().getTotalCopies()-list.get().getIssuedCopies()<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Out Of Stock ||");
        det.setBookId(list.get().getBookId());
        det.setAvailableCopies(list.get().getTotalCopies()-list.get().getIssuedCopies());
        det.setIsbn(list.get().getIsbn());

        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(det);
        LOGGER.info(source+" "+isbn+" Ending");
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/issueBook/{isbn}/{noOfCopies}")
    public ResponseEntity issueBook(@PathVariable String isbn, @PathVariable int noOfCopies) {
        String source = ".issueBook()";
        LOGGER.info(source+" "+isbn+" Starting");
        Optional<Book> list = bookRepository.findByIsbn(isbn);
        if (list.isPresent()) {
            if((list.get().getTotalCopies()-list.get().getIssuedCopies())-noOfCopies<0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested Copies Not Available");
            }
            Book book = list.get();
            int issueCopies = book.getIssuedCopies();
            if (issueCopies >= 0) {
                book.setIssuedCopies(list.get().getIssuedCopies() + noOfCopies);
                bookRepository.save(book);
                LOGGER.info(source+" "+isbn+" Ending");
                return ResponseEntity.status(HttpStatus.OK).body(book);
            }
        }
        LOGGER.info(source+" "+isbn+" Ending");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ISBN Not Available!!");
    }
}
