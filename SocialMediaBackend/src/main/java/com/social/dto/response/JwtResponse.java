/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.dto.response;

import java.io.Serializable;
import lombok.Getter;

/**
 *
 * @author DinhChuong
 */
@Getter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final String expirationDate;

    public JwtResponse(String jwttoken, String expirationDate) {
        this.token = jwttoken;
        this.expirationDate = expirationDate;
    }

}