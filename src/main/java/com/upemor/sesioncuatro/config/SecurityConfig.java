package com.upemor.sesioncuatro.config;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DataSource dataSource;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as principal, password as credentails, true from member where email=?")
                .authoritiesByUsernameQuery("select m.email as principal, r.name as role\r\n" + 
                		"from member as m\r\n" + 
                		"inner join member_roles as mr\r\n" +
                		"on m.id = mr.member_id\r\n" + 
                		"inner join role as r\r\n" + 
                		"on r.id = mr.role_id\r\n" + 
                		"where m.email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        http.authorizeRequests()
        	.antMatchers("/","/login","/h2-console/**").permitAll()
           .antMatchers("/members/**","/roles/**").hasAnyRole("ADMIN","MEMBER")
           .and().formLogin().loginPage("/login").permitAll()
           .defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/");
	}
}
