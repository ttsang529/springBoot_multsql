package idv.tommy.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import idv.tommy.jpa.dao.primary.BookRepository;
import idv.tommy.jpa.dao.secondary.TestRepository;
import idv.tommy.jpa.entity.primary.Book;
import idv.tommy.jpa.entity.secondary.Test;

@RestController
@RequestMapping("/jpa")
public class JpaController {
	@Autowired
    private BookRepository bookRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "pri")
    public List<Book> getJpaFirst() {
        return bookRepository.findAll();
    }
    
    
    
    @Autowired
    private TestRepository testRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "sec")
    public List<Test> getJpaSecond() {
        return testRepository.findTestAll();
    }
}
