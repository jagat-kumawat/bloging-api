package com.blog.service;

import com.blog.payload.commentdto;
import org.springframework.stereotype.Service;

@Service
public interface commentservice {
   commentdto createcomment(commentdto cod,Integer postid,Integer userid);
   void deletecomment(Integer id );



}
