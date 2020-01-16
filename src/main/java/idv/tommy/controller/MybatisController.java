package idv.tommy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import idv.tommy.mybatis.dao.primary.MybatisPrimaryMapper;
import idv.tommy.mybatis.dao.secondary.MybatisSecondaryMapper;
import idv.tommy.jdbc.model.Book;
import com.google.gson.Gson;


@RestController
@RequestMapping("/mybatis")
public class MybatisController {
	   
    @Autowired
    private MybatisPrimaryMapper mybatisPrimaryMapper;

	@Autowired
	private MybatisSecondaryMapper mybatisSecondaryMapper;
	
	@RequestMapping("/primary")
	public List<Book> getPrimary() {
		List<Book> books=mybatisPrimaryMapper.getAll();
		return books;
	}
	
	
	
	public static class GetPrimaryRequest {
	    private Integer bookid;
	    // getter, setter for name & number

		public Integer getFirstid() {
			return bookid;
		}

		public void setFirstid(Integer bookid) {
			this.bookid = bookid;
		}
	}
	
	
    @RequestMapping(value="/primary/id", method=RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Book getBook(@RequestBody GetPrimaryRequest request)throws IOException  {
//    	System.out.println("Hello world");
		System.out.println(request.getFirstid());
    	Book book=mybatisPrimaryMapper.getOne(request.getFirstid());
        return book;
    }
    
    
	public static class InsertPrimaryRequest {
	    private String name;
	    private String author;
	    // getter, setter for name & number
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
	
    @RequestMapping(value="primary/insert", method=RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public String insertFirst(@RequestBody InsertPrimaryRequest request) throws IOException {
	    HashMap<String, String> map = new HashMap<>();
	    mybatisPrimaryMapper.insert(request.getName(),request.getAuthor());
    	map.put("Insert", "Success");
    	return new Gson().toJson(map);
    }
    
    
	public static class UpdatePrimaryRequest {
		private Integer bookid;
	    private String name;
	    private String author;
	    // getter, setter for name & number
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
		public Integer getBookid() {
			return bookid;
		}
		public void setBookid(Integer bookid) {
			this.bookid = bookid;
		}
	}
    @RequestMapping(value="/primary/update")
    @ResponseBody
    public String updateFirst(@RequestBody UpdatePrimaryRequest request) throws IOException {
    	HashMap<String, String> map = new HashMap<>();
    	System.out.println(request.getBookid());
    	mybatisPrimaryMapper.update(request.getBookid(),request.getName(),request.getAuthor());
    	map.put("Update", "Success");
    	return new Gson().toJson(map);
    }
    
    @RequestMapping(value="/primary/delete/{bookid}")
    public String deleteFirst(@PathVariable("bookid") String bookid) {
    	HashMap<String, String> map = new HashMap<>();
//    	 System.out.println(bookid);
    	 mybatisPrimaryMapper.delete(Integer.valueOf(bookid));
    	 map.put("Delete", "Success");
    	 return new Gson().toJson(map);
    }
    
    
    //Mybatis second db
	@RequestMapping("/secondary")
	public ResponseEntity<List<Map<String, Object>>> getSecond() {
    	return new ResponseEntity <List<Map<String,Object>>>(mybatisSecondaryMapper.getAll(),HttpStatus.OK); 
	}
	
	public static class GetSecondRequest {
	    private Integer id;
	    // getter, setter for name & number

		public Integer getSecondid() {
			return id;
		}

		public void setSecondid(Integer id) {
			this.id = id;
		}
	}
	
	
    @RequestMapping(value="/secondary/id", method=RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public   ResponseEntity <List<Map<String, Object>>>  getBook(@RequestBody GetSecondRequest request)throws IOException  {
    	return new ResponseEntity <List<Map<String,Object>>>(mybatisSecondaryMapper.getOne(request.getSecondid()),HttpStatus.OK);

    }
}
