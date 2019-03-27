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
    <title><fmt:message key="title.shop" bundle="${bundle}" /></title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/site/images/logo_icon.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/selectFX/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/jqvmap/dist/jqvmap.min.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>

<body style="display:block;">
<!-- Left Panel -->

<jsp:include page="/WEB-INF/jsp/include/leftpanel.jsp"/>

<!-- Left Panel -->

<!-- Right Panel -->

<div id="right-panel" class="right-panel" style="width: 100%;">

    <!-- Header-->

    <header id="header" class="header">
        <div class="header-menu">
            <div class="col-sm-7">
            </div>
            <div class="col-sm-5">
                <div class="user-area dropdown float-right">
                    <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/crud?command=logout"><i
                                    class="fa fa-power-off" ></i><fmt:message key="composition.logout" bundle="${bundle}"/> </a>
                        </li>

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
                            <a class="nav-link" id="pills-home-tab"href="${pageContext.request.contextPath}/crud?command=changeLang&from=/crud?command=musicSelectionShop&lang=ru"
                               role="tab" aria-controls="pills-home" aria-selected="false">RU</a>
                        </li>
                        <li class="nav-item active ">
                            <a class="nav-link" id="pills-profile-tab" href="${pageContext.request.contextPath}/crud?command=changeLang&from=/crud?command=musicSelectionShop&lang=en"
                               role="tab" aria-controls="pills-profile" aria-selected="false">EN</a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </header><!-- /header -->


    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1><fmt:message key="shop.musicselection.info" bundle="${bundle}"/></h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li class="active"><fmt:message key="shop.musicselection.dashboard" bundle="${bundle}"/></li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="content mt-3">

        <c:if test="${success}">
            <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                <span class="badge badge-pill badge-dark"><fmt:message key="shop.musicselection.success" bundle="${bundle}"/></span>
                <fmt:message key="shop.musicselection.buynext" bundle="${bundle}"/> ${musicSelectionName}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
            </div>

        </c:if>

        <div class="animated fadeIn">
            <div class="row">
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            <strong><fmt:message key="shop.musicselection.musicelectionshop" bundle="${bundle}"/></strong><fmt:message key="shop.musicselection.buy" bundle="${bundle}"/>
                        </div>
                        <div class="card-body card-block">
                            <form action="${pageContext.request.contextPath}/crud" method="post" class="form-horizontal">
                                <div class="row form-group">
                                    <div class="col col-md-12">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <button id="btnClass" class="btn">
                                                    <i class="fa fa-search"></i><fmt:message key="shop.musicselection.button.buy" bundle="${bundle}"/>
                                                </button>
                                            </div>
                                            <input type="text" id="input" name="musicSelectionName"
                                                   placeholder="<fmt:message key="shop.musicselection.placeholder.name" bundle="${bundle}"/>" class="form-control">
                                            <script>
                                                input.oninput = function() {
                                                    document.getElementById("btnClass").className = "btn btn-primary";
                                                    document.getElementById("btnClass").disabled = false;
                                                };
                                            </script>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="command" value="buyMusicSelection">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <div id="bootstrap-data-table-export_wrapper"
                                 class="dataTables_wrapper dt-bootstrap4 no-footer">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <table id="bootstrap-data-table-export"
                                               class="table table-striped table-bordered dataTable no-footer"
                                               role="grid" aria-describedby="bootstrap-data-table-export_info">
                                            <thead>
                                            <tr role="row">
                                                <th class="sorting_asc" tabindex="0"
                                                    aria-controls="bootstrap-data-table-export" rowspan="1" colspan="1"
                                                    aria-sort="ascending"
                                                    aria-label="Name: activate to sort column descending"
                                                    style="width: 263px;"><fmt:message key="shop.musicselection.placeholder.name" bundle="${bundle}"/>
                                                </th>
                                                <th class="sorting" tabindex="0"
                                                    aria-controls="bootstrap-data-table-export" rowspan="1" colspan="1"
                                                    aria-label="Position: activate to sort column ascending"
                                                    style="width: 432px;"><fmt:message key="shop.musicselection.button.genre" bundle="${bundle}"/>
                                                </th>
                                                <th class="sorting" tabindex="0"
                                                    aria-controls="bootstrap-data-table-export" rowspan="1" colspan="1"
                                                    aria-label="Office: activate to sort column ascending"
                                                    style="width: 196px;"><fmt:message key="shop.musicselection.button.genre" bundle="${bundle}"/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            <c:forEach var="musicSelectionInfo" items="${musicSelectionInfoList}">

                                                <tr role="row" class="odd">
                                                    <td class ="sorting_1"><c:out value="${musicSelectionInfo.name}"/></td>
                                                    <td><c:out value="${musicSelectionInfo.description}"/></td>
                                                    <td><c:out value="${musicSelectionInfo.price}"/></td>
                                                </tr>

                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div> <!-- .content -->


    <!-- Header-->

</div><!-- /#right-panel -->
<!-- Right Panel -->




<script src="${pageContext.request.contextPath}/site/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/jszip/dist/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/pdfmake/build/vfs_fonts.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/datatables.net-buttons/js/buttons.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/site/assets/js/init-scripts/data-table/datatables-init.js"></script>

</body>
</html>
