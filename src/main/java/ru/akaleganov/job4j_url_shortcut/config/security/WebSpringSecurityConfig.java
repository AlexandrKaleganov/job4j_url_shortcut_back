package ru.akaleganov.job4j_url_shortcut.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtAuthenticationEntryPoint;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsersDetailServiceCustom usersDetailServiceCustom;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return {@link UsersDetailServiceCustom}  вернёт реализацию  интерфейса {@link UserDetailsService}
     */
    @Bean
    public UsersDetailServiceCustom getUserDetailService() {
        return new UsersDetailServiceCustom();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.getUserDetailService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public JwtAuthenticationEntryPoint unauthorizedHandler() {
        return new JwtAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
//                    .and().authorizeRequests().antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                    .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}