package idv.tommy.jpa.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import idv.tommy.jpa.entity.primary.Book;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Integer>{

}
