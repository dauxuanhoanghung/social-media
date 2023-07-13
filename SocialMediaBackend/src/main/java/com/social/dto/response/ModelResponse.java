/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author DinhChuong
 */
@Getter
@Setter
public class ModelResponse {

    private String code;

    private String message;

    private Object data;
    public ModelResponse() {
    }
    public ModelResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
