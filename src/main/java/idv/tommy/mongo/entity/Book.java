package idv.tommy.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="book")
public class Book {
    
    @Id
    public String id;
    @Field("name")
    public String name;
    @Field("author")
    public String author;

    public Book() {}

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
}