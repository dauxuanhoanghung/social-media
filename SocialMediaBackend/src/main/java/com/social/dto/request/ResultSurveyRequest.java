package com.social.dto.request;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author DinhChuong
 */
@Data
public class ResultSurveyRequest implements Serializable {
    private static final long serialVersionUID = -5L;
    private List<QuestionAndResult> answers;
    
}
