<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">

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

<jsp:include page="/WEB-INF/jsp/include/leftpanel.jsp"/>

<!-- Left Panel -->

<!-- Right Panel -->

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
                            <a class="nav-link" id="pills-home-tab"href="${pageContext.request.contextPath}/crud?command=changeLang&lang=ru&from=${pageContext.request.contextPath}/crud?command=music"
                               role="tab" aria-controls="pills-home" aria-selected="false">RU</a>
                        </li>
                        <li class="nav-item active ">
                            <a class="nav-link" id="pills-profile-tab" href="${pageContext.request.contextPath}/crud?command=changeLang&lang=en&from=${pageContext.request.contextPath}/crud?command=music"
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

        <div class="animated fadeIn">

            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active show" id="showFeedbackTab" data-toggle="tab" href="#profile"
                               role="tab" aria-controls="profile" aria-selected="true">Compositions</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="addFeedbackTab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="false">Albums</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="changeFeedbackTab" data-toggle="tab" href="#contact" role="tab"
                               aria-controls="contact" aria-selected="false">Music selections</a>
                        </li>
                    </ul>
                    <div class="card" style="border-top: none">
                        <div class="card-body">

                            <div class="tab-content pl-1 p-1" id="myTabContent">

                                <div class="tab-pane fade active show" id="profile" role="tabpanel"
                                     aria-labelledby="showFeedbackTab">

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <table class="table table-striped table-bordered dataTable no-footer"
                                                               role="grid"
                                                               aria-describedby="bootstrap-data-table-export_info">
                                                            <thead>
                                                            <tr role="row">
                                                                <th class="sorting_asc" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Name
                                            : activate to sort column descending" style="width: 280px;"
                                                                    aria-sort="ascending">Name
                                                                </th>
                                                                <th class="sorting" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Album
                                            : activate to sort column ascending" style="width: 476px;">Album
                                                                </th>
                                                                <th class="sorting" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Author
                                            : activate to sort column ascending" style="width: 202px;">Author
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>


                                                            <c:forEach var="compositionDto"
                                                                       items="${userMusicDto.compositionDtoList}">

                                                                <tr role="row" class="odd">
                                                                    <td class="sorting_1"><c:out
                                                                            value="${compositionDto.name}"/></td>
                                                                    <td><c:out value="${compositionDto.album}"/></td>
                                                                    <td><c:out value="${compositionDto.author}"/></td>
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

                                <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="addFeedbackTab">

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <table class="table table-striped table-bordered dataTable no-footer"
                                                               role="grid"
                                                               aria-describedby="bootstrap-data-table-export_info">
                                                            <thead>
                                                            <tr role="row">
                                                                <th class="sorting_asc" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Name
                                            : activate to sort column descending" style="width: 280px;"
                                                                    aria-sort="ascending">Name
                                                                </th>
                                                                <th class="sorting" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Album
                                            : activate to sort column ascending" style="width: 476px;">Genre
                                                                </th>
                                                                <th class="sorting" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Author
                                            : activate to sort column ascending" style="width: 202px;">Author
                                                                </th>
                                                                <th class="sorting" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Author
                                            : activate to sort column ascending" style="width: 202px;">Year
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>


                                                            <c:forEach var="albumDto"
                                                                       items="${userMusicDto.albumDtoList}">

                                                                <tr role="row" class="odd">
                                                                    <td class="sorting_1"><c:out
                                                                            value="${albumDto.name}"/></td>
                                                                    <td><c:out value="${albumDto.genre}"/></td>
                                                                    <td><c:out value="${albumDto.author}"/></td>
                                                                    <td><c:out value="${albumDto.year}"/></td>
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

                                <div class="tab-pane fade" id="contact" role="tabpanel"
                                     aria-labelledby="changeFeedbackTab">


                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <table class="table table-striped table-bordered dataTable no-footer"
                                                               role="grid"
                                                               aria-describedby="bootstrap-data-table-export_info">
                                                            <thead>
                                                            <tr role="row">
                                                                <th class="sorting_asc" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Name
                                            : activate to sort column descending" style="width: 280px;"
                                                                    aria-sort="ascending">Name
                                                                </th>
                                                                <th class="sorting" tabindex="0"
                                                                    aria-controls="bootstrap-data-table-export"
                                                                    rowspan="1" colspan="1" aria-label="Album
                                            : activate to sort column ascending" style="width: 476px;">Description
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>


                                                            <c:forEach var="musicSelectionInfo"
                                                                       items="${userMusicDto.musicSelectionInfoList}">

                                                                <tr role="row" class="odd">
                                                                    <td class="sorting_1"><c:out
                                                                            value="${musicSelectionInfo.name}"/></td>
                                                                    <td><c:out value="${musicSelectionInfo.description}"/></td>
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
