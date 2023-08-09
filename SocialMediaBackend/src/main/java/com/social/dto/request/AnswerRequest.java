package com.social.dto.request;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author LENOVO
 */
@Data
public class AnswerRequest implements Serializable {

    private static final long serialVersionUID = -5L;
    private String content;
}
