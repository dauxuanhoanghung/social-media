<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="currentPage" value="${param['page']}" />
<h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">
        <spring:message code="view.pages.post.post" /> /</span> <spring:message code="view.pages.post.manage-all" />
</h4>
<div class="card">
    <div class="card-header d-flex justify-content-between">
        <div>
            <h5 class="align-items-center">
                <spring:message code="view.pages.post.table-title" />
            </h5>
        </div>
    </div>
    <div class="table-responsive text-nowrap" style="min-height: 70vh">
        <c:if test="${not empty posts}">
            <table class="table">
                <caption class="ms-4">
                    <spring:message code="view.pages.post.table-title" />
                </caption>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th><spring:message code="view.pages.post.type" /></th>
                        <th><spring:message code="view.pages.post.countComment" /></th>
                        <th><spring:message code="view.pages.post.countAction" /></th>
                        <th><spring:message code="view.pages.user.actions" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="post" items="${posts}">
                        <tr>
                            <td>
                                <i class="fab fa-bootstrap fa-lg text-primary me-3"></i> 
                                <strong>${post.id}</strong>
                            </td>
                            <td>${post.type}</td>
                            <td>
                                ${post.countComment}
                            </td>
                            <td>
                                ${post.countAction}
                            </td>
                            <td>
                                <div class="dropdown">
                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                        <i class="bx bx-dots-vertical-rounded"></i>
                                    </button>
                                    <div class="dropdown-menu">
                                        <c:if test="${post.type eq 'SURVEY'}">
                                            <a class="dropdown-item" href="<c:url value="/admin/post/result/${post.id}"/>">
                                                <i class="bx bx-target-lock me-1"></i><spring:message code="view.pages.post.view-result" />
                                            </a>
                                        </c:if>
                                        <a class="dropdown-item" onclick="deletePost('<c:url value="/api/posts/${post.id}/delete/"/>')">
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
        <ul class="pagination justify-content-center">
            <c:forEach begin="1" end="${pages}" var="page">
                <li class="page-item <c:if test="${currentPage == page}">active</c:if>">
                    <a class="page-link" href="<c:url value="/admin/post?page=${page}" />">${page}</a>
                </li>
            </c:forEach>
        </ul>



        <c:if test="${empty posts}">
            <div class="alert alert-warning text-center" style="height: 100%" role="alert">
                <i class='bx bx-error-circle bx-lg'></i>
                <p class="mt-3">No post found</p>
            </div>
        </c:if>
    </div>
</div>

<script>
    const deletePost = (url) => {
        fetch(url, {
            method: "DELETE"
        })
                .then(res => {
                    if (res.status === 204) {
                        location.reload();
                    }
                })
    }
</script>