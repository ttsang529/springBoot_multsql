package idv.tommy.jdbc.model;

import javax.persistence.Entity;

@Entity
public class Test {
	private Integer id;
	private String testname;
	private String testauthor;
	//For output 
	public Test(Integer id,String testname,String testauthor) {
		this.id=id;
	    this.testname = testname;
	    this.testauthor=testauthor;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getTestauthor() {
		return testauthor;
	}
	public void setTestauthor(String testauthor) {
		this.testauthor = testauthor;
	}
}
