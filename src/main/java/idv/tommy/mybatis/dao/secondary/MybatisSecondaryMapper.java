package idv.tommy.mybatis.dao.secondary;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import idv.tommy.jdbc.model.Test;

public interface MybatisSecondaryMapper {
	List<Map<String,Object>> getAll();
	List<Map<String,Object>>  getOne(Integer id);
	void insert(@Param("testname") String testname,@Param("testauthor") String testauthor);
	void update(Test test);
	void delete(@Param("id") Integer id);
}
