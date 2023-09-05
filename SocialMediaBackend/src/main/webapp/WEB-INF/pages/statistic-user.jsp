<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="fw-bold py-3 mb-4">
    <span class="text-muted fw-light">
        <spring:message code="view.pages.statistic-user.statistic" /> /
    </span> 
    <spring:message code="view.pages.statistic-user.users" />
</h4>


<script src="<c:url value="/js/stats.js"/>"></script>
<script>
    let data = [], labels = [];
    <c:forEach items="${listTop10}" var="c">
    labels.push('${c[1]}');
    data.push(${c[2]});
    </c:forEach>
    window.onload = () => {
        draw(data, labels, "Thống kê người dùng", 'donut');
    };
</script>