package idv.tommy.mybatis.dao.primary;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import idv.tommy.jdbc.model.Book;

public interface MybatisPrimaryMapper {
	List<Book> getAll();
	Book getOne(Integer bookid);
	void insert(@Param("name") String name,@Param("author")  String author);
	void update(@Param("bookid") Integer bookid,@Param("name") String name,@Param("author")  String author);
	void delete(@Param("id") Integer bookid);
}
