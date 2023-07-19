<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- Footer -->
<footer class="content-footer footer bg-footer-theme">
    <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
        <div class="mb-2 mb-md-0">
            ©
            <script>
                document.write(new Date().getFullYear());
            </script>
            <spring:message code="view.layout.footer.text-made" />
            <a href="https://themeselection.com" target="_blank" class="footer-link fw-bolder"><spring:message code="view.layout.footer.author" /></a>
        </div>
    </div>
</footer>
<!-- / Footer -->
