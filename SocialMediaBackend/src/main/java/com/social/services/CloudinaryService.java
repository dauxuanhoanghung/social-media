package com.social.services;

import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface CloudinaryService {

    String uploadImage(MultipartFile file);

    boolean deleteImage(String publicId);
    
    Map getCloudinaryUsage();
}
