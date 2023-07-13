package com.social.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DinhChuong
 */
@Getter
@Setter

public class SearchResponse {

    private Integer totalObject;
    private Integer pageSize;
    private Integer totalPage;
    private Integer currentPage;
    private Integer totalObjectInCurrentPage;
    private List<Object> listResult;

    public SearchResponse() {
    }

    public SearchResponse(Integer totalPage, Integer pageSize, Integer totalObject, Integer currentPage, Integer totalObjectInCurrentPage, List<Object> listResult) {
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.totalObject = totalObject;
        this.currentPage = currentPage;
        this.totalObjectInCurrentPage = totalObjectInCurrentPage;
        this.listResult = listResult;
    }
}
