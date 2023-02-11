package com.example.btr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.btr.service.CustomerService;

@Configuration
@EnableWebSecurity
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {
	
private CustomerService customerService;
	
	@Autowired
	public CustomerSecurityConfig(@Lazy CustomerService userDetailsService) {
		super();
		this.customerService = userDetailsService;
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customerService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers(
	"/**",
	"/customer/**",
	"/customer/registerCustomer/**",
	"/profileCustomer",
	"/selectBuses",
	"/customer/makePayment/**",
	"/showBill",
	"/customer/passenger-details",
	"/customer/forgotPasswordCustomer",
	"/js/**",
	"/css/**",
	"/img/**").permitAll()
	.anyRequest().hasRole("CUSTOMER")

	.and()
	.formLogin()
	.loginPage("/customer/loginCustomer")
	.defaultSuccessUrl("/customer/home",true)
	.permitAll()

	.and()
	.logout()
	.invalidateHttpSession(true)
	.clearAuthentication(true)
	.logoutRequestMatcher(new AntPathRequestMatcher("/logoutCustomer"))
	.logoutUrl("/logoutCustomer")
	.logoutSuccessUrl("/loginCustomer")
	.permitAll()

	.and()
	.exceptionHandling()
	.accessDeniedPage("/403")

	.and()
	.csrf().disable(); //cross-site request forgery
	}
	
}
