package idv.tommy.jdbc.model;

import javax.persistence.Entity;


@Entity
public class Book{
    private Integer bookid;
    private String name;
    private String author;
    //output
    public Book(Integer bookid,String name, String author) {
    	this.bookid  = bookid;
        this.name	 = name;
        this.author	 = author;
    }
    
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
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
