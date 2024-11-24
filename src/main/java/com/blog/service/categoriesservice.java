package com.blog.service;

import com.blog.payload.categoresdto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface categoriesservice {
//    create
categoresdto createdto(categoresdto ct);

//update
    categoresdto update(categoresdto ct,Integer cateId);

//    get
    categoresdto getcate(Integer cateId);


//    getall
    List<categoresdto> getallcategores();

//    delete
    void deletecate(Integer cateId);

}
