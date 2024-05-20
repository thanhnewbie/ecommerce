package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.Exception.AppException;
import com.example.ecommerce.ecommerce.config.StorageConfig;
import com.example.ecommerce.ecommerce.constants.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class StorageService {
    private final  Path rootLocation;
    @Autowired
    public StorageService(StorageConfig storageConfig){
        this.rootLocation = Paths.get(storageConfig.getLocation());
    }
    public void init(){
        try{
            Files.createDirectories(Paths.get(rootLocation + Common.PRODUCT_IMAGE_UPLOAD_PATH));
        }
        catch (Exception ex){
            throw new AppException("could not initialize storage");
        }
    }
    public String store(String dir, MultipartFile file, String filename){
        try{
            if(file.isEmpty()) {
                throw new AppException("could not store empty file");
            }
            Path path = Paths.get(rootLocation.toString() + dir);
            Path desPath = path.resolve(Paths.get(filename)).normalize().toAbsolutePath();
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, desPath, StandardCopyOption.REPLACE_EXISTING);
                return Common.PRODUCT_IMAGE_UPLOAD_PATH;
            }
        }
        catch (Exception ex){
            throw new AppException("Failed to store file");
        }
    }

    public void deleteFileByPrefix(String prefix, String path){
        try{
            File file = new File(rootLocation.toString() + path);
            for (File item : Objects.requireNonNull(file.listFiles())) {
                if (item.getName().indexOf(prefix + "-") == 0) {
                    Files.deleteIfExists(Paths.get(item.getAbsolutePath()));
                }
            }
        }catch(IOException ex){
            throw new AppException("failed to delete file");
        }
    }

}
