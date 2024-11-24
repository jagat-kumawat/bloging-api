package com.blog.serviceimpl;

import com.blog.entity.roles;
import com.blog.entity.user;
import com.blog.payload.content;
import com.blog.payload.userdto;
import com.blog.repo.rolerepo;
import com.blog.repo.userrepo;
import com.blog.service.userservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.blog.exception.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class serimpl implements userservice {
    @Autowired
    private rolerepo rop;
    @Autowired
    private userrepo ur;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public userdto registernewuser(userdto uda) {
        user map = this.modelMapper.map(uda, user.class);

//        encoded the password
      map.setPassword(this.passwordEncoder.encode(map.getPassword()));

//      roles

        roles rol = this.rop.findById(content.NORMAL_USER).get();

        map.getRole().add(rol);
        user save = this.ur.save(map);

        return this.modelMapper.map(save, userdto.class);
    }

    @Override
    public userdto createuser(userdto ud) {
        user ua=this.dtotouser(ud);
        user save = this.ur.save(ua);

        return this.usertodto(save);

    }


    @Override
    public userdto getuser(Integer userId) {
     user us = this.ur.findById(userId).orElseThrow(()->new runexception("user","Id",userId));


        return this.usertodto(us);
    }

    @Override
    public userdto updateuser(userdto u, Integer userId) {
        user us = this.ur.findById(userId).orElseThrow(()->new runexception("user","value",userId));
        us.setId(u.getId());
        us.setName(u.getName());
        us.setPassword(u.getPassword());
        us.setEmail(u.getEmail());
        us.setAboud(u.getAboud());
        user save = this.ur.save(us);
        return this.usertodto(save);
    }

    @Override
    public List<userdto> getalluser() {
        List<user> all = this.ur.findAll();
        List<userdto> collect = all.stream().map(user -> this.usertodto(user)).collect(Collectors.toList());
        return collect;


    }

    @Override
    public void delete(Integer userId) {
        user us = this.ur.findById(userId).orElseThrow(()->new runexception("user","value",userId));
        this.ur.delete(us);


    }




    public user dtotouser(userdto ud) {
       user u = this.modelMapper.map(ud,user.class);

        return u;

    }
    public userdto usertodto(user u){

        userdto map = this.modelMapper.map(u, userdto.class);
     return map;
    }
}
