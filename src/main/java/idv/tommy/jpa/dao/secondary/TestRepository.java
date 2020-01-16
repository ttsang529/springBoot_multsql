package idv.tommy.jpa.dao.secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import idv.tommy.jpa.entity.secondary.Test;


@RepositoryRestResource
public interface TestRepository extends JpaRepository<Test, Integer>{
	@Modifying   
	@Query(value = 
				"select * from test" 
		, nativeQuery = true)//?1表示第一個引數，?2表示第二個引數
		List<Test> findTestAll();

}