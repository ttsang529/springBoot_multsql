package idv.tommy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import idv.tommy.jdbc.model.Book;
import idv.tommy.jdbc.model.Test;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {
	@Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
	
	
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/pri/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getBook() {

		String sql = "select * from book";
		List<Map<String, Object>> list = primaryJdbcTemplate.queryForList(sql);

		return list;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/pri/Id", method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public  List<Map<String, Object>> getBookId(@RequestBody Book book) throws IOException {
		System.out.println("bookid="+book.getBookid());
		return  primaryJdbcTemplate.queryForList("SELECT * from book where bookid = ?", book.getBookid());
	}	
	

	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/sec/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getTest() {

		String sql = "select * from test";
		List<Map<String, Object>> list = secondaryJdbcTemplate.queryForList(sql);

		return list;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/sec/like", method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
//	public List <Test> getBookId(@RequestBody Test test) throws IOException {
//        return  (List<Test>) secondaryJdbcTemplate.queryForObject(
//                "select * from test where testname like ? and testauthor like ?",
//                new Object[]{"%" + test.getTestname() + "%", "%" + test.getTestauthor() + "%"},
//                (rs, rowNum) ->
//                        new Test(
//                                rs.getInt("id"),
//                                rs.getString("testname"),
//                                rs.getString("testauthor")
//                        )
//        );
//	}	
	
	@ResponseBody
	@RequestMapping(value = "/sec/like", method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getBookId(@RequestBody Test test) throws IOException {
		return secondaryJdbcTemplate.queryForList("select * from test where testname like ? and testauthor like ?",
				"%" + test.getTestname() + "%","%" + test.getTestauthor() + "%");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/sec/insert", method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> saveTest(@RequestBody Test test) {

        String editquery = "INSERT INTO test(testname, testauthor) VALUES (?, ?)";
        Object[] params = new Object[] {test.getTestname(), test.getTestauthor()};

        secondaryJdbcTemplate.update(editquery, params);
        
        return secondaryJdbcTemplate.queryForList("SELECT * FROM test ORDER BY id DESC LIMIT 0, 1");

    }
	
	
	@ResponseBody
	@RequestMapping(value = "/sec/edit", method=RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> updateTest(@RequestBody Test test) {

		String updateQuery = "update test set testname = ?,testauthor=? where id = ?";
        Object[] params = new Object[] {test.getTestname(), test.getTestauthor(),test.getId()};

        secondaryJdbcTemplate.update(updateQuery, params);
        
        return secondaryJdbcTemplate.queryForList("SELECT * FROM test where id = ?",test.getId());

    }
	
	@ResponseBody
	@RequestMapping(value = "/sec/delete", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> deleteTest(@RequestBody Test test) {
	    HashMap<String, Object> map = new HashMap<>();

		if (secondaryJdbcTemplate.queryForList("SELECT * FROM test where id = ?",test.getId()).isEmpty()) {
			map.put("RESULT", "Delete id be "+test.getId().toString()+" is not existed");
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST); 
		}
		String deleteQuery = "DELETE FROM test WHERE id = ?";
        Object[] params = new Object[] {test.getId()};

        secondaryJdbcTemplate.update(deleteQuery, params);
        if (secondaryJdbcTemplate.queryForList("SELECT * FROM test where id = ?",test.getId()).isEmpty()) {
        	map.put("RESULT", "success");
        	return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
        }else {
        	map.put("RESULT", "delete failed");
        	return new ResponseEntity<Map<String,Object>>(map,HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }
	
}
