<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/12/2019
  Time: 12:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<li class="menu-item-has-children dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"> <i class="menu-icon fa fa-laptop"></i><b>Admin panel</b></a>
    <ul class="sub-menu children active dropdown-menu">
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewAdminCreateAlbumPage">Create album</a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewAdminAddUserBonusPage">Add user bonus</a></li>
        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/crud?command=viewUploadCompositionPage">Upload composition</a></li>
    </ul>
</li>