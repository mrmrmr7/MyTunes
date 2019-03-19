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

<body style="display:block;">
<!-- Left Panel -->

<jsp:include page="/WEB-INF/jsp/include/leftpanel.jsp"/>

<!-- Left Panel -->

<!-- Right Panel -->

<div id="right-panel" class="right-panel">

    <!-- Header-->

    <jsp:include page="/WEB-INF/jsp/include/header.jsp"/>


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

        <c:if test="${success}">
            <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                <span class="badge badge-pill badge-dark">Success</span>
                Feedback table about ${musicSelectionName} is shown!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
            </div>

        </c:if>

        <div class="animated fadeIn">

            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active show" id="showFeedbackTab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true">Show feedback</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="addFeedbackTab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="false">Add feedback</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="changeFeedbackTab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Change feedback</a>
                        </li>
                    </ul>
                    <div class="card" style="border-top: none">
                        <div class="card-body">

                            <div class="tab-content pl-3 p-1" id="myTabContent">
                                <div class="tab-pane fade active show" id="profile" role="tabpanel" aria-labelledby="showFeedbackTab">
                                    <form action="${pageContext.request.contextPath}/crud" method="post" class="form-horizontal">
                                        <div class="row">
                                            <div class="col col-md-12">
                                                <div class="input-group">
                                                    <div class="input-group-btn">
                                                        <button class="btn" id="btnClass" disabled="disabled">
                                                            <i class="fa fa-search"></i> Show
                                                        </button>
                                                    </div>
                                                    <input type="text" id="input" name="musicSelectionName"
                                                           placeholder="Music selection name" class="form-control">
                                                    <script>
                                                        input.oninput = function() {
                                                            document.getElementById("btnClass").className = "btn btn-primary";
                                                            document.getElementById("btnClass").disabled = false;
                                                        };
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="hidden" name="command" value="viewMusicSelectionFeedback">
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="addFeedbackTab">
                                    <h3>Home</h3>
                                    <p>Some content here.</p>
                                </div>
                                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="changeFeedbackTab">
                                    <h3>Menu 2</h3>
                                    <p>Some content here.</p>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Data Table</strong>
                        </div>
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
                                                    style="width: 263px;">Name
                                                </th>
                                                <th class="sorting" tabindex="0"
                                                    aria-controls="bootstrap-data-table-export" rowspan="1" colspan="1"
                                                    aria-label="Position: activate to sort column ascending"
                                                    style="width: 432px;">Genre
                                                </th>
                                                <th class="sorting" tabindex="0"
                                                    aria-controls="bootstrap-data-table-export" rowspan="1" colspan="1"
                                                    aria-label="Office: activate to sort column ascending"
                                                    style="width: 196px;">Author
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>


                                            <c:forEach var="feedbackDto" items="${feedbackDtoList}">

                                                <tr role="row" class="odd">
                                                    <td class ="sorting_1"><c:out value="${feedbackDto.login}"/></td>
                                                    <td><c:out value="${feedbackDto.description}"/></td>
                                                    <td><c:out value="${feedbackDto.date}"/></td>
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
