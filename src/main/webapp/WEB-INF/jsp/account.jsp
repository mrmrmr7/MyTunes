<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

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

<body>


<!-- Left Panel -->

<c:if test="${empty userDto}">
    <jsp:include page="include/clientleftpanel.jsp"/>
</c:if>
<c:if test="${not empty userDto}">
    <jsp:include page="include/${userDto.role.role}leftpanel.jsp"/>
</c:if>

<!-- Left Panel -->

<!-- Right Panel -->

<div id="right-panel" class="right-panel">

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
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <div class="mx-auto d-block">
                        <img class="rounded-circle mx-auto d-block" src="${pageContext.request.contextPath}/static/images/admin.jpg" alt="Card image cap">
                        <h5 class="text-sm-center mt-2 mb-1">${userDto.login}</h5>
                        <div class="location text-sm-center"><i class="fa fa-map-marker"></i> ${userDto.role.role} ${userDto.status.status}</div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table table-bordered">
                        <tbody>

                        <tr>
                            <th scope="row">First name</th>
                            <td>${userDto.first_name}</td>
                            <th scope="row">Second name</th>
                            <td>${userDto.second_name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Email</th>
                            <td>${userDto.email}</td>
                            <th scope="row">Balance</th>
                            <td>${userDto.balance}</td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div> <!-- .content -->

    <div class="col-sm-12 mb-4">
        <div class="card-group">
            <div class="card col-lg-2 col-md-6 no-padding">
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
                <div class="card-body bg-flat-color-2">
                    <div class="h1 text-muted text-right mb-4">
                        <i class="fa fa-user-plus text-light"></i>
                    </div>
                    <div class="h4 mb-0 text-light">
                        <span class="count">${fn:length(userDto.compositionList)}</span>
                    </div>
                    <small class="text-uppercase font-weight-bold text-light">Compositions</small>
                    <div class="progress progress-xs mt-3 mb-0 bg-light" style="width: 40%; height: 5px;"></div>
                </div>
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
                <div class="card-body bg-flat-color-3">
                    <div class="h1 text-right mb-4">
                        <i class="fa fa-cart-plus text-light"></i>
                    </div>
                    <div class="h4 mb-0 text-light">
                        <span class="count">${fn:length(userDto.albumList)}</span>
                    </div>
                    <small class="text-light text-uppercase font-weight-bold">Albums</small>
                    <div class="progress progress-xs mt-3 mb-0 bg-light" style="width: 40%; height: 5px;"></div>
                </div>
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
                <div class="card-body bg-flat-color-5">
                    <div class="h1 text-right text-light mb-4">
                        <i class="fa fa-pie-chart"></i>
                    </div>
                    <div class="h4 mb-0 text-light">
                        <span class="count">${fn:length(userDto.musicSelectionList)}</span>
                    </div>
                    <small class="text-uppercase font-weight-bold text-light">Music selections</small>
                    <div class="progress progress-xs mt-3 mb-0 bg-light" style="width: 40%; height: 5px;"></div>
                </div>
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
                <div class="card-body bg-flat-color-4">
                    <div class="h1 text-light text-right mb-4">
                        <i class="fa fa-clock-o"></i>
                    </div>
                    <div class="h4 mb-0 text-light">${fn:length(userDto.bonusList)}</div>
                    <small class="text-light text-uppercase font-weight-bold">Bonus</small>
                    <div class="progress progress-xs mt-3 mb-0 bg-light" style="width: 40%; height: 5px;"></div>
                </div>
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
            </div>
        </div>

    </div>
</div><!-- /#right-panel -->

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