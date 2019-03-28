<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <title><fmt:message key="admin.page" bundle="${bundle}"/></title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="/site/images/logo_icon.png">

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

<jsp:include page="/WEB-INF/jsp/include/leftpanel.jsp"/>
<%--<jsp:include page="testpanel.jsp"/>--%>

<div id="right-panel" class="right-panel"  style="width: 100%;">

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
                            <a class="nav-link" id="pills-home-tab"href="${pageContext.request.contextPath}/crud?command=changeLang&from=/crud?command=viewAdminCreateAlbumPage&lang=ru"
                               role="tab" aria-controls="pills-home" aria-selected="false">RU</a>
                        </li>
                        <li class="nav-item active ">
                            <a class="nav-link" id="pills-profile-tab" href="${pageContext.request.contextPath}/crud?command=changeLang&from=/crud?command=viewAdminCreateAlbumPage&lang=en"
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
                    <h1><fmt:message key="admin.createalbum.info" bundle="${bundle}"/></h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li class="active"><fmt:message key="admin.createalbum.admin" bundle="${bundle}"/></li>
                    </ol>
                </div>
            </div>
        </div>
    </div>


    <div class="content mt-3" style="width: 100%">

        <c:if test="${success}">
            <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                <span class="badge badge-pill badge-dark">Success</span>
                You successful add album!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
            </div>
        </c:if>

        <div class="row">

            <div class="col-lg-8 offset-md-3 mr-auto ml-auto">
                <div class="card">
                    <div class="card-header">
                        <strong><fmt:message key="admin.createalbum.album" bundle="${bundle}"/></strong> information
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/crud" method="post" id="addUserBonusFormId" class="form-horizontal">
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="compositionNameId" class=" form-control-label">Name</label></div>
                                <div class="col-12 col-md-9">
                                    <input type="text" id="compositionNameId" name="albumName" placeholder="Enter name"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="compositionPriceId" class=" form-control-label"><fmt:message key="admin.createalbum.price" bundle="${bundle}"/></label></div>
                                <div class="col-12 col-md-9">
                                    <input type="number" min="0" id="compositionPriceId" name="albumPrice" placeholder="Enter price"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="compositionYearId" class=" form-control-label"><fmt:message key="admin.createalbum.year" bundle="${bundle}"/></label></div>
                                <div class="col-12 col-md-9">
                                    <input type="text" id="compositionYearId" name="albumYear" placeholder="Enter year"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="SelectLm1"
                                                                 class=" form-control-label"><fmt:message key="admin.createalbum.author" bundle="${bundle}"/></label></div>
                                <div class="col-12 col-md-9">
                                    <select name="authorId" id="SelectLm1" class="form-control-sm form-control">>
                                        <option value="0">Please select</option>
                                        <c:forEach var="b" items="${authorList}">
                                            <option value="${b.id}">${b.pseudonim}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="SelectLm2"
                                                                 class=" form-control-label"><fmt:message key="admin.createalbum.genre" bundle="${bundle}"/></label></div>
                                <div class="col-12 col-md-9">
                                    <select name="genreId" id="SelectLm2" class="form-control-sm form-control">>
                                        <option value="0">Please select</option>
                                        <c:forEach var="b" items="${genreList}">
                                            <option value="${b.id}">${b.genre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="textarea-input" class=" form-control-label"><fmt:message key="admin.createalbum.descr" bundle="${bundle}"/></label>
                                </div>
                                <div class="col-12 col-md-9"><textarea name="albumDescription" id="textarea-input" rows="9" placeholder="Content..." class="form-control"></textarea>
                                </div>
                            </div>
                            <input type="hidden" name="command" value="addAlbum">
                        </form>
                    </div>
                    <div class="card-footer">
                        <button type="submit" form="addUserBonusFormId" class="btn btn-primary btn-sm">
                            <i class="fa fa-dot-circle-o"></i> <fmt:message key="admin.createalbum.submit" bundle="${bundle}"/>
                        </button>
                        <button type="reset" class="btn btn-danger btn-sm">
                            <i class="fa fa-ban"></i> <fmt:message key="admin.createalbum.reset" bundle="${bundle}"/>
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div> <!-- .content -->

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