package TrialExam.configuration;

import TrialExam.filter.ApiKeyAuthFilter;
import TrialExam.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(2)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private ApiKeyRepository apiKeyRepository;

    public ApiSecurityConfig(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Value("X-AUTHTOKEN")
    private String headerName;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!apiKeyRepository.findById(principal).isPresent()) {
                throw new BadCredentialsException("API Key was not found on the system");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                // Fine tune these...
                .antMatchers(HttpMethod.GET, "/ducks").permitAll()
                .antMatchers(HttpMethod.POST, "/ducks").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilter(filter);
//
     /*   httpSecurity
                .antMatcher("/ducks/**")
                .csrf().disable()   // disable X-site request forgery
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests(); // authorize all requests that has a correct header value
                //.anyRequest().authenticated();

        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()// Everybody can see root
                .antMatchers(HttpMethod.POST, "/ducks").hasRole("ADMIN") // Only Admin can POST
                .antMatchers(HttpMethod.GET, "/ducks").permitAll(); // All users can GET
               // .anyRequest().authenticated();
                //.and()
                //.formLogin().permitAll(); // The login page can be seen by everybody*/
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}password") // {noop} means password is being sent without encoding
//                .roles("USER") // user has role USER
//                .and()
//                .withUser("admin")
//                .password("{noop}password")
//                .roles("ADMIN"); // admin has role ADMIN}
//    }
}
