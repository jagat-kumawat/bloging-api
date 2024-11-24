package com.blog.serviceimpl;

import com.blog.service.fileservice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class fileserviceimpl  implements fileservice {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();


        String random = UUID.randomUUID().toString();
String filename1 = random.concat(String.valueOf(name.lastIndexOf(".")));
        String filepath = path + File.separator + filename1;
        // Ensure the directory exists
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir(); // Create the directory, including any necessary but nonexistent parent directories
        }

        Files.copy(file.getInputStream(), Paths.get(filepath));

        return filename1;
    }

    @Override
    public InputStream getresource(String path, String filename) throws FileNotFoundException {
        String filepath = path+File.separator+filename ;
        FileInputStream fileInputStream = new FileInputStream(filepath);

        return fileInputStream;
    }
}
