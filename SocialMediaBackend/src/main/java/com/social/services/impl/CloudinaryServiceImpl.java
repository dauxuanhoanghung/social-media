package com.social.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.social.services.CloudinaryService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LENOVO
 */
@Lazy
@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = this.cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
//            ObjectUtils.asMap(
//                        "folder", folderName, // Specify the folder here
//                        "width", width, // Set the width for resizing
//                        "height", height // Set the height for resizing
//            )
            return (String) uploadResult.get("secure_url");
        } catch (IOException ex) {
            Logger.getLogger(CloudinaryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean deleteImage(String publicId) {
        try {
            this.cloudinary.uploader()
                    .destroy(publicId, ObjectUtils.emptyMap());
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CloudinaryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Map getCloudinaryUsage() {
        try {
            return cloudinary.api().usage(ObjectUtils.emptyMap());
        } catch (Exception ex) {
            Logger.getLogger(CloudinaryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    

    @Override
    public boolean checkPublicIdExists(String publicId) {
        try {
            // Make an API call to retrieve information about the resource
            Map result = cloudinary.api().resource(publicId, ObjectUtils.emptyMap());

            // If the result is not empty, it means the resource exists
            return !result.isEmpty();
        } catch (Exception ex) {
            Logger.getLogger(CloudinaryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
