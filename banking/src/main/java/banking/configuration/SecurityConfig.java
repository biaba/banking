package banking.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import banking.services.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BankingAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(daoAuthProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/customers/**").permitAll() // for REST Client requests
			.antMatchers("/customer/**").hasRole("CUSTOMER")
			.antMatchers("/employee/**").hasRole("EMPLOYEE")
		.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login/processing").successHandler(successHandler)
			.and()
			.logout()
			.logoutSuccessUrl("/")
		.and()
			.exceptionHandling().accessDeniedPage("/accessDenied");
	
	}

// Password encoding Bean
	@Bean
	public BCryptPasswordEncoder bcryptPsswEncoder() {
		return new BCryptPasswordEncoder();
	}

// Custom authentication provider
	@Bean
	public DaoAuthenticationProvider daoAuthProvider() {
		DaoAuthenticationProvider daoProv = new DaoAuthenticationProvider();
		daoProv.setPasswordEncoder(bcryptPsswEncoder());
		daoProv.setUserDetailsService(userService);
		
		return daoProv;
	}
	

}
