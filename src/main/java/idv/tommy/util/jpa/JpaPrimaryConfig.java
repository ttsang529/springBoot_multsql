package idv.tommy.util.jpa;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
    basePackages = "idv.tommy.jpa.dao.primary", 
    entityManagerFactoryRef = "primaryEntityManager", 
    transactionManagerRef = "primaryTransactionManager"
)

public class JpaPrimaryConfig {
    @Autowired
    private Environment env; 
    
	@Autowired 
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
	
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource);
        em.setPackagesToScan(
          new String[] { "idv.tommy.jpa.entity.primary" });
 
        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.naming.physical-strategy","org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");   
        properties.put("hibernate.show_sql",true);       
        em.setJpaPropertyMap(properties);
 
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		primaryEntityManager().getObject());
        return transactionManager;
    }

}