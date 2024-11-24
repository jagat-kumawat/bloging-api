package com.blog.controller;

import com.blog.payload.content;
import com.blog.payload.postdto;
import com.blog.payload.postresponse;
import com.blog.repo.postrepo;
import com.blog.service.fileservice;
import com.blog.service.postservice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.OutputKeys;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class postcontroller {
    @Value("${project.image}")
    private String path;
    @Autowired
    private fileservice f;
    @Autowired
    private postservice psc;
    @Autowired
    private postrepo pro;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<postdto> createdto(
            @RequestBody postdto pd,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        postdto createpost = this.psc.createpost(pd, userId, categoryId);
        return new ResponseEntity<postdto>(createpost, HttpStatus.CREATED);

    }
//    get by  user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<postdto>> getpost(@PathVariable Integer userId){
        List<postdto> getpostbyuser = this.psc.getpostbyuser(userId);
        return new ResponseEntity<>(getpostbyuser, HttpStatus.OK);


    }
    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<postdto>> getcategoreypost(@PathVariable Integer catId){

        List<postdto> getpostbycategories = this.psc.getpostbycategories(catId);
        return new ResponseEntity<>(getpostbycategories,HttpStatus.OK);


    }
   @GetMapping("/{postid}")
    public ResponseEntity<postdto> getpostid(@PathVariable Integer postid){
       postdto getpost = this.psc.getpost(postid);
       return new ResponseEntity<>(getpost,HttpStatus.OK);
   }
   @GetMapping("/all")
    public ResponseEntity<postresponse>getallpost(
            @RequestParam(value = "pagenumber",defaultValue = content.PAGE_NUMBER,required = false) Integer pagenumber,
            @RequestParam(value = "pagesize",defaultValue =content.PAGE_SIZE,required = false)Integer pagesize ,
            @RequestParam(value = "sortby",defaultValue = content.SORT_BY,required = false)String sortby
            ){
       postresponse getallpost = this.psc.getallpost(pagenumber, pagesize,sortby);
       return new ResponseEntity<>(getallpost,HttpStatus.OK);
   }
   @DeleteMapping("/delete/{postid}")
   public ResponseEntity<?> deletepost(@PathVariable Integer postid){
        this.psc.delete(postid);
        return ResponseEntity.ok("done");
   }
   @PutMapping("/update/{postid}")
public ResponseEntity<postdto> updatepost( @RequestBody postdto p,  @PathVariable Integer postid){
       postdto updatepost = this.psc.updatepost(p, postid);
       return new ResponseEntity<>(updatepost,HttpStatus.OK);
   }
   @GetMapping("/search/{keyboard}")
public ResponseEntity<List<postdto>> searchpost(
        @PathVariable String keyboard
   ){
       List<postdto> searchpost = this.psc.searchpost(keyboard);
       return new ResponseEntity<>(searchpost,HttpStatus.OK);

   }
   @PutMapping("/image/upload/{id}")
    public ResponseEntity<postdto> fileinput(@RequestParam("image") MultipartFile image,@PathVariable Integer id) throws Exception {
       postdto getpost = this.psc.getpost(id);
       String s = this.f.uploadImage(path,image);

       getpost.setImage(s);

       postdto updatepost = this.psc.updatepost(getpost,id);
       return new ResponseEntity<>(updatepost,HttpStatus.OK);



   }
   @GetMapping(value = "/getfile{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void getfilename(@PathVariable("image") String imagename, HttpServletResponse response) throws Exception {

       InputStream getresource = this.f.getresource(path, imagename);
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
       StreamUtils.copy(getresource,response.getOutputStream());


   }

}
