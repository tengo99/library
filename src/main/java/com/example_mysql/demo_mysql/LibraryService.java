package com.example_mysql.demo_mysql;

import com.example_mysql.demo_mysql.model.Author;
import com.example_mysql.demo_mysql.model.Book;
import com.example_mysql.demo_mysql.model.Member;
import com.example_mysql.demo_mysql.model.request.AuthorCreationRequest;
import com.example_mysql.demo_mysql.model.request.BookCreationRequest;
import com.example_mysql.demo_mysql.model.request.BookLendRequest;
import com.example_mysql.demo_mysql.model.request.MemberCreationRequest;
import com.example_mysql.demo_mysql.repository.AuthorRepository;
import com.example_mysql.demo_mysql.repository.BookRepository;
import com.example_mysql.demo_mysql.repository.LendRepository;
import com.example_mysql.demo_mysql.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;

    public Book readBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }

        throw new EntityNotFoundException("Book with id " + id + " not found");
    }

    public List<Book> readBooks(){
        return bookRepository.findAll();
    }

    public Book readBook(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return book.get();
        }

        throw new EntityNotFoundException("Book with ISBN " + isbn + " not found");
    }

    public Book createBook(BookCreationRequest book) {
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author with id " + book.getAuthorId() + " not found");
        }

        Book bookToCreate = new Book();
//        BeanUtils.copyProperties(book, bookToCreate);
        bookToCreate.setName(book.getName());
        bookToCreate.setIsbn(book.getIsbn());
        bookToCreate.setAuthor(author.get());
        return bookRepository.save(bookToCreate);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent()) {
            throw new EntityNotFoundException("Member with id " + id + " not found");
        }

        Member member = optionalMember.get();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        return memberRepository.save(member);
    }

    public Author createAuthor(AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }


}
