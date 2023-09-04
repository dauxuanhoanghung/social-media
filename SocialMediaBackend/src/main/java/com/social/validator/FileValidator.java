package com.social.validator;

import com.social.dto.request.FileUploadRequest;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidator implements Validator {

    private static final long MAX_FILE_SIZE_BYTES = 10 * 1024 * 1024; // 10MB

    @Override
    public boolean supports(Class<?> clazz) {
        return FileUploadRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FileUploadRequest request = (FileUploadRequest) target;
        List<MultipartFile> files = request.getFiles();
        if (files.isEmpty()) {
            errors.rejectValue("files", "validator.file.empty", "File is empty");
            return;
        }
        MultipartFile file = files.get(0);
        if (file.isEmpty()) {
            errors.rejectValue("files", "validator.file.empty", "File is empty");
        }

        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            errors.rejectValue("files", "validator.file.sizeExceeded", "File size exceeds the maximum allowed (10MB)");
        }

        if (!isImage(file.getContentType())) {
            errors.rejectValue("files", "validator.file.notImage", "File is not an image");
        }
    }
    
    private boolean isImage(String contentType) {
        return contentType != null && contentType.startsWith("image");
    }

}
