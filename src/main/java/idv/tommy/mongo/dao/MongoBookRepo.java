package idv.tommy.mongo.dao;


import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import idv.tommy.mongo.entity.Book;

public interface MongoBookRepo extends MongoRepository<Book, String> {
    
    public List<Book> findByName(String name);
    public List<Book> findByAuthor(String author);   
    
    @Query("{ 'name' : { '$regex' : ?0 , $options: 'i'}}")
    List<Book> findLikeByName(String name);

}
