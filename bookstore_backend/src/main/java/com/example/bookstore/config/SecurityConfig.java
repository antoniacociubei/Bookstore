package com.example.bookstore.config;
/*
package com.example.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Optionally disable CSRF protection
                .authorizeRequests()
                .antMatchers("/user/register").permitAll() // Allow unauthenticated access to the registration endpoint
                .anyRequest().authenticated() // Require authentication for all other endpoints
                .and()
                .httpBasic(); // Use HTTP Basic authentication
    }
}*/

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Optionally disable CSRF protection
                .authorizeRequests()
                .antMatchers("/user/register").permitAll() // Allow unauthenticated access to the registration endpoint
                .anyRequest().authenticated() // Require authentication for all other endpoints
                .and()
                .httpBasic(); // Use HTTP Basic authentication
    }

}
*/
