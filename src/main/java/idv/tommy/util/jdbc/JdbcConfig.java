package idv.tommy.util.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {
	@Autowired 
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
	@Autowired 
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
}
