<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">
        <spring:message code="view.pages.user.user" /> /</span> <spring:message code="view.pages.user.manage-all" />
</h4>
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form:form action="/create-group" method="post" modelAttribute="community" >
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><spring:message code="view.pages.group-user.select-title" /></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body " >
                    <div class="table-responsive text-nowrap" style="min-height: 70vh">
                        <c:if test="${not empty users}">
                            <table class="table">
                                <caption class="ms-4">
                                    <spring:message code="view.pages.user.table-title" />
                                </caption>
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th><spring:message code="view.pages.user.avatar" /></th>
                                        <th><spring:message code="view.pages.user.alumniId" /></th>
                                        <th><spring:message code="view.pages.user.role" /></th>
                                        <th><spring:message code="view.pages.user.status" /></th>
                                        <th><spring:message code="view.pages.user.actions" /></th>
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
                                                        title="${user.displayName}"
                                                        >
                                                        <c:choose>
                                                            <c:when test="${user != null and user.avatar != null and user.avatar != ''}">
                                                                <img src="${user.avatar}" alt="user-avatar" alt="Avatar" class="rounded-circle" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="https://static.vecteezy.com/system/resources/previews/009/734/564/original/default-avatar-profile-icon-of-social-media-user-vector.jpg" alt="Avatar" class="rounded-circle">
                                                            </c:otherwise>
                                                        </c:choose>
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
                                            <td >
                                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
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
                        <div class="container mt-3">
                            <div class="row d-flex align-items-center">
                                <div class="col-md-9">
                                    <div class="mb-3">
                                        <label for="exampleTextField" class="form-label"><spring:message code="view.pages.group-user.group-name" /></label>
                                        <input type="text" class="form-control" id="exampleTextField" placeholder="Enter text">
                                    </div>
                                </div>
                                <div class="col-md-3 d-flex justify-content-center">
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="toggleSwitch">
                                        <label class="form-check-label" for="toggleSwitch">Active</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>

        </form:form>

    </div>
</div>
<div class="card">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center">
                <spring:message code="view.pages.group-user.table-tite" />
            </h5>
        </div>
        <div>
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#myModal">
                <spring:message code="view.pages.group-user.create-group" />
            </button>
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
                        <th>                           
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        </th>  
                        <th>Id</th>
                        <th><spring:message code="view.pages.group-user.group-name" /></th>
                        <th><spring:message code="view.pages.group-user.number-member" /></th>
                        <th><spring:message code="view.pages.group-user.create-by" /></th>
                        <th><spring:message code="view.pages.user.status" /></th>
                        <th><spring:message code="view.pages.user.actions" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="group" items="${groups}">
                        <tr>
                            <td>
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                            </td>
                            <td>
                                <i class="fab fa-bootstrap fa-lg text-primary me-3"></i> 
                                <strong>${group.id}</strong>
                            </td>
                            <td>
                                ${group.name}
                            </td>
                            <td>0</td>
                            <td>${group.founderId.displayName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${group.status eq status[0]}">
                                        <span class="badge bg-label-warning me-1">${group.status}</span>
                                    </c:when>
                                    <c:when test="${group.status eq status[1]}">
                                        <span class="badge bg-label-info me-1">${group.status}</span>
                                    </c:when>
                                    <c:when test="${group.status eq status[2]}">
                                        <span class="badge bg-label-danger me-1">${group.status}</span>
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
                                            <i class="bx bx-edit-alt me-1"></i><spring:message code="view.pages.user.edit" />
                                        </a>
                                        <a class="dropdown-item" href="javascript:void(0);">
                                            <i class="bx bx-trash me-1"></i><spring:message code="view.pages.user.delete" />
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

<script>
    
</script>