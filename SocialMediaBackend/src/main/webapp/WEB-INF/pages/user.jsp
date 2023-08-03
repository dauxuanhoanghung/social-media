<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">User /</span> Manage All</h4>

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
    <div class="table-responsive text-nowrap" style="min-height: 70vh">
        <c:if test="${not empty users}">
            <table class="table">
                <caption class="ms-4">
                    <spring:message code="view.pages.user.table-title" />
                </caption>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Avatar</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>
                                <i class="fab fa-bootstrap fa-lg text-primary me-3"></i> 
                                <strong>${user.id}</strong>
                            </td>
                            <td>
                                <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
                                    <li
                                        data-bs-toggle="tooltip"
                                        data-popup="tooltip-custom"
                                        data-bs-placement="top"
                                        class="avatar avatar-xs pull-up"
                                        title="Lilian Fuller"
                                        >
                                        <img src="${user.avatar}" alt="Avatar" class="rounded-circle" />
                                    </li>
                                </ul>
                            </td>
                            <td>${user.alumniId}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.role.name eq 'ROLE_ADMIN'}">
                                        <span class="badge bg-label-warning me-1">${user.role.name}</span>
                                    </c:when>
                                    <c:when test="${user.role.name eq 'ROLE_ALUMNI'}">
                                        <span class="badge bg-label-success me-1">${user.role.name}</span>
                                    </c:when>
                                    <c:when test="${user.role.name eq 'ROLE_LECTURER'}">
                                        <span class="badge bg-label-info me-1">${user.role.name}</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.status eq status[0]}">
                                        <span class="badge bg-label-warning me-1">${user.status}</span>
                                    </c:when>
                                    <c:when test="${user.status eq status[1]}">
                                        <span class="badge bg-label-info me-1">${user.status}</span>
                                    </c:when>
                                    <c:when test="${user.status eq status[2]}">
                                        <span class="badge bg-label-danger me-1">${user.status}</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <div class="dropdown">
                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                        <i class="bx bx-dots-vertical-rounded"></i>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="<c:url value="/admin/user/${user.id}"/>">
                                            <i class="bx bx-edit-alt me-1"></i> Edit
                                        </a>
                                        <a class="dropdown-item" href="javascript:void(0);">
                                            <i class="bx bx-trash me-1"></i> Delete
                                        </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty users}">
            <div class="alert alert-warning text-center" style="height: 100%" role="alert">
                <i class='bx bx-error-circle bx-lg'></i>
                <p class="mt-3">No user found</p>
            </div>
        </c:if>
    </div>
</div>