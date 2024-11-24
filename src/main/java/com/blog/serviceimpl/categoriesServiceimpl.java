package com.blog.serviceimpl;

import com.blog.entity.categories;
import com.blog.exception.runexception;
import com.blog.payload.categoresdto;
import com.blog.payload.userdto;
import com.blog.repo.categoresrepo;
import com.blog.service.categoriesservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class categoriesServiceimpl implements categoriesservice {
        @Autowired
        private categoresrepo ctre;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public categoresdto createdto(categoresdto ct) {
        categories map = this.modelMapper.map(ct, categories.class);
        categories save = this.ctre.save(map);
        return this.modelMapper.map(save,categoresdto.class);


    }

    @Override
    public categoresdto update(categoresdto ct, Integer cateId) {
        categories byId = this.ctre.findById(cateId).orElseThrow(()->new runexception("user","Id",cateId));
        byId.setTitle(ct.getTitle());
        byId.setDescription(ct.getDescription());
        categories save = this.ctre.save(byId);
        return this.modelMapper.map(save,categoresdto.class);


    }

    @Override
    public categoresdto getcate(Integer cateId) {

        categories byId = this.ctre.findById(cateId).orElseThrow(()->new runexception("user","Id",cateId));


        return this.modelMapper.map(byId,categoresdto.class);
    }

    @Override
    public List<categoresdto> getallcategores() {
        List<categories> all = this.ctre.findAll();
        List<categoresdto> collect = all.stream().map((cat)->this.modelMapper.map(cat,categoresdto.class)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public void deletecate(Integer cateId) {
        categories byId = this.ctre.findById(cateId).orElseThrow(()->new runexception("user","Id",cateId));
        this.ctre.delete(byId);



    }
//    public categories dtotocategories(categoresdto ct){
//        categories map = this.modelMapper.map(ct, categories.class);
//        return map;
//    }
//    public categoresdto categoreTodto(categories c){
//        categoresdto map = this.modelMapper.map(c, categoresdto.class);
//        return map;
//
//    }


}
