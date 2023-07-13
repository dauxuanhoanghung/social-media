/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.dto.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author DinhChuong
 */
@Getter
@Setter

public class UserCredentialRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String username;

    private String password;

    //need default constructor for JSON Parsing
    public UserCredentialRequest() {

    }

    public UserCredentialRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}
