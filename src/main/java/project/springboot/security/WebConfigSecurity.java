package project.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailServiceImplementation userDetailServiceImplementation;
	
	@Override // It configures the access request by Http.
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable() // Deactivate the memory pattern configurations.
		.authorizeRequests() // To allow access restriction. 
		.antMatchers(HttpMethod.GET, "/").permitAll() // Any user can access the page
		.antMatchers(HttpMethod.GET, "/personregister").hasAnyRole("ADMIN") //"ROLE_" is not necessary!
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // Allowed to any user
		.loginPage("/login") // Redirect to our login.html
		.defaultSuccessUrl("/personregister") // Redirect to register if the login process is successful
		.failureUrl("/login?error=true") // Redirect to Error if the login process is unsuccessful
		.and().logout().logoutSuccessUrl("/login") // Mapping Logout's URL and invalidate authenticated user.
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override // Create user's authentication with database or in memory.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailServiceImplementation).
		passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	@Override // Ignores specific URLs. 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");
	}
	
}
