<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 2/28/2019
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-4">
    <div class="card">
        <div class="card-header">
            <strong class="card-title mb-3">Profile Card</strong>
        </div>
        <div class="card-body">
            <div class="mx-auto d-block">
                <img class="rounded-circle mx-auto d-block" src="${pageContext.request.contextPath}/static/images/admin.jpg " alt="Card image cap">
                <h5 class="text-sm-center mt-2 mb-1"><c:out value="${albumFeedback.date} ${albumFeedback.id}"/></h5>
                <div class="location text-sm-center"><i class="fa fa-map-marker"></i><c:out value="${albumFeedback.feedback}"/></div>
            </div>
            <hr>
            <div class="card-text text-sm-center">
                <a href="#"><i class="fa fa-facebook pr-1"></i></a>
                <a href="#"><i class="fa fa-twitter pr-1"></i></a>
                <a href="#"><i class="fa fa-linkedin pr-1"></i></a>
                <a href="#"><i class="fa fa-pinterest pr-1"></i></a>
            </div>
        </div>
    </div>
</div>