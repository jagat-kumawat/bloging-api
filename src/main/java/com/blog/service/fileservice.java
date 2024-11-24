package com.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@Service
public interface fileservice {
    String uploadImage(String path, MultipartFile file)throws IOException;
    InputStream getresource(String path, String filename)throws FileNotFoundException;
}
