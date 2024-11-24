package com.blog.serviceimpl;

import com.blog.entity.comment;
import com.blog.entity.posts;
import com.blog.entity.user;
import com.blog.exception.runexception;
import com.blog.payload.commentdto;
import com.blog.repo.commentrepo;
import com.blog.repo.postrepo;
import com.blog.repo.userrepo;
import com.blog.service.commentservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class commentserviceimpl implements commentservice {
    @Autowired
    private userrepo ur;
    @Autowired
    private postrepo pr;
    @Autowired
    private commentrepo cr;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public commentdto createcomment(commentdto cod, Integer postid,Integer userid) {
        user u = this.ur.findById(userid).orElseThrow(() -> new runexception("userid", "uservalue", userid));
        posts p = this.pr.findById(postid).orElseThrow(() -> new runexception("postid", "postvalue", postid));
        comment map = this.modelMapper.map(cod, comment.class);
        map.setUu(u);
        map.setPost(p);
        comment save = this.cr.save(map);
        commentdto map1 = this.modelMapper.map(save, commentdto.class);
        return map1;


    }

    @Override
    public void deletecomment(Integer id) {
        comment byId = this.cr.findById(id).orElseThrow(()->new runexception("commentid","comment",id));
        this.cr.delete(byId);


    }
}
