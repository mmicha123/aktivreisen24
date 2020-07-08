package net.aktivreisen24;

import net.aktivreisen24.exceptions.VacationNotFoundException;
import net.aktivreisen24.model.Account;
import net.aktivreisen24.repository.JdbcAccountRepo;
import net.aktivreisen24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.antMatchers( "/resources/**", "/", "/index", "vacation**", "/register", "/vacations", "/vacationsByFilter", "/css/**", "/js/**","/images/**", "/fonts/**","/scss/**","/**/favicon.ico").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/profile")
//				.usernameParameter("username")//
//				.passwordParameter("password")
//				.permitAll()
//				.and()
//				.logout()
//				.permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("pass")
				.roles("USER");
	}
}