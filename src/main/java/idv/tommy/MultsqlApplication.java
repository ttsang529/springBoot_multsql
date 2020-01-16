package idv.tommy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "idv.tommy.mongo.dao")
public class MultsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultsqlApplication.class, args);
	}

}
