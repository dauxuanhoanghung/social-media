<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="fw-bold py-3 mb-4">
    <span class="text-muted fw-light">
        <spring:message code="view.pages.statistic-user.statistic" /> /
    </span> 
    <spring:message code="view.pages.statistic-user.users" />
</h4>

<div class="col-12 order-2 order-md-3 order-lg-2 mb-4">
    <div class="card">
        <div id="barChart"></div>
    </div>
</div>

<div class="col-12 col-lg-6 order-2 order-md-3 order-lg-2 mb-4">
    <div class="card">
        <div class='d-flex justify-content-between'>
            <div class="d-flex align-items-center justify-content-center">
                <div class='m-2'>
                    <input type="radio" id="monthRadio" name="selection" value="month" onclick="showSelection()" checked> 
                    <label for="monthRadio"><spring:message code="view.pages.statistic-user.month"/></label>
                </div>
                <div class='m-2'>
                    <input type="radio" id="quarterRadio" name="selection" value="quarter" onclick="showSelection()"> 
                    <label for="quarterRadio"><spring:message code="view.pages.statistic-user.quarter"/></label>
                </div>
            </div>
            <div style="margin-left: auto" class="d-flex m-2">
                <div id="monthSelection" class="m-2" style="display: flex">
                    <label for="month"><spring:message code="view.pages.statistic-user.selectMonth"/></label>
                    <select class="form-control"id="month" name="month">
                        <c:forEach begin="1" end="12" var="monthNumber">
                            <option value="${monthNumber}">${monthNumber}</option>
                        </c:forEach>
                    </select>
                </div>
                <div id="quarterSelection" style="display: none;" class="m-2">
                    <label for="quarter"><spring:message code="view.pages.statistic-user.selectQuarter"/></label>
                    <select id="quarter" name="quarter" class="form-control">
                        <c:forEach begin="1" end="4" var="quarter">
                            <option value="${quarter}">Q${quarter}</option>
                        </c:forEach>
                    </select>
                </div>
                <div id="yearSelection" class="m-2 d-flex">
                    <label for="year"><spring:message code="view.pages.statistic-user.selectYear"/></label>
                    <select id="year" name="year" class="form-control ">
                        <c:forEach begin="2022" end="${currentYear}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-warning" onclick="getData()">
                    <spring:message code="view.pages.statistic-user.view"/>
                </button>
            </div>
        </div>
        <div id="donutChart"></div>
    </div>
</div>

<div class="col-12 col-lg-6 order-2 order-md-3 order-lg-2 mb-4">
    <div class="card">
        <div id="lineChart"></div>
    </div>
