package com.blog.controller;

import com.blog.payload.ApiResponse;
import com.blog.payload.categoresdto;
import com.blog.service.categoriesservice;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class categoriescontrollers {

@Autowired
    private categoriesservice ct;
//create
@PostMapping("/")
public ResponseEntity<categoresdto> createcategories(@Valid @RequestBody categoresdto c){
    categoresdto createdto = this.ct.createdto(c);

    return new ResponseEntity<categoresdto>(createdto, HttpStatus.CREATED);


}
//get
    @GetMapping("/{cateId}")
    public ResponseEntity<categoresdto> getdto(@PathVariable Integer cateId){
        categoresdto getcate = this.ct.getcate(cateId);
        return new ResponseEntity<>(getcate,HttpStatus.ACCEPTED);

    }


//    getall
    @GetMapping("/getall")
    public ResponseEntity<List<categoresdto>> getalll(){
        List<categoresdto> getallcategores = this.ct.getallcategores();
        return new ResponseEntity<>(getallcategores,HttpStatus.OK);

    }

//    delete
    @DeleteMapping("/delete/{cateId}")
    public ResponseEntity<?> deletecategories(@PathVariable Integer cateId){
    this.ct.deletecate(cateId);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);

}

//    update
    @PutMapping("/get/{cateid}")
    public ResponseEntity<categoresdto> updatedto(@Valid @RequestBody categoresdto ct, @PathVariable Integer cateid){
        categoresdto update = this.ct.update(ct, cateid);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }


}
