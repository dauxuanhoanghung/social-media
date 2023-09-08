<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="fw-bold py-3 mb-4">
    <span class="text-muted fw-light">
        <spring:message code="view.pages.statistic-user.statistic" /> /
    </span> 
    <spring:message code="view.pages.statistic-post.posts" />
</h4>

<div class="col-12 order-2 order-md-3 order-lg-2 mb-4">
    <div class="card">
        <div id="barChart"></div>
    </div>
</div>
<div class="row">
    <div class="col-12 col-lg-6 order-2 order-md-3 order-lg-2 mb-4">
        <div class="card">
            <div class='d-flex justify-content-between'>
                <div class="d-flex align-items-center justify-content-center">
                    <div class='m-2'>
                        <input type="radio" id="monthRadio" name="selection" value="month" onclick="showSelection()" checked> 
                        <label for="monthRadio"><spring:message code="view.pages.statistic-user.month"/></label>
                    </div>
                    <div class='m-2'>
                        <input type="radio" id="quarterRadio" name="selection" value="quarter" onclick="showSelection()"> 
                        <label for="quarterRadio"><spring:message code="view.pages.statistic-user.quarter"/></label>
                    </div>
                </div>
                <div style="margin-left: auto" class="d-flex m-2">
                    <div id="monthSelection" class="m-2" style="display: flex">
                        <label for="month"><spring:message code="view.pages.statistic-user.selectMonth"/></label>
                        <select class="form-control"id="month" name="month">
                            <c:forEach begin="1" end="12" var="monthNumber">
                                <option value="${monthNumber}">${monthNumber}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="quarterSelection" style="display: none;" class="m-2">
                        <label for="quarter"><spring:message code="view.pages.statistic-user.selectQuarter"/></label>
                        <select id="quarter" name="quarter" class="form-control">
                            <c:forEach begin="1" end="4" var="quarter">
                                <option value="${quarter}">Q${quarter}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="yearSelection" class="m-2 d-flex">
                        <label for="year"><spring:message code="view.pages.statistic-user.selectYear"/></label>
                        <select id="year" name="year" class="form-control ">
                            <c:forEach begin="2022" end="${currentYear}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="btn btn-warning" onclick="getData()">
                        <spring:message code="view.pages.statistic-user.view"/>
                    </button>
                </div>
            </div>
            <div id="donutChart"></div>
        </div>
    </div>

    <div class="col-12 col-lg-6 order-2 order-md-3 order-lg-2 mb-4">
        <div class="card">
            <div id="lineChart"></div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/stats.js"/>"></script>
<script></script>
