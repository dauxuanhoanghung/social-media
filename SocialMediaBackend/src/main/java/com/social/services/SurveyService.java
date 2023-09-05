/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.services;

import com.social.dto.request.ResultSurveyRequest;
import com.social.pojo.SurveyResult;
import java.util.List;

/**
 *
 * @author DinhChuong
 */
public interface SurveyService {
     List<SurveyResult> save(ResultSurveyRequest result);
}
