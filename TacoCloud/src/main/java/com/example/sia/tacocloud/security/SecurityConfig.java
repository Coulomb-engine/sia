package com.example.sia.tacocloud.security;

import com.example.sia.tacocloud.web.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Coulomb
 * @since 2021/11/5 15:34
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll()")

                .and()
                // 告诉Spring Security自定义登录页的路径
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("user")
                .passwordParameter("pwd")
                // 指定默认的登录成功页，强制定向到taco设计页
                .defaultSuccessUrl("/design", true)

                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")

                .and()
                .headers()
                .frameOptions()
                .sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 基于内存的用户存储
//        auth.inMemoryAuthentication()
//                .withUser("buzz").password("{noop}123456").authorities("ROLE_USER")
//                .and()
//                .withUser("woody").password("{noop}123456").authorities("ROLE_USER");

        // 基于JDBC的用户存储
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
//                .authoritiesByUsernameQuery("select username, authority from userauthorities where username = ?")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        // 基于LDAP的用户存储
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}");

        // 自定义用户认证
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }
}
