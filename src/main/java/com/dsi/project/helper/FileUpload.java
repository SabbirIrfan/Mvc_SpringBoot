package com.dsi.project.helper;

import org.springframework.boot.jdbc.metadata.CommonsDbcp2DataSourcePoolMetadata;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUpload {
//    final String uploadDir = "src/main/resources/static/images";

// this will upload the file in target directory which hold the compiled content from which the application runs
    public final String uploadDir =  new ClassPathResource("static/images/").getFile().getAbsolutePath();


    public FileUpload() throws IOException {

    }

    public  boolean uploadFile(MultipartFile file , Integer id){
        boolean uploadFlag = false;



        try{

            if(file.isEmpty()){
                System.out.println("image/file not found");
                return false;
            } else if (!file.getContentType().equals("image/jpeg") &&
                     !file.getContentType().equals("image/png" )) {
                System.out.println("we accept png and jpeg file only." + file.getContentType());
                return false;
            }
            System.out.println(uploadDir+id+".png" );
            Files.copy(file.getInputStream(), Paths.get(uploadDir+ File.separator + id+ ".png"), StandardCopyOption.REPLACE_EXISTING);
            uploadFlag = true;




        }
        catch(Exception ex){
            uploadFlag = false;
        }

        return  uploadFlag;

    }
}
