<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<c:url value="/" var="context"/>
<html
    lang="en"
    class="light-style layout-menu-fixed"
    dir="ltr"
    data-theme="theme-default"
    data-assets-path="../assets/"
    data-template="vertical-menu-template-free"
    >
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            />
        <title><tiles:insertAttribute name="title"/></title>
        <meta name="description" content="" />
        <tiles:insertAttribute name="css"/>
        <!-- Helpers -->
        <script src="<c:url value="${context}vendor/js/helpers.js" />"></script>
        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="<c:url value="${context}js/config.js" />"></script>
    </head>

    <body>
        <!-- Layout wrapper -->
        <div class="layout-wrapper layout-content-navbar">
            <div class="layout-container">
                <tiles:insertAttribute name="sidebar"/>

                <!-- Layout container -->
                <div class="layout-page">
                    <!-- Navbar -->
                    <tiles:insertAttribute name="header" />
                    <!-- / Navbar -->

                    <!-- Content wrapper -->
                    <div class="content-wrapper">
                        <!-- Content -->
                        <div class="container-xxl flex-grow-1 container-p-y">
                            <tiles:insertAttribute name="content" />
                        </div>
                        <!-- / Content -->

                        <tiles:insertAttribute name="footer"/>
                        <div class="content-backdrop fade"></div>
                    </div>
                    <!-- Content wrapper -->
                </div>
                <!-- / Layout page -->
            </div>

            <!-- Overlay -->
            <div class="layout-overlay layout-menu-toggle"></div>
        </div>
        <!-- / Layout wrapper -->
        <div id="loading">Loading...</div>
        <tiles:insertAttribute name="js" />
        <script>
            // Function to remove the loading element
            function removeLoading() {
                var loadingElement = document.getElementById("loading");
                loadingElement.parentNode.removeChild(loadingElement);
            }

            // Check if all resources have finished loading
            function checkResourcesLoaded() {
                var styleSheets = document.styleSheets;
                var scripts = document.scripts;

                for (var i = 0; i < styleSheets.length; i++) {
                    if (styleSheets[i].cssRules === null) {
                        // CSS is still loading
                        return false;
                    }
                }

                for (var j = 0; j < scripts.length; j++) {
                    if (scripts[j].readyState === "loading") {
                        // JavaScript is still loading
                        return false;
                    }
                }

                return true;
            }

            // Function to periodically check if resources have finished loading
            function checkLoadingStatus() {
                if (checkResourcesLoaded()) {
                    // All resources have loaded
                    removeLoading();
                } else {
                    // Keep checking after a short delay
                    setTimeout(checkLoadingStatus, 200);
                }
            }

            // Start checking the loading status when the page has finished loading
            window.addEventListener("load", checkLoadingStatus);
        </script>
    </body>
</html>
