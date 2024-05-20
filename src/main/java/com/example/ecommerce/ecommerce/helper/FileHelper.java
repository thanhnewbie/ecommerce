package com.example.ecommerce.ecommerce.helper;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.apache.commons.io.FilenameUtils;

import java.util.Arrays;

public class FileHelper {
    private static final String[] IMAGE_FILE_TYPE = {"jpg", "jpeg", "png"};
    public static boolean isAllowImageType(String filename){
        String fileNameExtension = FilenameUtils.getExtension(filename);
        return Arrays.asList(IMAGE_FILE_TYPE).contains(fileNameExtension);
    }
    public static String randomUniqueFileName(String filename){
        String fileBaseName = FilenameUtils.getBaseName(filename);
        String fileExtension = FilenameUtils.getExtension(filename);
        String nanoId = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 10);
        return fileBaseName + "-" + nanoId + "." + fileExtension;
    }
}
