package com.example.kyrsach.Secure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login","/registration", "/api/users", "/api/films").permitAll().
                antMatchers("/Edit/kinoteatr","/Edit/zabronirovannoe/{id1}/{id4}" ,
                "/Edit/clientFilm/{id}", "/Edit/zabronirovannoe" ,"/Edit/Zal/{id}/{id1}","/Edit/Ryad/{id}/{id1}/{id2}","/Edit/Mestos/{id}/{id1}/{id2}/{id3}"
                ,"/Edit/zabronirovannoe/{id}/{id1}/{id2}/{id3}/{id4}").hasAuthority("USER").
                antMatchers("/ADD/**", "/Edit/**" ,"/admin", "/stat").hasAuthority("ADMIN").
                anyRequest().authenticated().
                and().formLogin().loginPage("/login").permitAll().
                and().logout().permitAll().
                and().csrf().disable().cors().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                passwordEncoder(passwordEncoder).
                usersByUsernameQuery("SELECT username, password, active FROM User WHERE username = ?").
                authoritiesByUsernameQuery("SELECT u.username, ur.roles FROM User u INNER JOIN user_roles ur on u.id = ur.id_user WHERE username = ?");
    }


    @Lazy
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

}
