/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.dto;

import com.social.pojo.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LENOVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer id;
    private String content;
    private Boolean lockComment;
    private Integer countAction;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private User user;
}
