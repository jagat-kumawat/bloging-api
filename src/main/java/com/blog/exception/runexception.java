package com.blog.exception;
public class runexception extends RuntimeException {
    String id;
    String fieldvalue;
  Integer fieldname;

    public runexception(String id, String fieldvalue, Integer fieldname) {
        super(String.format("%s not found with %s :%s",id,fieldvalue,fieldname));
        this.id = id;
        this.fieldvalue = fieldvalue;
        this.fieldname = fieldname;
    }
}
