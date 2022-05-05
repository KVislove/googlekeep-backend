package com.kvat.googlekeepbackend.impls;

import com.kvat.googlekeepbackend.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name  ex- abc.png
        String name=file.getOriginalFilename();

        //random name generate file
        String randomId= UUID.randomUUID().toString();
        String filename1=randomId.concat(name.substring(name.lastIndexOf(".")));

        //full path
        String filePath=path+ File.separator+ filename1;

        //create folder if not created
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return filename1;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath= path+ File.separator + filename;
        InputStream is= new FileInputStream(fullPath);
        // db logic to return input stream
        return is;
    }
}
