/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supplychain.supplychain.configuration;

import com.supplychain.supplychain.security.AccessTokenProcessingFilter;
import com.supplychain.supplychain.security.AccessTokenUserDetailsService;
import com.supplychain.supplychain.security.config.SecurityConfig;
import com.supplychain.supplychain.security.util.TokenGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.GET;

import static org.springframework.http.HttpMethod.PUT;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebSecurityConfiguration() {
        super(true);
    }

    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher("/error")))
                .addFilter(accessTokenProcessingFilter())
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .exceptionHandling().and()
                .headers().and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .securityContext().and()
                .anonymous().and()
                .authorizeRequests()
                // .antMatchers(OPTIONS, "/**").anonymous()
                .antMatchers(POST, "/users").anonymous()
                .antMatchers(OPTIONS, "/users").anonymous()
                .antMatchers(OPTIONS, "/login").anonymous()
                .antMatchers(POST, "/login").anonymous()
                .antMatchers(POST, "/items/save/image/{itemId}").anonymous()
                .antMatchers(GET, "/users/{userId}").anonymous()
                .antMatchers(GET, "/sendSMS").anonymous()

                .antMatchers(POST, "/supplier").anonymous()
                .antMatchers(POST, "/emailsent").anonymous()
                .antMatchers(POST, "/delivery").anonymous()
                .antMatchers(POST, "/shipped").anonymous()
                .antMatchers(POST, "/delivered").anonymous()

                .antMatchers(POST, "/shipped/{postorderId}").anonymous()
                // .antMatchers(POST, "/acceptrequest/{postorderId}").anonymous()
                .antMatchers(POST, "/delivered/{postorderId}").anonymous()
                .antMatchers(POST, "/invoice/{postorderId}").anonymous()
                .antMatchers(POST, "/acceptrequestdelivery/{postorderId}").anonymous()

                .antMatchers(GET, "/postorder/bulkship/orders").anonymous()
                .antMatchers(GET, "/postorder/bulkdelivery/orders").anonymous()
                .antMatchers(POST, "/postorder/bulkshipped/{orders}").anonymous()


                // .antMatchers(OPTIONS, "/**").anonymous()
                // .antMatchers(POST, "/home").anonymous()
                // .antMatchers(OPTIONS, "/home").anonymous()
                // .antMatchers(POST, "/items").anonymous()
                // .antMatchers(OPTIONS, "/items").anonymous()
                // .antMatchers(POST, "/contactlist").anonymous()
                // .antMatchers(OPTIONS, "/contactlist").anonymous()
           
                // .antMatchers(OPTIONS, "/").anonymous()
                .antMatchers(OPTIONS, "/**").anonymous()
                .anyRequest().authenticated();
    }

    @Bean
    protected AccessTokenUserDetailsService accessTokenUserDetailsService() {
        return new AccessTokenUserDetailsService();
    }

    @Bean
    protected PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authProvider = new PreAuthenticatedAuthenticationProvider();
        authProvider.setPreAuthenticatedUserDetailsService(accessTokenUserDetailsService());
        return authProvider;
    }

    @Bean
    protected AccessTokenProcessingFilter accessTokenProcessingFilter() throws Exception {
        AccessTokenProcessingFilter filter = new AccessTokenProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConfigurationProperties("app.security")
    public SecurityConfig securityConfig() {
        return new SecurityConfig();
    }

    @Bean
    @ConfigurationProperties("app.security.configuration")
    public TokenGenerator tokenGenerator(SecurityConfig securityConfig) {
        return new TokenGenerator(securityConfig.getTokenGeneratorPassword(), securityConfig.getTokenGeneratorSalt());
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .allowCredentials(true);
            }
        };
    }
}
