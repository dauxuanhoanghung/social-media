<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h1 class="text-center text-success"><spring:message code="view.pages.login.title" /></h1>
<c:url value="/login" var="action" />
<form method="post" action="/login">
    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="username" placeholder="<spring:message code="view.pages.login.username" />..." name="username">
        <label for="username"><spring:message code="view.pages.login.username" /></label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="password" class="form-control" id="pwd" placeholder="<spring:message code="view.pages.login.password" />..." name="password">
        <label for="pwd"><spring:message code="view.pages.login.password" /></label>
    </div>

    <input type="submit" value="<spring:message code="view.pages.login.login-btn" />" class="btn btn-danger" />
</form>