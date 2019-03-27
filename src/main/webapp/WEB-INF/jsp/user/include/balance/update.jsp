<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>


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
    <title><fmt:message key="title.balance" bundle="${bundle}" /></title>
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

<body style="display: block">


<!-- Left Panel -->

<jsp:include page="/WEB-INF/jsp/include/leftpanel.jsp"/>

<%--<jsp:include page="testpanel.jsp"/>--%>

<div id="right-panel" class="right-panel" style="width: 100%;">

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
                            <a class="nav-link" id="pills-home-tab"href="${pageContext.request.contextPath}/crud?command=changeLang&from=/crud?command=viewCurrentBalancePage&lang=ru"
                               role="tab" aria-controls="pills-home" aria-selected="false">RU</a>
                        </li>
                        <li class="nav-item active ">
                            <a class="nav-link" id="pills-profile-tab" href="${pageContext.request.contextPath}/crud?command=changeLang&from=/crud?command=viewCurrentBalancePage&lang=en"
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
        <my:alert needShow="${notValidData}" label="updatebalance.fail" message="updatebalance.notvalidcount"/>
        <my:alert needShow="${success}" label="updatebalance.success" message="updatebalance.succesupdate"/>


        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-6 offset-md-3 mr-auto ml-auto">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Credit Card</strong>
                        </div>
                        <div class="card-body">
                            <!-- Credit Card -->
                            <div id="pay-invoice">
                                <div class="card-body">
                                    <div class="card-title">
                                        <h3 class="text-center">Pay Invoice</h3>
                                    </div>
                                    <hr>
                                    <form action="${pageContext.request.contextPath}/crud" method="post" id="updateBalanceForm" novalidate="novalidate">
                                        <div class="form-group">
                                            <label for="cc-pament" class="control-label mb-1">Payment amount</label>
                                            <input id="cc-pament" name="paymentCount" type="text" class="form-control" aria-required="true" aria-invalid="false" value="100">
                                        </div>
                                        <div class="form-group has-success">
                                            <label for="cc-name" class="control-label mb-1">Name on card</label>
                                            <input id="cc-name" name="paymentNameOnCard" type="text" class="form-control cc-name valid" data-val="true" data-val-required="Please enter the name on card" autocomplete="cc-name" aria-required="true" aria-invalid="false" aria-describedby="cc-name-error">
                                            <span class="help-block field-validation-valid" data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
                                        </div>
                                        <div class="form-group">
                                            <label for="cc-number" class="control-label mb-1">Card number</label>
                                            <input id="cc-number" name="paymentCardNumber" type="tel" class="form-control cc-number identified visa" value="" data-val="true" data-val-required="Please enter the card number" data-val-cc-number="Please enter a valid card number" autocomplete="cc-number">
                                            <span class="help-block" data-valmsg-for="cc-number" data-valmsg-replace="true"></span>
                                        </div>
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group">
                                                    <label for="cc-exp" class="control-label mb-1">Expiration</label>
                                                    <input id="cc-exp" name="paymentCardExpiration" type="tel" class="form-control cc-exp" value="" data-val="true" data-val-required="Please enter the card expiration" data-val-cc-exp="Please enter a valid month and year" placeholder="MM / YY" autocomplete="cc-exp">
                                                    <span class="help-block" data-valmsg-for="cc-exp" data-valmsg-replace="true"></span>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <label for="x_card_code" class="control-label mb-1">Security code</label>
                                                <div class="input-group">
                                                    <input id="x_card_code" name="paymentCardSecurityCode" type="tel" class="form-control cc-cvc" value="" data-val="true" data-val-required="Please enter the security code" data-val-cc-cvc="Please enter a valid security code" autocomplete="off">
                                                    <div class="input-group-addon">
                                                        <span class="fa fa-question-circle fa-lg" data-toggle="popover" data-container="body" data-html="true" data-title="Security Code" data-content="<div class='text-center one-card'>The 3 digit code on back of the card..<div class='visa-mc-cvc-preview'></div></div>" data-trigger="hover"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <button id="payment-button" type="submit" form="updateBalanceForm" class="btn btn-lg btn-info btn-block">
                                                <span id="payment-button-amount">Pay $100.00</span>
                                            </button>
                                        </div>
                                        <input type="hidden" name="command" value="updateBalance">
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div> <!-- .card -->

                </div>
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