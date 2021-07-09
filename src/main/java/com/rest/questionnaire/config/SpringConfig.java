package com.rest.questionnaire.config;

import com.rest.questionnaire.utils.Permission;
import com.rest.questionnaire.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/api/survey/{id}").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/api/survey").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/survey").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/survey/{id}").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())

                .antMatchers(HttpMethod.GET, "/api/question/{id}").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/api/question/{id}").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/question/{id}").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/question/{id}").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.ADMIN_WRITE.getPermission())

                .antMatchers(HttpMethod.POST,"/api/answer/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/answer/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/answer/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/answer/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/answer/go_to_survey/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/answer/survey_user_id/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
    }


    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
