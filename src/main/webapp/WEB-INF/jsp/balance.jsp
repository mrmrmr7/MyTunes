<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/site/favicon.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/selectFX/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/jqvmap/dist/jqvmap.min.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>

<body style="display: block">


<!-- Left Panel -->

<jsp:include page="include/leftpanel.jsp"/>

<%--<jsp:include page="testpanel.jsp"/>--%>

<div id="right-panel" class="right-panel" style="width: 100%;">

    <!-- Header-->

    <jsp:include page="include/header.jsp"/>

    <!-- Header-->

    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1>Account information</h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li class="active">Dashboard</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>



    <div class="content mt-3">
        <c:if test="${not empty newBalance}" >
            <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                <span class="badge badge-pill badge-dark">Success</span>
                Your balance equals ${newBalance}$
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
            </div>
        </c:if>

        <div class="animated fadeIn">
            <jsp:include page="user/include/balance/${balanceType}.jsp"/>
        </div>

    </div>



</div><!-- /#right-panel -->


<!-- Left Panel -->

<!-- Right Panel -->

<!-- Right Panel -->

<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/site/vendors/chart.js/dist/Chart.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/site/assets/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/site/assets/js/widgets.js"></script>
<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/site/vendors/jqvmap/dist/jquery.vmap.min.js"></script>
<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/site/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
<script src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/site/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
<script>
    (function($) {
        "use strict";

        jQuery('#vmap').vectorMap({
            map: 'world_en',
            backgroundColor: null,
            color: '#ffffff',
            hoverOpacity: 0.7,
            selectedColor: '#1de9b6',
            enableZoom: true,
            showTooltip: true,
            values: sample_data,
            scaleColors: ['#1de9b6', '#03a9f5'],
            normalizeFunction: 'polynomial'
        });
    })(jQuery);
</script>

</body>

</html>