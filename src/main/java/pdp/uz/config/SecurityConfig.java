package pdp.uz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .authorities("GET_ALL_CATEGORY", "PARENT_CATEGORY", "CHILDREN_CATEGORY", "ADD_CATEGORY")
                .and()
                .withUser("user").password(passwordEncoder().encode("123"))
                .roles("USER")
                .authorities("GET_ALL_CATEGORY", "PARENT_CATEGORY", "CHILDREN_CATEGORY")
                .and()
                .withUser("guest").password(passwordEncoder().encode("111"))
                .roles("GUEST")
                .authorities("GET_ALL_CATEGORY");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
