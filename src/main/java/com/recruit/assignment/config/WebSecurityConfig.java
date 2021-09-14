package com.recruit.assignment.config;

import com.recruit.assignment.config.exception.LoginFailureHandler;
import com.recruit.assignment.domain.user.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() { return new LoginFailureHandler(); }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Role Setting.
//                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/board/**").hasRole(UserRole.USER.name())
                // Login.
                .and()
                .formLogin()
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginPage("/user/login")
                .failureHandler(loginFailureHandler())
                .defaultSuccessUrl("/board/list")
                // Logout.
                .and()
                .logout()
                .logoutSuccessUrl("/user/login")
                .invalidateHttpSession(true)
                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                        request.setAttribute("errorMessage", authException.getMessage());
//                        response.sendRedirect("user/login");
//                    }
//                })
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**", "/error");
    }

}
