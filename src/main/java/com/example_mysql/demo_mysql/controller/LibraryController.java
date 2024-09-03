package com.example_mysql.demo_mysql.controller;

import com.example_mysql.demo_mysql.LibraryService;
import com.example_mysql.demo_mysql.model.Author;
import com.example_mysql.demo_mysql.model.Book;
import com.example_mysql.demo_mysql.model.Lend;
import com.example_mysql.demo_mysql.model.Member;
import com.example_mysql.demo_mysql.model.request.AuthorCreationRequest;
import com.example_mysql.demo_mysql.model.request.BookCreationRequest;
import com.example_mysql.demo_mysql.model.request.BookLendRequest;
import com.example_mysql.demo_mysql.model.request.MemberCreationRequest;
import com.example_mysql.demo_mysql.model.response.BookResponseDto;
import com.example_mysql.demo_mysql.model.response.LendResponseDto;
import com.example_mysql.demo_mysql.model.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/book")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn) {
        if(isbn == null) {
            List<Book> books = libraryService.readBooks();
            List<BookResponseDto> list = books.stream().map(book -> new BookResponseDto(book.getIsbn(), book.getName(), book.getAuthor().getFirstName())).toList();
            return ResponseEntity.ok(list);
        }
        Book book = libraryService.readBook(isbn);
        BookResponseDto bookResponseDto = new BookResponseDto(book.getIsbn(), book.getName(), book.getAuthor().getFirstName());
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookResponseDto> readBook(@PathVariable Long bookId) {
        Book book = libraryService.readBook(bookId);
        BookResponseDto bookResponseDto = new BookResponseDto(book.getIsbn(), book.getName(), book.getAuthor().getFirstName());
        return ResponseEntity.ok(bookResponseDto);
    }

    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookCreationRequest request) {
        Book book = libraryService.createBook(request);
        BookResponseDto bookResponseDto = new BookResponseDto(book.getIsbn(), book.getName(), book.getAuthor().getFirstName());
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long bookId) {
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/member")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberCreationRequest request) {
        Member member = libraryService.createMember(request);
        List<Lend> lends = member.getLends();
        List<LendResponseDto> collect =
                lends.stream()
                        .map(lend -> new LendResponseDto(lend.getStartOn(), lend.getDueOn(), lend.getBook().getName(), lend.getBook().getIsbn(), lend.getBook().getAuthor().getFirstName())).toList();
        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getFirstName(), member.getLastName(), collect);
        return ResponseEntity.ok(memberResponseDto);
    }


    @PatchMapping("/member/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody MemberCreationRequest request) {
        return ResponseEntity.ok(libraryService.updateMember(memberId, request));
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.createAuthor(request));
    }
}
