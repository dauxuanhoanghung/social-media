<%-- 
    Document   : login
    Created on : Jul 13, 2023, 10:41:40 AM
    Author     : DinhChuong
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-success">ĐĂNG NHẬP NGƯỜI DÙNG</h1>
<c:url value="/login" var="action" />
<form method="post" action="/login">
    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="username" placeholder="Tên đăng nhập" name="username">
        <label for="username">Tên đăng nhập</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="password" class="form-control" id="pwd" placeholder="Mật khẩu" name="password">
        <label for="pwd">Mật khẩu</label>
    </div>

    <input type="submit" value="Đăng nhập" class="btn btn-danger" />
</form>