package com.blog.service;

import com.blog.entity.posts;
import com.blog.payload.postdto;
import com.blog.payload.postresponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface postservice {


//    create
 postdto createpost(postdto p,Integer userId,Integer categeryId);
//    update
    postdto updatepost(postdto p ,Integer postid);

//    delete
        void delete(Integer postid);
//    get
    postdto getpost(Integer postid);

//    getall
    postresponse getallpost(Integer pagenumber, Integer pagesize,String sortby);
//    getcategories
    List<postdto> getpostbycategories(Integer cid);

//    getuser
    List<postdto> getpostbyuser(Integer userId);
    List<postdto> searchpost(String keyboard);

}
