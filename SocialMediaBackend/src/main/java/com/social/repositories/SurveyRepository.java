/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories;

import com.social.pojo.SurveyResult;
import java.util.List;

/**
 *
 * @author DinhChuong
 */
public interface SurveyRepository {
    List<SurveyResult> save(List<SurveyResult> result);
}
