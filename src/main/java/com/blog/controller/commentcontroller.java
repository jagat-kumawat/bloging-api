package com.blog.controller;

import com.blog.entity.comment;
import com.blog.payload.commentdto;
import com.blog.serviceimpl.commentserviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class commentcontroller {
    @Autowired
    private commentserviceimpl coip;
@PostMapping("/created/user/{userid}/post/{postid}")
public ResponseEntity<commentdto> createcommentsss(@RequestBody commentdto comm, @PathVariable Integer userid,@PathVariable Integer postid){

    commentdto createcomment = this.coip.createcomment(comm, postid, userid);
    return new ResponseEntity<>(createcomment, HttpStatus.CREATED);

}
    @DeleteMapping("/delete/{commentid}")
    public ResponseEntity<?> deletesss(@PathVariable Integer commentid) {

                        this.coip.deletecomment(commentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
