<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="context"/>
<!-- build:js assets/vendor/js/core.js -->
<script src="<c:url value="${context}vendor/libs/jquery/jquery.js" />" ></script>
<script src="<c:url value="${context}vendor/libs/popper/popper.js" />" ></script>
<script src="<c:url value="${context}vendor/js/bootstrap.js" />" ></script>
<script src="<c:url value="${context}vendor/libs/perfect-scrollbar/perfect-scrollbar.js" />" ></script>

<script src="<c:url value="${context}vendor/js/menu.js" />" ></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="<c:url value="${context}vendor/libs/apex-charts/apexcharts.js" />" ></script>

<!-- Main JS -->
<script src="<c:url value="${context}js/main.js" />" ></script>

<!-- Page JS -->
<script src="<c:url value="${context}js/dashboards-analytics.js" />" ></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>