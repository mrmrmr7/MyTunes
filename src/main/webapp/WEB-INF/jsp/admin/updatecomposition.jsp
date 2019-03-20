<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

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

<body style="display: block">


<!-- Left Panel -->

<jsp:include page="/WEB-INF/jsp/include/leftpanel.jsp"/>
<%--<jsp:include page="testpanel.jsp"/>--%>

<div id="right-panel" class="right-panel" style="width: 100%;">

    <!-- Header-->

    <jsp:include page="/WEB-INF/jsp/include/header.jsp"/>

    <!-- Header-->

    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1>Update composition panel</h1>
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


    <div class="content mt-3" style="width: 100%">

        <c:if test="${success}">
            <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
                <span class="badge badge-pill badge-dark">Success</span>
                You successful update composition!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">X</span>
                </button>
            </div>
        </c:if>

        <div class="card">
            <div class="card-body">
                <div class="tab-pane fade active show" id="profile" role="tabpanel" aria-labelledby="showFeedbackTab">
                    <form action="${pageContext.request.contextPath}/crud" method="post" id="ThisForm" class="form-horizontal" style="margin-bottom: 0">
                        <div class="row">
                            <div class="col col-md-12">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <button class="btn" id="btnClass" form="ThisForm" disabled="disabled">
                                            <i class="fa fa-search"></i> Show
                                        </button>
                                    </div>
                                    <input type="text" id="input" name="compositionName" placeholder="Composition name"
                                           class="form-control">
                                    <script>
                                        input.oninput = function () {
                                            document.getElementById("btnClass").className = "btn btn-primary";
                                            document.getElementById("btnClass").disabled = false;
                                        };
                                    </script>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="command" value="getCompositionForUpdate">
                    </form>
                </div>
            </div>
        </div>

        <c:if test="${showInf}">
            <div class="row">
                <div class="col-md-6 offset-md-3 mr-auto ml-auto">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Composition info</strong>
                        </div>
                        <div class="card-body">
                            <!-- Credit Card -->
                            <div id="pay-invoice">
                                    <form action="${pageContext.request.contextPath}/crud" method="post" id="updateBalanceForm" novalidate="novalidate">
                                        <div class="form-group">
                                            <label for="cc-pament" class="control-label mb-1">Name</label>
                                            <input id="cc-pament" name="compositionName" type="text" class="form-control" aria-required="true" aria-invalid="false" value="${composition.name}">
                                        </div>
                                        <div class="form-group has-success">
                                            <label for="cc-name" class="control-label mb-1">Year</label>
                                            <input id="cc-name" name="compositionYear" type="text"  value="${composition.year}" class="form-control cc-name valid" data-val="true" data-val-required="Please enter the name on card" autocomplete="cc-name" aria-required="true" aria-invalid="false" aria-describedby="cc-name-error">
                                            <span class="help-block field-validation-valid" data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="cc-number" class="control-label mb-1">Price</label>
                                            <input id="cc-number" name="compositionPrice" type="tel" class="form-control cc-number identified visa" value="${composition.price}" data-val="true" data-val-required="Please enter the card number" data-val-cc-number="Please enter a valid card number" autocomplete="cc-number">
                                            <span class="help-block" data-valmsg-for="cc-number" data-valmsg-replace="true"></span>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col col-md-3"><label for="SelectLm"
                                                                             class=" form-control-label">Album</label></div>
                                            <div class="col-12 col-md-9">
                                                <select name="albumId" id="SelectLm" class="form-control-sm form-control">>
                                                    <option value="0">Please select</option>
                                                    <c:forEach var="b" items="${albumList}">
                                                        <c:if test="${b.id == compositionAlbumId}">
                                                            <option value="${b.id}" selected="selected">${b.name}</option>
                                                        </c:if>
                                                        <c:if test="${b.id != compositionAlbumId}">
                                                            <option value="${b.id}">${b.name}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3"><label for="SelectLm1"
                                                                             class=" form-control-label">Author</label></div>
                                            <div class="col-12 col-md-9">
                                                <select name="authorId" id="SelectLm1" class="form-control-sm form-control">>
                                                    <option value="0">Please select</option>
                                                    <c:forEach var="b" items="${authorList}">
                                                        <c:if test="${b.id == compositionAuthorId}">
                                                            <option value="${b.id}" selected="selected">${b.pseudonim}</option>
                                                        </c:if>
                                                        <c:if test="${b.id != compositionAuthorId}">
                                                            <option value="${b.id}">${b.pseudonim}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div>
                                            <button id="payment-button" type="submit" form="updateBalanceForm" class="btn btn-lg btn-info btn-block">
                                                <span id="payment-button-amount">Update</span>
                                            </button>
                                        </div>
                                        <input type="hidden" name="compositionId" value="${composition.id}">
                                        <input type="hidden" name="command" value="updateComposition">
                                    </form>
                                </div>
                        </div>
                    </div> <!-- .card -->

                </div>
            </div>
        </c:if>
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