package com.social.dto.request;

import com.social.enums.PostType;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author LENOVO
 */
@Data
public class SurveyRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String content;
    private PostType type = PostType.SURVEY;
    List<QuestionRequest> questions;
}
