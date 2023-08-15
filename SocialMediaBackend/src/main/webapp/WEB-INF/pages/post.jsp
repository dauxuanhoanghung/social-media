<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">
    <spring:message code="view.pages.post.post" /> /</span> <spring:message code="view.pages.post.manage-all" />
</h4>
<div class="card">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center">
                <spring:message code="view.pages.user.table-title" />
            </h5>
        </div>
        <div>
            <a href="<c:url value="/admin/user/create" />" class="btn btn-success">
                <spring:message code="view.pages.user.button-create" />
            </a>
        </div>
    </div>
    
    <c:if test="${empty users}">
        <div class="alert alert-warning text-center" style="height: 100%" role="alert">
            <i class='bx bx-error-circle bx-lg'></i>
            <p class="mt-3">No user found</p>
        </div>
    </c:if>
</div>
</div>