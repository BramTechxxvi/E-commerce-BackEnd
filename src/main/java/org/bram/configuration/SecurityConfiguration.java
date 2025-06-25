package org.bram.configuration;

public class SecurityConfiguration {
}




//@Configuration
//public class SecurityConfig {
//
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//    public SecurityConfig(JwtService jwtService, UserRepository userRepository) {
//        this.jwtService = jwtService;
//        this.userRepository = userRepository;
//    }
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
