package org.bram.configuration;

import org.bram.data.repository.UserRepository;
import org.bram.services.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.JavaBean;

@Configuration
public class SecurityConfiguration {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfiguration(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userRepository);
    }
}

//@Bean
//public JwtAuthenticationFilter jwtAuthenticationFilter() {
//    return new JwtAuthenticationFilter(jwtService, userRepository);
//}



//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter(jwtService, userRepository);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers("/auth/**").permitAll() // public auth endpoints
//                        .antMatchers("/seller/**").hasRole("SELLER") // restrict by role
//                        .antMatchers("/customer/**").hasRole("CUSTOMER")
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT is stateless
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    // Optional, if you need AuthenticationManager for login flow
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();




//
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf().disable()
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/auth/**").permitAll() // Public endpoints like /auth/login
//                    .requestMatchers("/seller/**").hasRole("SELLER") // Only SELLER role can access
//                    .requestMatchers("/customer/**").hasRole("CUSTOMER") // Only CUSTOMER role can access
//                    .anyRequest().authenticated() // Everything else requires authentication
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//}
//
//@Bean
//public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//    return config.getAuthenticationManager();
//}