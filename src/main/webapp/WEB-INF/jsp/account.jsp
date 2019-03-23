<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="language" var="bundle" scope="application"/>
<html>
<!--<![endif]-->

<head>

    <fmt:requestEncoding value="UTF-8"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta charset="UTF-8"/>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/site/favicon.ico">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/themify-icons/css/themify-icons.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/flag-icon-css/css/flag-icon.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/selectFX/css/cs-skin-elastic.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/jqvmap/dist/jqvmap.min.css"/>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/assets/css/style.css"/>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>

<body style="display: block">


<!-- Left Panel -->

<jsp:include page="include/leftpanel.jsp"/>

<div id="right-panel" class="right-panel">

    <!-- Header-->

    <header id="header" class="header">
        <div class="header-menu">
            <div class="col-sm-7">
            </div>
            <div class="col-sm-5">
                <div class="user-area dropdown float-right">
                    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

                        <a href="${pageContext.request.contextPath}/#" class="dropdown-toggle" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <img class="user-avatar rounded-circle"
                                 src="${pageContext.request.contextPath}/site/images/admin.jpg" alt="User Avatar">
                        </a>

                        <div class="user-menu dropdown-menu">
                            <a class="nav-link" href="${pageContext.request.contextPath}/#" ><i class="fa fa-user"></i>
                                My Profile</a>

                            <a class="nav-link" href="${pageContext.request.contextPath}/#"><i class="fa fa-user"></i>
                                Notifications <span class="count">13</span></a>

                            <a class="nav-link" href="${pageContext.request.contextPath}/#"><i class="fa fa-cog"></i>
                                Settings</a>

                            <a class="nav-link" href="${pageContext.request.contextPath}/crud?command=logout"><i
                                    class="fa fa-power-off"></i>Logout</a>
                        </div>
                        <li class="nav-item">
                            <a class="nav-link" id="pills-home-tab"href="${pageContext.request.contextPath}/crud?command=changeLang&from=${pageContext.request.contextPath}/crud?command=account&lang=ru"
                               role="tab" aria-controls="pills-home" aria-selected="false">RU</a>
                        </li>
                        <li class="nav-item active ">
                            <a class="nav-link" id="pills-profile-tab" href="${pageContext.request.contextPath}/crud?command=changeLang&from=${pageContext.request.contextPath}/crud?command=account&lang=en"
                               role="tab" aria-controls="pills-profile" aria-selected="false">EN</a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </header><!-- /header -->
    <!-- Header-->

    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1><fmt:message key="account.accountinfo" bundle="${bundle}"/></h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li class="active"><fmt:message key="account.dashboard" bundle="${bundle}"/></li>
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
                        <img class="rounded-circle mx-auto d-block"
                             src="${pageContext.request.contextPath}/static/images/admin.jpg" alt="Card image cap">
                        <h5 class="text-sm-center mt-2 mb-1">${userDto.login}</h5>
                        <div class="location text-sm-center"><i
                                class="fa fa-map-marker"></i> ${userDto.role.role} ${userDto.status.status}</div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table table-bordered">
                        <tbody>

                        <tr>
                            <th scope="row"><fmt:message key="account.firstname" bundle="${bundle}"/></th>
                            <td>${userDto.first_name}</td>
                            <th scope="row"><fmt:message key="account.secondname" bundle="${bundle}"/></th>
                            <td>${userDto.second_name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Email</th>
                            <td>${userDto.email}</td>
                            <th scope="row"><fmt:message key="account.balance" bundle="${bundle}"/></th>
                            <td>${userDto.balance}$</td>
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
                    <small class="text-uppercase font-weight-bold text-light"><fmt:message key="account.panel.composition" bundle="${bundle}"/></small>
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
                    <small class="text-light text-uppercase font-weight-bold"><fmt:message key="account.panel.album" bundle="${bundle}"/></small>
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
                    <small class="text-uppercase font-weight-bold text-light"><fmt:message key="account.panel.musicselection" bundle="${bundle}"/></small>
                    <div class="progress progress-xs mt-3 mb-0 bg-light" style="width: 40%; height: 5px;"></div>
                </div>
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
                <div class="card-body bg-flat-color-4">
                    <div class="h1 text-light text-right mb-4">
                        <i class="fa fa-clock-o"></i>
                    </div>
                    <div class="h4 mb-0 text-light">${fn:length(userDto.bonusList)}</div>
                    <small class="text-light text-uppercase font-weight-bold"><fmt:message key="account.panel.bonus" bundle="${bundle}"/></small>
                    <div class="progress progress-xs mt-3 mb-0 bg-light" style="width: 40%; height: 5px;"></div>
                </div>
            </div>
            <div class="card col-lg-2 col-md-6 no-padding no-shadow">
            </div>
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
    (function ($) {
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