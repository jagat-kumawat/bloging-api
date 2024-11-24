package com.blog.controller;

import com.blog.entity.user;
import com.blog.payload.userdto;
import com.blog.service.userservice;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.blog.payload.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class homecontroller {
    @Autowired
    private userservice usr;
    @PostMapping("/")
    public ResponseEntity<userdto> created(@Valid @RequestBody userdto us ){
        userdto creat = this.usr.createuser(us);

        return   new ResponseEntity<>(creat, HttpStatus.CREATED);
    }
@GetMapping("/{userId}")
    public ResponseEntity<userdto> getuser(@PathVariable Integer userId){
    userdto getuser = this.usr.getuser(userId);
    return new ResponseEntity<>(getuser,HttpStatus.ACCEPTED);


}
@GetMapping("/all")
    public ResponseEntity<List<userdto>> alluser(){
    List<userdto> getalluser = this.usr.getalluser();
    return  new ResponseEntity<>(getalluser,HttpStatus.ACCEPTED);


}
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse> deleteuser( @PathVariable("userId") Integer uid){
      this.usr.delete(uid);
return new ResponseEntity<>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<userdto> createuser(@Valid @PathVariable Integer userId,@RequestBody userdto u){

        userdto updateuser = this.usr.updateuser(u,userId);
        return new ResponseEntity<>(updateuser,HttpStatus.ACCEPTED);
    }

    }
