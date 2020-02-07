package banking.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "banking")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:database.properties")
public class BankConfig implements WebMvcConfigurer {

	@Autowired
	Environment env;

	// DataSource bean
	@Bean
	public DataSource secDataSource() {

		ComboPooledDataSource combo = new ComboPooledDataSource();
		try {
			combo.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException();
		}
		combo.setUser(env.getProperty("jdbc.user"));
		combo.setJdbcUrl(env.getProperty("jdbc.url"));
		combo.setUser(env.getProperty("jdbc.user"));
		combo.setPassword(env.getProperty("jdbc.password"));

		// set connection pool props

		combo.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		combo.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		combo.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		combo.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return combo;
	}

	// Hibernate bean
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		// creating session factories
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		// set the properties
		sessionFactory.setDataSource(secDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());

		return sessionFactory;
	}

	// Views bean
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/views/");
		return viewResolver;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	// Defining static resource location
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	// helper method to set Hibernate Properties
	private Properties getHibernateProperties() {

		// setting hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return props;
	}

	// helper method to turn int to String for Environment properties
	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

}
