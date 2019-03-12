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
                <li class="active">
                    <a href="${pageContext.request.contextPath}/crud?command=move&path=account" id="id1"> <i class="menu-icon fa fa-dashboard"></i> <b>Account </b></a>
                </li>
                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">UI elements</h3><!-- /.menu-title -->
                </li>

                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">Icons</h3><!-- /.menu-title -->
                </li>
                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">Extras</h3><!-- /.menu-title -->
                </li>

                <c:if test="${userDto.role.role.equalsIgnoreCase('ADMIN')}">
                    <jsp:include page="adminPanel.jsp"/>
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