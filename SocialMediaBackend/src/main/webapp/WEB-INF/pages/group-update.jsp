<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">
        <spring:message code="view.pages.user.user" /> /</span> <spring:message code="view.pages.user.manage-all" />
</h4>


<div class="card mb-3 p-2">
    <div class="container ">
        <div class="row d-flex align-items-center">
            <div class="col-md-9">
                <div class="mb-3">
                    <label for="exampleTextField" class="form-label"><spring:message code="view.pages.group-user.group-name" /></label>
                    <input data-group-id="${myGroup.id}" type="text" class="form-control" id="groupName" placeholder="Enter text" 
                           value="${myGroup.name}">
                </div>
            </div>
            <div class="col-md-3 d-flex justify-content-center">
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" id="toggleSwitch" 
                           <c:if test="${myGroup.status == 'ACTIVE'}">checked</c:if>>
                           <label class="form-check-label" for="toggleSwitch">Active</label>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-header d-flex justify-content-between">
            <div>
                <h5 class="align-items-center">
                <spring:message code="view.pages.user.table-title" />
            </h5>
        </div>
        <div>
            <button " class="btn btn-success" onclick="saveCommunity()">
                <%--<spring:message code="view.pages.user.button-create" />--%>
                Update group
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
                            <td>
                                <div class="form-check">
                                    <input class="form-check-input group-member" 
                                           value="${user.id}" type="checkbox" 
                                           id="checkbox-delete" 
                                           ${userOfGroup.contains(user) ? 'checked' : ''}>
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
    const saveCommunity = () => {
        const groupName = document.getElementById("groupName").value;
        const id = document.getElementById("groupName").getAttribute("data-group-id");
        const checkBoxs = document.querySelectorAll('.group-member');
        const toggleSwitch = document.getElementById("toggleSwitch").checked;
        const users = Array.from(checkBoxs).filter(cb => cb.checked).map(item => parseInt(item.value));

        const data = {
            id,
            users, // assuming 'users' is defined elsewhere in your code
            name: groupName, // assuming 'groupName' is defined elsewhere in your code
            status: toggleSwitch ? "ACTIVE" : "DEACTIVE"
        };
        console.log(data);
        fetch('<c:url value="/admin/community/update-group" />', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json' // Tell the server we're sending JSON data
            },
            body: JSON.stringify(data),
//            redirect: 'follow'
        }).then(res => {
            if (res.status == 200)
                window.location.href = "http://localhost:8080/admin/community/group";
        }).catch();
    }
</script>