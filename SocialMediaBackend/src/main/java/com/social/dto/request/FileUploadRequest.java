package com.social.dto.request;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LENOVO
 */
@Data
public class FileUploadRequest implements Serializable {

    private static final long serialVersionUID = -1L;
    private List<MultipartFile> files;
}
