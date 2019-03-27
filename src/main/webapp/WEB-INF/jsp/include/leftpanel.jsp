<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/1/2019
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>

<aside id="left-panel" class="left-panel" style="min-width: 280px;">
    <nav class="navbar navbar-expand-sm navbar-default">
        <div class="navbar-header">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/crud?command=account"><img src="${pageContext.request.contextPath}/site/images/logo_name.png" style="margin-top: 10px" alt="Logo"></a>

            <a class="navbar-brand hidden" href="${pageContext.request.contextPath}/site"><img src="${pageContext.request.contextPath}/site/images/logo_icon.png" alt="Logo"></a>
        </div>

        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title"><fmt:message key="leftpanel.menu" bundle="${bundle}"/></h3><!-- /.menu-title -->
                </li>

                <li class="active">
                    <a href="${pageContext.request.contextPath}/crud?command=account" id="accountId"> <i class="menu-icon fa fa-dashboard"></i> <b><fmt:message key="leftpanel.account" bundle="${bundle}"/></b></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/crud?command=music" id="accountId1"> <i class="menu-icon fa fa-bar-chart"></i><fmt:message key="leftpanel.mymusic" bundle="${bundle}"/></a>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i><fmt:message key="leftpanel.musicshop" bundle="${bundle}"/></a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/crud?command=compositionShop"><fmt:message key="leftpanel.compositionshop" bundle="${bundle}"/></a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/crud?command=albumShop"><fmt:message key="leftpanel.albumshop" bundle="${bundle}"/></a></li>
                        <li><i class="menu-icon fa fa-pie-chart"></i><a href="${pageContext.request.contextPath}/crud?command=musicSelectionShop"><fmt:message key="leftpanel.musicselectionshop" bundle="${bundle}"/></a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i><fmt:message key="leftpanel.balance" bundle="${bundle}"/></a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewCurrentBalancePage"><fmt:message key="leftpanel.currentbalance" bundle="${bundle}"/></a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewUpdateBalancePage"><fmt:message key="leftpanel.updatebalance" bundle="${bundle}"/></a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i><fmt:message key="leftpanel.feedbackpanel" bundle="${bundle}"/></a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewCompositionFeedbackPage"><fmt:message key="leftpanel.feedback.composition" bundle="${bundle}"/></a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewAlbumFeedbackPage"><fmt:message key="leftpanel.feedback.album" bundle="${bundle}"/></a></li>
                        <li><i class="menu-icon fa fa-pie-chart"></i><a href="${pageContext.request.contextPath}/crud?command=viewMusicSelectionFeedbackPage"><fmt:message key="leftpanel.feedback.musicselection" bundle="${bundle}"/></a></li>
                    </ul>
                </li>


                <c:if test="${role == 1}">
                    <jsp:include page="/WEB-INF/jsp/admin/adminPanel.jsp"/>
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