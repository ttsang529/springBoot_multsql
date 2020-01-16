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

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
    basePackages = "idv.tommy.jpa.dao.secondary", 
    entityManagerFactoryRef = "secondaryEntityManager", 
    transactionManagerRef = "secondaryTransactionManager"
)
public class JpaSecondaryConfig {
    @Autowired
    private Environment env; 
    
    @Autowired 
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondaryDataSource);
        em.setPackagesToScan(
          new String[] { "idv.tommy.jpa.entity.secondary" });
 
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

    
    @Bean
    public PlatformTransactionManager secondaryTransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		secondaryEntityManager().getObject());
        return transactionManager;
    }
}
