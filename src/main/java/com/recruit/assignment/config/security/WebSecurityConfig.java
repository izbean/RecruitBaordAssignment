package com.recruit.assignment.config.security;

import com.recruit.assignment.config.security.LoginFailureHandler;
import com.recruit.assignment.domain.user.UserRole;
import com.recruit.assignment.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

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
                .antMatchers("/*").permitAll()
                .antMatchers("/api/v1/user/**").permitAll()
                .antMatchers("/api/v1/board/**").hasRole(UserRole.USER.name())
                .antMatchers("/admin/**").hasRole(UserRole.ADMIN.name())
                // Login.
                .and()
                .formLogin()
                .usernameParameter("userId")
                .passwordParameter("password")
                .loginPage("/user/login")
                .failureHandler(loginFailureHandler())
                .failureForwardUrl("/login")
                .defaultSuccessUrl("/")
                // Logout.
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**", "/error");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userService, passwordEncoder());
    }

}
