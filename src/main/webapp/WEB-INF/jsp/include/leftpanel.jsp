<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/1/2019
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>

<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">
        <div class="navbar-header">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/crud?command=account"><img src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo"></a>

            <a class="navbar-brand hidden" href="${pageContext.request.contextPath}/site"><img src="${pageContext.request.contextPath}/static/images/logo2.png" alt="Logo"></a>
        </div>

        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">Menu</h3><!-- /.menu-title -->
                </li>

                <li class="active">
                    <a href="${pageContext.request.contextPath}/crud?command=account" id="accountId"> <i class="menu-icon fa fa-dashboard"></i> <b>Account </b></a>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Music shop</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/crud?command=compositionShop">Composition</a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/crud?command=albumShop">Album</a></li>
                        <li><i class="menu-icon fa fa-pie-chart"></i><a href="${pageContext.request.contextPath}/crud?command=musicSelectionShop">Music selection</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Balance</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/crud?command=currentBalance">Current balance</a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/site/charts-flot.html">Update balance</a></li>
                        <li><i class="menu-icon fa fa-pie-chart"></i><a href="${pageContext.request.contextPath}/site/charts-peity.html">History</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Feedback</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewCompositionFeedbackPage">Composition</a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewAlbumFeedback">Album</a></li>
                        <li><i class="menu-icon fa fa-pie-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewMusicSelectionFeedback">Music selection</a></li>
                    </ul>
                </li>


                <c:if test="${userDto.role.role.equalsIgnoreCase('ADMIN')}">
                    <jsp:include page="/WEB-INF/jsp/include/adminPanel.jsp"/>
                </c:if>

            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside><!-- /#left-panel -->

<script src="${pageContext.request.contextPath}/site/vendors/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/popper.js/dist/umd/popper.min.js"></script>

<script src="${pageContext.request.contextPath}/site/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/jquery-validation-unobtrusive/dist/jquery.validate.unobtrusive.min.js"></script>

<script src="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/site/assets/js/main.js"></script>