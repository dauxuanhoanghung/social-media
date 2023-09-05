package com.social.dto.request;

import com.social.pojo.Question;
import lombok.Data;

/**
 *
 * @author DinhChuong
 */
@Data
public class QuestionAndResult {

    private Question question;
    private String result;
}
