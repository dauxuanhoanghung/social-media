<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">
        <spring:message code="view.pages.account.account-settings" /> /</span> <spring:message code="view.pages.account.account" />
</h4>
<c:if test="${user != null}">
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-pills flex-column flex-md-row mb-3">
                <li class="nav-item">
                    <a class="nav-link active" href="javascript:void(0);">
                        <i class="bx bx-user me-1"></i> <spring:message code="view.pages.account.account" />
                    </a>
                </li>
            </ul>
            <div class="card mb-4">
                <h5 class="card-header">
                    <spring:message code="view.pages.account.profile-details" />
                </h5>
                <!-- Account -->
                <div class="card-body">
                    <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <c:choose>
                            <c:when test="${user != null and user.avatar != null and user.avatar != ''}">
                                <img src="${user.avatar}" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                            </c:when>
                            <c:otherwise>
                                <img src="https://static.vecteezy.com/system/resources/previews/009/734/564/original/default-avatar-profile-icon-of-social-media-user-vector.jpg" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                            </c:otherwise>
                        </c:choose>
                        <div class="button-wrapper">
                            <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
                                <span class="d-none d-sm-block"><spring:message code="view.pages.account.upload-new-photo" /></span>
                                <i class="bx bx-upload d-block d-sm-none"></i>
                                <input type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg">
                            </label>
                            <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                                <i class="bx bx-reset d-block d-sm-none"></i>
                                <span class="d-none d-sm-block"><spring:message code="view.pages.account.reset" /></span>
                            </button>

                            <p class="text-muted mb-0"><spring:message code="view.pages.account.size-photo" /></p>
                        </div>
                    </div>
                </div>
                <hr class="my-0">
                <div class="card-body">
                    <c:url var="updateAction" value="/admin/user/${user.id}"/>
                    <form:form id="formAccountSettings" method="POST" modelAttribute="user" action="${updateAction}" enctype="multipart/form-data">
                        <form:hidden path="id" />
                        <form:hidden path="avatar" />
                        <form:hidden path="coverBg" />
                        <form:hidden path="password" />
                        <form:hidden path="modifiedDate"/>
                        <form:hidden path="slug" />
                        <form:hidden path="status" />
                        <div class="row">
                            <div class="mb-3 col-md-6">
                                <label for="firstName" class="form-label">
                                    <spring:message code="view.pages.account.name" />
                                </label>
                                <form:input class="form-control" type="text" id="firstName" path="displayName" />
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="email" class="form-label">E-mail</label>
                                <form:input class="form-control" type="text" id="email" path="email" placeholder="john.doe@example.com" />
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="organization" class="form-label">
                                    <spring:message code="view.pages.account.alumniId" />
                                </label>
                                <form:input type="text" class="form-control" id="organization" path="alumniId" />
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="join-date" class="form-label">
                                    <spring:message code="view.pages.user.join-date" />
                                </label>
                                <form:input type="text" class="form-control" id="join-date" path="createdDate" disabled="true" />
                            </div>
                            <div class="mb-3 col-md-6">
                                <label for="language" class="form-label">
                                    <spring:message code="view.pages.account.role" />
                                </label>
                                <form:select id="language" path="role" class="select2 form-select">
                                    <c:forEach items="${roles}" var="role">
                                        <c:choose>
                                            <c:when test="${role.id == user.role.id}">
                                                <option value="${role.id}" selected>${role.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${role.id}">${role.name}</option>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="mt-2">
                            <button type="submit" class="btn btn-primary me-2">
                                <spring:message code="view.pages.account.save-changes" />
                            </button>
                            <button type="reset" class="btn btn-outline-secondary">Cancel</button>
                        </div>
                    </form:form>
                </div>
                <!-- /Account -->
            </div>
            <div class="card">
                <h5 class="card-header">Delete Account</h5>
                <div class="card-body">
                    <div class="mb-3 col-12 mb-0">
                        <div class="alert alert-warning">
                            <h6 class="alert-heading fw-bold mb-1">Are you sure you want to delete your account?</h6>
                            <p class="mb-0">Once you delete your account, there is no going back. Please be certain.</p>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${user.status == status[1]}">
                            <c:url value="/admin/user/${user.id}/${status[0]}" var="action" />
                            <div id="formAccountDeactivation">
                                <div class="form-check mb-3">
                                    <input class="form-check-input" type="checkbox" name="accountActivation" id="accountActivation">
                                    <label class="form-check-label" for="accountActivation">I confirm my account deactivation</label>
                                </div>
                                <button class="btn btn-danger deactivate-account" onclick="changeStatus('${action}')">
                                    <spring:message code="view.pages.account.btnDeactiveText"/>
                                </button>
                            </div>
                        </c:when>
                        <c:otherwise >
                            <c:url value="/admin/user/${user.id}/${status[1]}" var="action"/>
                            <div id="formAccountDeactivation">
                                <button class="btn btn-success deactivate-account" onclick="changeStatus('${action}')">
                                    <spring:message code="view.pages.account.btnActiveText"/>
                                </button>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <script>
        function changeStatus(endpoint) {
            fetch(endpoint, {
                method: "PUT"
            })
                    .then(res => {
                        if (res.status === 200) {
                            location.reload();
                        } else {
                            alert("SOMETHING WRONG!!!");
                        }
                    });
        }

    </script>
</c:if>