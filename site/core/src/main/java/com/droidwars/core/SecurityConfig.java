package com.droidwars.core;

import com.droidwars.core.security.JwtAuthenticationEntryPoint;
import com.droidwars.core.security.JwtAuthenticationTokenFilter;
import com.droidwars.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder())
            .and().eraseCredentials(true);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         http
             // we don't need CSRF because our token is invulnerable
             .csrf().disable()

             .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

             // don't create session
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

             .authorizeRequests()
             .antMatchers("/api/authenticate").permitAll()
             .antMatchers("/api/register").permitAll()
             .antMatchers("/api/**").authenticated()
             .antMatchers("/**").permitAll()
             .anyRequest().authenticated();

        // Custom JWT based security filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();

       /* http.
            authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/registration").permitAll()
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            .antMatchers("/current-user").authenticated()
            .antMatchers("/api/**").permitAll()
            // TODO at this time CSRF doesn't matter
            .and().csrf().disable()
            .formLogin()
            //.loginPage("/login").failureUrl("/login?error=true")
            .defaultSuccessUrl("/")
            .and().logout().logoutSuccessUrl("/")
            .and().exceptionHandling().accessDeniedPage("/access-denied")*/
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/static/**");
    }

}
