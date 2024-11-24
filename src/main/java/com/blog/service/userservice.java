package com.blog.service;

import com.blog.payload.userdto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userservice {
    userdto registernewuser(userdto uda);
    userdto createuser(userdto u);
    userdto  getuser( Integer userId);
    userdto updateuser(userdto u , Integer userId);
    List<userdto> getalluser();
    void delete(Integer userId);


}