</div>
<script src="<c:url value="/js/stats.js"/>"></script>
<script>
                    // Radio Check
                    const showSelection = () => {
                        var selectedOption = document.querySelector('input[name="selection"]:checked').value;
                        var monthSelection = document.getElementById("monthSelection");
                        var quarterSelection = document.getElementById("quarterSelection");
                        monthSelection.style.display = "none";
                        quarterSelection.style.display = "none";
                        if (selectedOption === "month")
                            monthSelection.style.display = "flex";
                        else if (selectedOption === "quarter")
                            quarterSelection.style.display = "flex";

                    }
                    const fetchDataBarChart = () => {

                    }
                    const formatDate = (originalDateString) => {
                        const originalDate = new Date(originalDateString);
                        const day = originalDate.getDate();
                        const month = originalDate.getMonth() + 1; // Months are zero-based, so add 1
                        const year = originalDate.getFullYear();
                        const hours = originalDate.getHours();
                        const minutes = originalDate.getMinutes();
                        const seconds = originalDate.getSeconds();
                        const formattedDate = `\${day.toString().padStart(2, '0')}-\${month.toString().padStart(2, '0')}-\${year}`;
                        const formattedTime = `\${hours.toString().padStart(2, '0')}:\${minutes.toString().padStart(2, '0')}:\${seconds.toString().padStart(2, '0')}`;
                        return formattedDate + " " + formattedTime;
                    }
                    const getData = () => {
                        const selectedOption = document.querySelector('input[name="selection"]:checked').value;
                        const selectedMonth = document.getElementById("month").value;
                        const selectedQuarter = document.getElementById("quarter").value;
                        const selectedYear = document.getElementById("year").value;
                        let fromDate = "";
                        let toDate = "";
                        const formatter = new Intl.DateTimeFormat('en-US', {
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit',
                            hour: 'numeric',
                            minute: 'numeric',
                            second: 'numeric',
                            hour12: true
                        });
                        if (selectedOption === 'quarter') {
                            const quarterToMonth = {1: 1, 2: 4, 3: 7, 4: 10};
                            const startMonth = quarterToMonth[selectedQuarter];
                            const endMonth = startMonth + 2;
                            fromDate = new Date(selectedYear, startMonth - 1, 1, 0, 0, 0); // -1 since months are zero-based
                            toDate = new Date(selectedYear, endMonth - 1, 31, 23, 59, 59); // Assuming end of month and day
                            fromDate = formatter.format(fromDate);
                            toDate = formatter.format(toDate);
                        } else if (selectedOption === 'month') {
                            const monthNumber = parseInt(selectedMonth);
                            fromDate = new Date(selectedYear, monthNumber - 1, 1, 0, 0, 0); // -1 since months are zero-based
                            const lastDayOfMonth = new Date(selectedYear, monthNumber, 0); // This gives the last day of the month
                            toDate = new Date(selectedYear, monthNumber - 1, lastDayOfMonth.getDate(), 23, 59, 59);
                            fromDate = formatter.format(fromDate);
                            toDate = formatter.format(toDate);
                        }
                        const baseUrl = '<c:url value="/admin/statistic/user-register/" />'; // Replace with your actual base URL
                        const queryParams = `?fromDate=\${encodeURIComponent(formatDate(fromDate))}&toDate=\${encodeURIComponent(formatDate(toDate))}`;
                        const fullUrl = baseUrl + queryParams;
                        console.log(fullUrl);
                        fetch(`\${fullUrl}`)
                                .then(res => res.json())
                                .then(data => {
                                    let info = [], label = [];
                                    data.forEach(i => {
                                        label.push(i[1]);
                                        info.push(i[2]);
                                    })
                                    draw(info, label, 'New User Register');
                                })
                                .catch()
                    }
                    const drawBarChart = () => {
                        let countPost = [];
                        let countComment = [];
                        let countAction = [];
                        let username = [];
    <c:forEach items="${listTop10}" var="c">
                        username.push('${c[0].displayName}')
                        countPost.push(${c[1]});
                        countComment.push(${c[2]});
                        countAction.push(${c[3]});
    </c:forEach>
                        console.log('username', username);
                        console.log('countPost', countPost);
                        console.log('countComment', countComment);
                        const series = [{
                                name: '<spring:message code="view.pages.statistic-user.countPost" />',
                                data: countPost
                            }, {
                                name: '<spring:message code="view.pages.statistic-user.countComment" />',
                                data: countComment
                            }, {
                                name: '<spring:message code="view.pages.statistic-user.countAction" />',
                                data: countAction
                            }];
                        drawColChart(series, username, '<spring:message code="view.pages.statistic-user.mostActiveUser"/>', 'bar', "barChart");
                    }
                    const drawUserRegisterChart = () => {
                        const baseUrl = '<c:url value="/admin/statistic/user-register/" />';
                        fetch(`\${baseUrl}`)
                                .then(res => res.json())
                                .then(data => {
                                    console.log(data);
                                    let info = [], label = [];
                                    data.forEach(i => {
                                        label.push(i[1]);
                                        info.push(i[2]);
                                    })
                                    console.log(info)
                                    draw(info, label, 'New User Register');
                                })
                    }
                    window.onload = () => {
                        drawBarChart();
                        drawUserRegisterChart();
                    };
</script>