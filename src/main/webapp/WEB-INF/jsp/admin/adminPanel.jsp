<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/12/2019
  Time: 12:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<li class="menu-item-has-children dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"> <i class="menu-icon fa fa-laptop"></i><b><fmt:message key="adminpanel.title" bundle="${bundle}"/></b></a>
    <ul class="sub-menu children active dropdown-menu">
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewAdminCreateAlbumPage"><fmt:message key="adminpanel.createalbum" bundle="${bundle}"/></a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewAdminAddUserBonusPage"><fmt:message key="adminpanel.addbonus" bundle="${bundle}"/></a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewUploadCompositionPage"><fmt:message key="adminpanel.downloadcomposition" bundle="${bundle}"/></a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewCreateMusicSelectionPage"><fmt:message key="adminpanel.createmusicselection" bundle="${bundle}"/></a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewUpdateMusicSelectionPage"><fmt:message key="adminpanel.updatemusicselection" bundle="${bundle}"/></a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewUploadCompositionToMusicSelectionPage"><fmt:message key="adminpanel.addcompositiontomusicselection" bundle="${bundle}"/></a></li>
    </ul>
</li>