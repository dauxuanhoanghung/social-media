<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">
        <spring:message code="view.pages.survey-result.survey" /> /</span> <spring:message code="view.pages.survey-result.result" />
</h4>
<div class="card">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center">
                <spring:message code="view.pages.survey-result.result" />
            </h5>
        </div>
    </div>
    <div class="table-responsive text-nowrap" style="min-height: 70vh">    
        <table class="table">
            <caption class="ms-4">
                <spring:message code="view.pages.survey-result.result" />
            </caption>
            <thead>
                <tr>
                    <th>Username</th>
                        <c:forEach items="${questions}" var="q" >
                        <td>${q.content}</td>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${results}" var="rs" >
                    <tr>
                        <td>${rs.key}</td>
                        <c:forEach items="${rs.value}" var="r">
                            <c:forEach items="${questions}" var="q">
                                <c:if test="${r.get('questionId') == q.id}">
                                    <td>
                                        ${r.get('result')}
                                    </td>
                                </c:if>
                            </c:forEach>    
                        </c:forEach>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>