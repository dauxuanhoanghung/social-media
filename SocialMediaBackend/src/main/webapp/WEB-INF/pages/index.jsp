<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>HOME - DASHBOARD</h1>

<div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4">
    <div class="card">
        <div class="row row-bordered g-0">
            <div class="col-md-8">
                <h5 class="card-header m-0 me-2 pb-3">Total Revenue</h5>
                <div id="donutChart" class="px-2" style="min-height: 315px;">
                </div>
                <div class="resize-triggers">
                    <div class="expand-trigger">
                        <div style="width: 513px; height: 377px;">
                        </div>
                    </div>
                    <div class="contract-trigger">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="donutChart"></div>
<c:forEach items="${userStats}" var="stats">
    <h1>${stats[0]} - ${stats[1]} - ${stats[2]}</h1>
    <div id="donutChart"></div>
</c:forEach>

<script src="<c:url value="/js/stats.js"/>"></script>
<script>
    let data = [], labels = [];
    <c:forEach items="${userStats}" var="c">
    labels.push('${c[1]}');
    data.push(${c[2]});
    </c:forEach>
    window.onload = () => {
        draw(data, labels, "Thống kê người dùng", 'donut');
    };
</script>