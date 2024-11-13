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
import java.util.Objects;

@Component
public class FileUpload {
    final String uploadDir = "src/main/resources/static/images";

// this will upload the file in target directory which hold the compiled content from which the application runs
//    public final String uploadDir =  new ClassPathResource("static/images").getFile().getAbsolutePath();


    public FileUpload() throws IOException {

    }

    public  boolean uploadFile(MultipartFile file , Integer id, String user) throws Exception{
        boolean uploadFlag = false;



        try{
//            OLD WAY
//            InputStream is = file.getInputStream();
//            byte[] data = new byte[is.available()];
//            is.read(data);
//
//            FileOutputStream fos = new FileOutputStream(Upload_dir + File.separator + file.getOriginalFilename());
//
//            fos.write(data);
//
//            fos.flush();
//            fos.close();;

            if(file.isEmpty()){
                System.out.println("image/file not found");
                return false;
            }
            System.err.println(uploadDir+id+".png" + file+" "+ id + " "+ user  + " has uploaded a photo");
            Files.copy(file.getInputStream(), Paths.get(uploadDir+'/'+user+ File.separator + id+ ".png"), StandardCopyOption.REPLACE_EXISTING);
            uploadFlag = true;




        }
        catch(Exception ex){
//            TODO: logger
        }

        return  uploadFlag;

    }
}
