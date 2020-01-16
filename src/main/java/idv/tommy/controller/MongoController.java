package idv.tommy.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tommy.mongo.dao.MongoBookRepo;
import idv.tommy.mongo.entity.Book;

@RestController
@RequestMapping(value = "/mongo")
public class MongoController {
	    
	    @Autowired
	    private MongoBookRepo mongoRepo;
	    
	    /**
	     * 查詢全部 Mongo
	     */
	    @GetMapping(value="all", produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<Book> getAllCustomers() {
	        return mongoRepo.findAll();
	    }
	    
	    /**
	     * 依id查詢
	     */
	    @GetMapping(value="/id/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	    public Book getCustomerById(@PathVariable String id) {
	        return mongoRepo.findById(id).orElse(null);
	    }

	    /**
	     * 依名稱name查詢
	     */
	    @PutMapping(value="/name", produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<Book> findByName(@RequestBody Book book) {
	        return mongoRepo.findByName(book.getName());
	    }

	    /**
	     * 依author查詢
	     */
	    @PutMapping(value="/author", produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<Book> findByAuthor (@RequestBody Book book) {
	        return mongoRepo.findByAuthor(book.getAuthor());
	    }

	    
	    /**
	     * getName
	     */
	    @PutMapping(value="/findLikeByName", produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<Book> findLikeByName (@RequestBody Book book) {
	        return mongoRepo.findLikeByName(book.getName());
	    }

	    
	    /**
	     * 新增一筆資料
	     */
	    @PostMapping(value="/add", produces=MediaType.APPLICATION_JSON_VALUE)
	    public Book createBook(@RequestBody Book book) {
	    	book.setId(ObjectId.get().toHexString());
	    	mongoRepo.save(book);
	        return book;
	    }
}
