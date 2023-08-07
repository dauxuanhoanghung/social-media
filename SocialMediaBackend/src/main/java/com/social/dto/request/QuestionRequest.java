package com.social.dto.request;

import com.social.enums.QuestionType;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author LENOVO
 */
@Data
public class QuestionRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String content;
    private QuestionType questionType;
    List<AnswerRequest> answers;
}
