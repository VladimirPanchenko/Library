package ru.itprogram.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itprogram.service.impl.UserService;

@Data
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                    //Доступ только для "не" зарегистрированных пользователей
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/books").not().fullyAuthenticated()
                    //Доступ только для пользователей с ролью Администратор
                    .antMatchers("/issuedBooks").hasRole("ADMIN")
                    //Доступ только для пользователей с ролью Пользователь
                    .antMatchers("/readers").hasRole("USER")
                    //Доступ разрешен всем пользователям
                    .antMatchers("/", "/resources/**").permitAll()
                    //Все остальные страницы требуют аутентификации
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder
                .inMemoryAuthentication()
                .withUser("test").password(bCryptPasswordEncoder().encode("test")).roles("USER")
            .and()
                .withUser("admin").password(bCryptPasswordEncoder().encode("admin")).roles("ADMIN");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

}
