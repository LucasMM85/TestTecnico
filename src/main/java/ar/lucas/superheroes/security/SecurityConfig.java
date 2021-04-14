package ar.lucas.superheroes.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Lucas Mussi
 * date 14/4/2021
 */

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${apikey.name}")
    private String header;

    @Value("${apikey.value}")
    private String headerValue;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(header);

        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!headerValue.equals(principal))
            {
                throw new BadCredentialsException("API key inválida");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http
            .antMatcher("/superheroes/**")
            .authorizeRequests().antMatchers("/**").authenticated()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
            and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }
}
