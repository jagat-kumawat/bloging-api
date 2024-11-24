package com.blog.config;

import com.blog.jwt.jwtauthenticationentrypoint;
import com.blog.jwt.jwtauthenticationfilter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMethodSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor

public class  configrestion {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Autowired
    private customuserdetailservice cus;
    private jwtauthenticationentrypoint point;

//    public static final String[] PUBLIC_URLS= {"/api/v1/aut/**","/v3/api-docs/**","/v2/api-docs/**","/swagger-resources/**","/swagger-ui/**","/webjars/**"};

    private jwtauthenticationfilter filter;


        @Bean
        public PasswordEncoder passwordEncoder(){

            return new BCryptPasswordEncoder();
        }




//        @Bean
//        public UserDetailsService userDetailsService(){
//
////            UserDetails normaluser = User.withUsername("jagat").password(passwordEncoder().encode("jagat12345")).roles("NORMAL").build();
////            UserDetails adminuser = User.withUsername("jatin").password(passwordEncoder().encode("jagat12345")).roles("ADMIN").build();
////            return  new InMemoryUserDetailsManager(normaluser, adminuser);
//////ydi hme data base se user get krna h to
//////    customdatabasetoget   = = iski class create crni hoi jese ki
//
//
//               return new customuserdetailservice();
//        }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/aut/**").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll() // If you want all GETs allowed
                        .requestMatchers("/v3/api-docs/**").permitAll() // For Springdoc
                        .requestMatchers("/swagger-ui/**").permitAll() // For Springfox
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(cus).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
//        return configuration.getAuthenticationManager();
//    }












    }


