package com.blog.controller;

import com.blog.jwt.jwtRequest;
import com.blog.jwt.jwtResponse;
import com.blog.jwt.jwtTokenHelper;
import com.blog.payload.userdto;
import com.blog.service.userservice;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aut")
@AllArgsConstructor
@SecurityRequirement(name = "javainuseapi")
public class authcontroller {
    private jwtTokenHelper jwthelper;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private userservice usese;

    @PostMapping("/login")
    public ResponseEntity<jwtResponse> createtoken(


            @RequestBody jwtRequest request
    ) throws Exception {
this.authenticate(request.getEmail(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwthelper.generateToken(userDetails);

      jwtResponse jwts = jwtResponse.builder()
              .Token(token).build();

        return  new ResponseEntity<>(jwts, HttpStatus.OK);


    }

    private void authenticate(String email, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        try {


            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception e){

            System.out.println("user is disabled");
        throw new Exception("invalid username and password");
        }



    }

//    register new user api
    @PostMapping("/register")
    public ResponseEntity<userdto> registerdto(@RequestBody userdto ud)
    {

        userdto registernewuser = this.usese.registernewuser(ud);


        return  new ResponseEntity<>(registernewuser,HttpStatus.CREATED);
    }


}
