<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h4 class="fw-bold py-3 mb-4">
    <span class="text-muted fw-light">
        <spring:message code="view.pages.create-user.user" /> /
    </span> 
    <spring:message code="view.pages.create-user.create" />
</h4>

<div class="card">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center"><spring:message code="view.pages.create-user.title" /></h5>
        </div>
    </div>
    <div class="container-xxl flex-grow-1 container-p-y">
        <!-- Basic Layout & Basic with Icons -->
        <div class="row">
            <!-- Basic with Icons -->
            <div class="col-xxl">
                <div class="card mb-4">
                    <div class="card-body">
                        <c:url value="/admin/user/create" var="action" />
                        <form:form action="${action}" method="post" modelAttribute="user" enctype="multipart/form-data">
                            <form:errors path="*" element="div" cssClass="alert alert-danger" />
                            <form:hidden path="id" />
                            <form:hidden path="alumniId" />
                            <form:hidden path="password" value="ou@123" />
                            <form:hidden path="slug" />
                            <form:hidden path="status" value="${status[0]}"/>

                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label" for="basic-icon-default-fullname">
                                    <spring:message code="view.pages.create-user.username" />
                                </label>
                                <div class="col-sm-10">
                                    <div class="input-group input-group-merge">
                                        <span id="basic-icon-default-fullname2" class="input-group-text">
                                            <i class="bx bx-user"></i>
                                        </span>
                                        <spring:message code="view.pages.create-user.username" var="usernamePlacholder"/>
                                        <form:input
                                            type="text"
                                            class="form-control"
                                            id="basic-icon-default-fullname"
                                            placeholder="${usernamePlacholder}"
                                            path="username"
                                            aria-label="John Doe"
                                            aria-describedby="basic-icon-default-fullname2"
                                            />
                                    </div>
                                    <div class="form-text"><form:errors path="username" cssClass="text-danger"/></div>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label" for="basic-icon-default-email">
                                    <spring:message code="view.pages.create-user.email" />
                                </label>
                                <div class="col-sm-10">
                                    <div class="input-group input-group-merge">
                                        <span class="input-group-text">
                                            <i class="bx bx-envelope"></i>
                                        </span>
                                        <spring:message code="view.pages.create-user.email" var="emailPlaceholder"/>
                                        <form:input
                                            type="text"
                                            id="basic-icon-default-email"
                                            class="form-control"
                                            path="email"
                                            placeholder="${emailPlaceholder}"
                                            aria-label="john.doe"
                                            aria-describedby="basic-icon-default-email2"
                                            />
                                    </div>
                                    <div class="form-text"><form:errors path="email" cssClass="text-danger"/></div>
                                </div>    
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 form-label" for="basic-icon-default-phone">
                                    <spring:message code="view.pages.create-user.display-name" />
                                </label>
                                <div class="col-sm-10">
                                    <div class="input-group input-group-merge">
                                        <span id="basic-icon-default-phone2" class="input-group-text"
                                              ><i class="icon bx bx-user-circle"></i
                                            ></span>
                                            <spring:message code="view.pages.create-user.display-name" var="displayNamePlaceholder" />
                                            <form:input
                                                type="text"
                                                id="basic-icon-default-phone"
                                                class="form-control phone-mask"
                                                path="displayName"
                                                placeholder="${displayNamePlaceholder}"
                                                aria-label="658 799 8941"
                                                aria-describedby="basic-icon-default-phone2"
                                                />
                                    </div>
                                    <div class="form-text">
                                        <form:errors path="displayName" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-2 form-label" for="basic-icon-default-message">
                                    <spring:message code="view.pages.create-user.role" />
                                </label>
                                <div class="col-sm-10">
                                    <form:select class="form-select" id="role" name="role" path="role">
                                        <c:forEach items="${roles}" var="role">
                                            <c:choose>
                                                <c:when test="${user.role.id eq role.id}">
                                                    <option value="${role.id}" selected>${role.name}</option>
                                                </c:when>
                                                <c:when test="${user.role.id != role.id}">
                                                    <option value="${role.id}">${role.name}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                </div>                                        
                                <form:errors path="role" cssClass="text-danger"/>
                            </div>
                            <div class="row justify-content-end">
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary">
                                        <spring:message code="view.pages.create-user.button" />
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>